package com.vizend.town.reign.ruling.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.dream.util.EncryptUtils;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domainbackup.CitizenBackup;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.TownerEntity;
import com.vizend.town.reign.people.shared.dto.CitizenCDto;
import com.vizend.town.reign.people.shared.dto.CitizenRDto;
import com.vizend.town.reign.ruling.facade.CitizenManager;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 12. 12.
 */
@Repository
public class CitizenManagerLogic implements CitizenManager {
	//
	@Autowired
	private TownIdGenerator townIdGenerator;

	@Autowired
	private CitizenEntity citizenEntity;
	
	@Autowired
	private CastellanEntity castellanEntity;
	
	@Autowired
	private NationEntity nationEntity;
	
	@Autowired
	private TownerEntity townerEntity;

	//--------------------------------------------------------------------------
	// methods

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String registCitizen(String metroId, CitizenCDto citizenCDto) {
		//
		// validation
		if(citizenEntity.existByLoginId(citizenCDto.getLoginId())){
			throw new VizendTownException("loginId already exists! >> loginId : "+citizenCDto.getLoginId());
		}
		
		Castellan castellan = null; 
		
		if (!castellanEntity.existByAuthEmail(citizenCDto.getEmail())) {
			Nation nation = nationEntity.retrieveByMetroId(metroId);
			castellan = new Castellan(
					nation.getId(), 
					townIdGenerator.generateCastellanId(nation.getId()), 
					citizenCDto.getLoginName(), 
					citizenCDto.getEmail()); 
			castellanEntity.create(castellan);
		} else {
			castellan = castellanEntity.retrieveByAuthEmail(citizenCDto.getEmail());
		}
		
		String citizenId = townIdGenerator.generateCitizenId(metroId); 
		Citizen citizen = citizenCDto.createDomain(castellan, metroId, citizenId);
		citizen.setPassword(EncryptUtils.encript(citizenCDto.getPassword()));

		return citizenEntity.create(citizen);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registCitizens(String metroId, List<CitizenCDto> citizenDtos) {
		//
		for(CitizenCDto citizenDto : citizenDtos){
			registCitizen(metroId, citizenDto);
		}
	}

	@Override
	public CitizenRDto findCitizen(String citizenId) {
		//
		Citizen citizen = citizenEntity.retrieve(citizenId);
		if (citizen == null) {
			throw new VizendTownException("No such a citizen:" + citizenId);
		}
		CitizenRDto citizenDto = CitizenRDto.createDto(citizen);

		return citizenDto;
	}

	@Override
	public CitizenRDto findCitizenByLoginId(String loginId) {
		Citizen citizen = citizenEntity.retrieveByLoginId(loginId);
		CitizenRDto citizenDto = CitizenRDto.createDto(citizen);

		return citizenDto;
	}

	@Override
	public List<CitizenRDto> findCitizensByMetroId(String metroId, int offset, int pageSize) {
		//
		List<Citizen> domainList = citizenEntity.retrieveByMetroId(metroId, offset, pageSize);
		List<CitizenRDto> dtoList = new ArrayList<CitizenRDto>();
		for (Citizen domain : domainList) {
			CitizenRDto dto = CitizenRDto.createDto(domain);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public List<CitizenRDto> findCitizensByIds(List<String> citizenIds) {
		//
		List<Citizen> citizens = citizenEntity.retrieveByIds(citizenIds);
		return CitizenRDto.createDtos(citizens);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyCitizen(String citizenId, List<NameValue> pairs) {
		//
		Citizen citizen = citizenEntity.retrieve(citizenId);
		if (citizen == null) {
			throw new VizendTownException("No such a citizen:" + citizenId);
		}

		citizen.setValues(pairs);
		citizenEntity.update(citizen);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void removeCitizen(String citizenId) {
		//
		Citizen citizen = citizenEntity.retrieve(citizenId);
		
		CitizenBackup citizenBackup = new CitizenBackup(citizen);
		citizenEntity.createBackup(citizenBackup);
		
		if(citizen != null){
			townerEntity.deleteByCitizenId(citizen.getId());
			citizenEntity.delete(citizen);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCitizens(List<String> citizenIds) {
		//
		for(String citizenId : citizenIds){
			removeCitizen(citizenId);
		}
	}
}