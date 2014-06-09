package com.vizend.town.reign.ruling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.dto.MetroCDto;
import com.vizend.town.reign.nation.shared.dto.MetroRDto;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.ruling.facade.MetroManager;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MetroManagerLogic implements MetroManager {
	//
	@Autowired
	private MetroEntity metroEntity;

	@Autowired
	private NationEntity nationEntity; 
	
	@Autowired
	private CitizenEntity citizenEntity; 
	
	@Autowired
	private TownEntity townEntity; 
	
	@Autowired
	private TownIdGenerator townIdGenerator; 
	
	//--------------------------------------------------------------------------
	//Metro

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String registMetro(String nationId, MetroCDto metroCDto) {
		// 
		Nation nation = nationEntity.retrieve(nationId);
		String metroId = townIdGenerator.generateMetroId(nationId);
		
		Metro metro = metroCDto.createMetro(nation, metroId);
		metroEntity.create(metro);

		return metro.getId();
	}

	@Override
	public MetroRDto findMetro(String metroId) {
		//
		Metro metro = metroEntity.retrieve(metroId);
		MetroRDto metroDto = MetroRDto.createRDto(metro);

		return metroDto;
	}

	@Override
	public List<MetroRDto> findMetrosInNation(String nationId) {
		//
		List<Metro> metros = metroEntity.retrieveByNationId(nationId);
		return MetroRDto.createDtos(metros);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void modifyMetro(String metroId, List<NameValue> pairs) {
		//
		Metro metro = metroEntity.retrieve(metroId);
		if (metro == null) {
			throw new VizendTownException("No such a metro:" + metroId); 
		}
		
		metro.setValues(pairs);
		metroEntity.update(metro);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(String metroId) {
		//
		int townCount = townEntity.countByMetroId(metroId); 
		if (townCount > 0) {
			throw new VizendTownException("Can't remove the metro, town count -> " + townCount);
		}
		
		int citizenCount = citizenEntity.countByMetroId(metroId); 
		if (citizenCount > 0) {
			throw new VizendTownException("Can't remove the metro, citizen count -> " + citizenCount);
		}
		
		Metro metro = metroEntity.retrieve(metroId); 
		metroEntity.delete(metro); 
	}
}
