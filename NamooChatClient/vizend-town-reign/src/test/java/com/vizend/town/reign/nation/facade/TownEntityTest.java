package com.vizend.town.reign.nation.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.shared.helper.NationHelper;

/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 16, 2013
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class TownEntityTest extends SpringDbUnitTestTemplate {
	//
	@Test
	public void testCreate(){
		// setup
		Town sampleTown = prepareTown(); 
		
		// verification
		Town retrieved = townEntity.retrieve(sampleTown.getId());
		assertThat(retrieved, is(not(nullValue())));
	}
	
	@Test
	public void testRetrieveByMetroId(){
		// setup
		Town sampleTown = prepareTown(); 
		
		// example
		List<Town> towns = townEntity.retrieveByMetroId(sampleTown.getMetro().getId());
		
		// verification
		assertThat(towns, is(not(nullValue())));
		assertThat(towns.size(), is(1));
	}
	
	@Test
	public void testUpdate(){
		// setup
		Town sampleTown = prepareTown(); 
		
		Town town = townEntity.retrieve(sampleTown.getId()); 
		String newName = "NewTown";
		town.setName(newName);
		townEntity.update(town);
		
		// verification
		Town modifiedTown = townEntity.retrieve(sampleTown.getId());
		assertThat(modifiedTown, is(not(nullValue())));
		assertThat(modifiedTown.getName(), is(newName));
	}
	
	@Test
	public void testDelete(){
		// setup
		Town sampleTown = prepareTown(); 
		
		Town town = townEntity.retrieve(sampleTown.getId());
		assertThat(town, is(not(nullValue())));
		
		// example
		townEntity.delete(town);
		
		// verification
		try {
			townEntity.retrieve(sampleTown.getId());
			assertTrue(false); 
		} catch (Exception e) {
			assertTrue(true); 
		}
	}
	
	@Test
	public void testExistByTownName(){
		// setup
		Metro metro = prepareMetro();
		int maxTownCount = 3;
		List<Town> towns = prepareTowns(metro, maxTownCount);
		
		Town targetTown = towns.get(0);
		
		// verification
		boolean hasTown = townEntity.existByTownName(metro.getId(), targetTown.getName());
		assertTrue(hasTown);
	}
	
	@Test
	public void testExistDefaultTown(){
		// setup
		Metro metro = prepareMetro();
		int maxTownCount = 3;
		List<Town> towns = prepareTowns(metro, maxTownCount);
		assertThat(towns.size(), is(3));
		
		// verification
		boolean hasDefaultTown = townEntity.existDefaultTown(metro.getId());
		assertTrue(hasDefaultTown);
	}
	
	@Test
	public void testCountByMetroId(){
		// setup
		Metro metro = prepareMetro();
		int maxTownCount = 3;
		List<Town> towns = prepareTowns(metro, maxTownCount);
		
		// example
		int townCount = townEntity.countByMetroId(metro.getId());
		assertThat(townCount, is(towns.size()));
	}
	
	//--------------------------------------------------------------------------
	
	@Autowired TownEntity townEntity;
	@Autowired MetroEntity metroEntity;
	@Autowired NationEntity nationEntity; 
    @Autowired TownIdGenerator idGen; 

	//--------------------------------------------------------------------------
	
    private Metro prepareMetro() {
    	// 
    	String nationId = idGen.generateNationId();
    	Nation sampleNation = NationHelper.getSampleNation(nationId); 
    	nationEntity.create(sampleNation); 
    	
    	Nation resultNation = nationEntity.retrieve(sampleNation.getId());
    	assertNotNull(resultNation);
    	
    	String metroId = idGen.generateMetroId(resultNation.getId()); 
    	Metro metro = NationHelper.getSampleMetro(resultNation, metroId, 1); 
    	metroEntity.create(metro); 
    	
    	Metro resultMetro = metroEntity.retrieve(metro.getId());
    	assertNotNull(resultMetro);
    	
    	return resultMetro; 
    }
    
    private List<Town> prepareTowns(Metro metro, int maxTownCount){
    	//
    	List<Town> towns = new ArrayList<Town>();
    	
    	for(int count=0; count < maxTownCount; count++){
    		String townId = idGen.generateTownId(metro.getId());
    		Town town = NationHelper.getSampleTown(metro, townId, count);
    		townEntity.create(town);
    		
    		Town resultTown = townEntity.retrieve(town.getId());
    		assertNotNull(resultTown);
    		
    		towns.add(resultTown);
    	}
    	
    	return towns;
    }
    
    private Town prepareTown() {
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
    	
    	String townId = idGen.generateTownId(resultMetro.getId()); 
    	Town town = NationHelper.getSampleTown(resultMetro, townId, 1); 
    	townEntity.create(town); 
    	
    	Town resultTown = townEntity.retrieve(town.getId());
    	assertNotNull(resultTown);
    	
    	return resultTown; 
    }
    
}
