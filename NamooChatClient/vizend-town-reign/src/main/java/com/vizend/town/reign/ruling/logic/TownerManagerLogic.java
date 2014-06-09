package com.vizend.town.reign.ruling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.TownerEntity;
import com.vizend.town.reign.people.shared.dto.TownerRDto;
import com.vizend.town.reign.ruling.facade.TownerManager;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class TownerManagerLogic implements TownerManager {
	//
	@Autowired
	private TownerEntity townerEntity;
	
	@Autowired
	private CitizenEntity citizenEntity; 

	//--------------------------------------------------------------------------
	// methods 
	
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String registTowner(String townId, String citizenId) {
    	//
    	Citizen citizen = citizenEntity.retrieve(citizenId);
    	Towner towner = new Towner(citizen, townId);
    	return townerEntity.create(towner);
    }
    
    @Override
    public TownerRDto findTowner(String townerId) {
    	//
    	Towner towner = townerEntity.retrieve(townerId);
    	return TownerRDto.createDto(towner);
    }

    @Override
	public List<TownerRDto> findTownersByTownId(String townId, int offset, int pageSize) {
		//
    	List<Towner> towners = townerEntity.retrieveByTownId(townId, offset, pageSize); 
    	return TownerRDto.createDtos(towners);
	}

    @Override
    public List<TownerRDto> findTownersByCitizenId(String citizenId) {
    	//
    	List<Towner> towners = townerEntity.retrieveByCitizenId(citizenId);
    	return TownerRDto.createDtos(towners);
    }

}
