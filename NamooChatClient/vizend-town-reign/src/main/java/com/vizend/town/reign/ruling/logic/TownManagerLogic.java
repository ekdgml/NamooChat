/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
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
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.dto.TownCDto;
import com.vizend.town.reign.nation.shared.dto.TownRDto;
import com.vizend.town.reign.ruling.facade.TownManager;

/**
 * TOWN CRUD
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 21.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class TownManagerLogic implements TownManager {

    @Autowired
    private MetroEntity metroEntity;

    @Autowired
    private TownEntity townEntity;

	@Autowired
	private TownIdGenerator townIdGenerator; 
	
    //--------------------------------------------------------------------------
    //Town

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String registTown(String metroId, TownCDto townCDto) {
		//
		if(townCDto.isDefaultTown() && townEntity.existDefaultTown(metroId)){
			throw new VizendTownException("Default Town already exists.");
		}
		Metro metro = metroEntity.retrieve(metroId);
		String townId = townIdGenerator.generateTownId(metroId);

		Town town = townCDto.createTown(metro, townId);
		townEntity.create(town);

		return town.getId();
	}
	
	@Override
    public TownRDto findTown(String townId) {
        //
        Town town = townEntity.retrieve(townId);
        return TownRDto.createRDto(town);
    }

	public List<TownRDto> findTownsInMetro(String metroId) {
		// 
		List<Town> towns = townEntity.retrieveByMetroId(metroId);
		
		return TownRDto.createDtos(towns); 
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyTown(String townId, List<NameValue> pairs) {
        //
        Town town = townEntity.retrieve(townId);
        town.setValues(pairs);

        townEntity.update(town);
    }

    @Override
    public boolean hasTownName(String metroId, String townName) {
    	//
    	return townEntity.existByTownName(metroId, townName);
    }

}
