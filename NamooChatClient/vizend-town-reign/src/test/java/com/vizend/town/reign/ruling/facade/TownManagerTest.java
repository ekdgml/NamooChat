package com.vizend.town.reign.ruling.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.dto.TownCDto;
import com.vizend.town.reign.nation.shared.dto.TownRDto;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.TownerEntity;
 
/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 21.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class TownManagerTest extends SpringDbUnitTestTemplate {

    @Autowired
    private TownManager townManager;

    //--------------------------------------------------------------------------
    //Town CRUD
    
    @Test
    public void testRegistTown(){
    	// 
    	Metro metro = prepareMetro();
    	
    	String metroId = metro.getId();
    	int count = 1;
    	
    	TownCDto townCDto = NationHelper.getSampleTownCDto(count);
    	String townId = townManager.registTown(metroId, townCDto);
    	
    	// verification
    	TownRDto townRDto = townManager.findTown(townId);
    	assertNotNull(townRDto);
    	assertThat(townCDto.getName(), is(townRDto.getName()));
    }
    
    @Test
    public void testFindTown(){
    	//
    	Town sampleTown = prepareTown(); 
    	
    	TownRDto townDto = townManager.findTown(sampleTown.getId());
    	
    	// verification
    	assertNotNull(townDto);
    	assertThat(townDto.getName(), is(sampleTown.getName()));
    }
    
    @Test
    public void findTownsInMetro(){
    	//
    	Metro metro = prepareMetro();
    	
    	int maxTownCount = 3;
    	List<Town> towns = prepareTowns(metro, maxTownCount);
    	
    	// verification
    	List<TownRDto> townDtos = townManager.findTownsInMetro(metro.getId());
    	assertThat(townDtos.size(), is(3));
    	assertThat(townDtos.get(0).getName(), is(towns.get(0).getName()));
    	assertThat(townDtos.get(1).getName(), is(towns.get(1).getName()));
    	assertThat(townDtos.get(2).getName(), is(towns.get(2).getName()));
    }
    
    @Test
    public void modifyTown(){
    	// setup
    	Town sampleTown = prepareTown(); 
    	
    	String newTownName = "NewSoft Town";
    	String newIntro = "올바른 성장과 따뜻한 나눔";
    	List<NameValue> pairs = new ArrayList<NameValue>();
    	pairs.add(new NameValue("name", newTownName));
    	pairs.add(new NameValue("intro", newIntro));
     	
    	// example
    	townManager.modifyTown(sampleTown.getId(), pairs);
    	
    	// verification
    	TownRDto townDto = townManager.findTown(sampleTown.getId());
    	assertNotNull(townDto);
    	assertThat(townDto.getName(), is(newTownName));
    	assertThat(townDto.getIntro(), is(newIntro));
    }
    
    @Test
    public void testHasTownName(){
    	//
    	Metro metro = prepareMetro();
    	
    	int maxTownCount = 1;
    	List<Town> towns = prepareTowns(metro, maxTownCount);
    	
    	// verification
    	boolean hasTownName = townManager.hasTownName(metro.getId(), towns.get(0).getName());
    	assertTrue(hasTownName);
    }
    
    //--------------------------------------------------------------------------
    
    @Autowired TownIdGenerator idGen; 
	@Autowired NationEntity nationEntity; 
	@Autowired CastellanEntity castellanEntity; 
	@Autowired MetroEntity metroEntity; 
	@Autowired CitizenEntity citizenEntity; 
	@Autowired TownEntity townEntity; 
	@Autowired TownerEntity townerEntity; 

	//--------------------------------------------------------------------------
     
    private Town prepareTown() {
    	//
    	Metro metro = prepareMetro(); 
    	
    	int townCount = 1;
    	String townId = idGen.generateTownId(metro.getId()); 
    	Town town = NationHelper.getSampleTown(metro, townId, townCount); 
    	townEntity.create(town);
    	
    	Town resultTown = townEntity.retrieve(town.getId());
    	assertNotNull(resultTown);
    	
    	return resultTown; 
    }
    
    private List<Town> prepareTowns(Metro metro, int maxTownCount){
    	//
    	List<Town> towns = new ArrayList<Town>();
    	for(int count=0; count < maxTownCount ; count++){
    		String townId = idGen.generateTownId(metro.getId()); 
        	Town town = NationHelper.getSampleTown(metro, townId, count); 
        	townEntity.create(town);
        	
        	Town resultTown = townEntity.retrieve(town.getId());
        	assertNotNull(resultTown);
        	
        	towns.add(resultTown);
    	}
    	
    	return towns;
    }
    
    private Metro prepareMetro(){
    	//
    	String nationId = idGen.generateNationId();
    	Nation nation = NationHelper.getSampleNation(nationId); 
    	nationEntity.create(nation); 
    	
    	Nation resultNation = nationEntity.retrieve(nation.getId());
    	assertNotNull(resultNation);
    	
    	String metroId = idGen.generateMetroId(resultNation.getId()); 
    	Metro metro = NationHelper.getSampleMetro(resultNation, metroId, 1); 
    	metroEntity.create(metro);
    	
    	Metro resultMetro = metroEntity.retrieve(metro.getId());
    	assertNotNull(resultMetro);
    	
    	return resultMetro;
    }
}
