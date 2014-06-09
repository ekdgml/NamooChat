package com.vizend.town.reign.ruling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.dto.NationCDto;
import com.vizend.town.reign.nation.shared.dto.NationRDto;
import com.vizend.town.reign.ruling.facade.NationManager;
import com.vizend.town.reign.ruling.shared.MetroHierarchyDto;
import com.vizend.town.reign.ruling.shared.NationHierarchyDto;
import com.vizend.town.reign.ruling.shared.TownHierarchyDto;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 27.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class NationManagerLogic implements NationManager {
    //
    @Autowired
    private NationEntity nationEntity;

    @Autowired
    private MetroEntity metroEntity;

	@Autowired
	private TownEntity townEntity;

	@Autowired
	private TownIdGenerator townIdGenerator;
	
    //--------------------------------------------------------------------------
    // methods

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String buildNation(NationCDto nationCDto) {
		// 
		String nationId = townIdGenerator.generateNationId();
		Nation nation = nationCDto.createNation(nationId);
		return nationEntity.create(nation);
	}
	
    @Override
    public NationRDto findNation(String nationId) {
        //
        Nation nation = nationEntity.retrieve(nationId);
        return NationRDto.createDto(nation);
    }

    @Override
    public NationRDto findParentNation(String metroId) {
    	//
    	Metro metro = metroEntity.retrieve(metroId);
    	Nation nation = metro.getNation();
    	return NationRDto.createDto(nation);
    }

    @Override
    public NationRDto findNationOfTown(String townId) {
    	//
    	Town town = townEntity.retrieve(townId);
    	Nation nation = town.getMetro().getNation();
    	return NationRDto.createDto(nation);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modify(String nationId, List<NameValue> pairs) {
    	//
    	Nation nation = nationEntity.retrieve(nationId);
    	nation.setValues(pairs);
    	nationEntity.update(nation);
    }

    @Override
    public NationHierarchyDto findNationHierarchy(String nationId) {
    	//
    	Nation nation = nationEntity.retrieve(nationId);
    	NationHierarchyDto nationHirDto = NationHierarchyDto.createDto(nation);
    	List<Metro> metros = nation.getMetros();
    	for(Metro metro : metros){
    		MetroHierarchyDto metroHirDto = MetroHierarchyDto.createDto(metro);
    		nationHirDto.addMetroHirDto(metroHirDto);

    		List<Town> towns = metro.getTowns();
    		for(Town town : towns){
    			TownHierarchyDto townHirDto = TownHierarchyDto.createDto(town);
    			metroHirDto.addTownHirDto(townHirDto);
    		}
    	}
    	return nationHirDto;
    }
}
