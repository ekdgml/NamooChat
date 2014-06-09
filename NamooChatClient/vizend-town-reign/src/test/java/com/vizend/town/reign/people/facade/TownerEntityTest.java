package com.vizend.town.reign.people.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;
 
/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ahn, young-su</a>
 * @date 2013. 2. 7.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class TownerEntityTest extends SpringDbUnitTestTemplate {
	//
	@Test
	public void testCreate(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		
		// verification
		Towner result = townerEntity.retrieve(sampleTowner.getId());
		assertThat(result, is(not(nullValue())));
	}
	
	@Test
	public void testRetrieveByCitizenId(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		String citizenId = sampleTowner.getCitizen().getId(); 
		
		// example
		List<Towner> towners = townerEntity.retrieveByCitizenId(citizenId);
		
		// verification
		assertThat(towners, is(not(nullValue())));
		assertThat(towners.size(), is(1));
	}
	
	@Test
	public void testRetrieveByTownAndCitizenId(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		String townId = sampleTowner.getTownId(); 
		String citizenId = sampleTowner.getCitizen().getId(); 
		
		// example
		Towner towner = townerEntity.retrieve(townId, citizenId);
		
		// verification
		assertThat(towner, is(not(nullValue())));
	}
	
	@Test
	public void testRetrieveByTownId(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		String townId = sampleTowner.getTownId(); 
		
		int offset = 0; 
		int pageSize = 10; 
		
		// example
		List<Towner> towners = townerEntity.retrieveByTownId(townId, offset, pageSize);
		
		// verification
		assertThat(towners, is(not(nullValue())));
		assertThat(towners.size(), is(1));
	}
	
	@Test
	public void testDelete(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		
		Towner towner = townerEntity.retrieve(sampleTowner.getId());
		assertThat(towner, is(not(nullValue())));
		
		// example
		townerEntity.delete(towner);
		
		// verification
		try {
			townerEntity.retrieve(sampleTowner.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			assertTrue(true); 
		}
	}
	
	@Test
	public void testCountByTownId(){
		// setup
		Towner sampleTowner = prepareTowner(); 
		
		// example
		int count = townerEntity.countByTownId(sampleTowner.getTownId());
		
		// verification
		assertThat(count, is(1));
	}
	
	//--------------------------------------------------------------------------
	
	@Autowired TownerEntity townerEntity;
	@Autowired TownEntity townEntity; 
	@Autowired TownIdGenerator idGen; 
	@Autowired CastellanEntity castellanEntity; 
	@Autowired CitizenEntity citizenEntity;
	@Autowired NationEntity nationEntity; 
	@Autowired MetroEntity metroEntity; 

	//--------------------------------------------------------------------------
	
	private Towner prepareTowner() {
		//
		String nationId = idGen.generateNationId(); 
		Nation nation = NationHelper.getSampleNation(nationId); 
		nationEntity.create(nation);
		// 
		String castellanId = idGen.generateCastellanId(nationId);
		Castellan castellan = PeopleHelper.getSampleCastellan(nationId, castellanId, 1);
		castellanEntity.create(castellan); 
    	// 
    	String metroId = idGen.generateMetroId(nation.getId()); 
    	Metro metro = NationHelper.getSampleMetro(nation, metroId, 1); 
    	metroEntity.create(metro);
    	//
    	String citizenId = idGen.generateCitizenId(metroId); 
    	Citizen citizen = PeopleHelper.getSampleCitizen(metroId, castellan, citizenId, 1); 
    	citizenEntity.create(citizen); 
    	
    	String townId = idGen.generateTownId(metroId); 
    	Town town = NationHelper.getSampleTown(metro, townId, 1);
    	townEntity.create(town); 
    	
    	Towner towner = PeopleHelper.getSampleTowner(townId, citizen, 1);
    	townerEntity.create(towner); 
    	
    	return towner; 
    }
}
