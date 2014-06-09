package com.vizend.town.reign.people.facade;
 
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;

/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ahn, young-su</a>
 * @date 2013. 3. 18.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class CitizenEntityTest extends SpringDbUnitTestTemplate {
	//
	@Test
	public void testCreate(){
		// setup
		Citizen sampleCitizen = prepareCitizen(); 
		
		// verification
		Citizen citizenResult = citizenEntity.retrieve(sampleCitizen.getId());
		assertThat(citizenResult, is(not(nullValue())));
	}
	
	@Test
	public void testRetrieveByMetroId(){
		// setup
		Citizen sampleCitizen = prepareCitizen();
		
		String metroId = sampleCitizen.getMetroId();
		int offset = 0;
		int pageSize = 1;
		
		// verification
		List<Citizen> citizens = citizenEntity.retrieveByMetroId(metroId, offset, pageSize);
		assertThat(citizens.size(), is(1));
		assertThat(citizens.get(0).getLoginName(), is(sampleCitizen.getLoginName()));
		assertThat(citizens.get(0).getLoginId(), is(sampleCitizen.getLoginId()));
	}
	
	@Test
	public void testRetrieveByIds(){
		// setup
		Citizen sampleCitizen = prepareCitizen();
		
		List<String> citizenIds = new ArrayList<String>();
		citizenIds.add(sampleCitizen.getId());
		
		// verification
		List<Citizen> citizenResults = citizenEntity.retrieveByIds(citizenIds);
		assertThat(citizenResults.size(), is(1));
		assertThat(citizenResults.get(0).getLoginName(), is(sampleCitizen.getLoginName()));
		assertThat(citizenResults.get(0).getLoginId(), is(sampleCitizen.getLoginId()));
	}
	
	
	@Test
	public void testUpdate(){
		// setup
		Citizen sampleCitizen = prepareCitizen(); 
		String newName = "New,Name";
		String newEmail = "newname@nextree.co.kr";
		
		Citizen citizen = citizenEntity.retrieve(sampleCitizen.getId());
		citizen.setLoginName(newName);
		citizen.setEmail(newEmail);
		
		// example
		citizenEntity.update(citizen);
		
		// verification
		Citizen citizenResult = citizenEntity.retrieve(sampleCitizen.getId());
		assertThat(citizenResult, is(not(nullValue())));
		assertThat(citizenResult.getLoginName(), is(newName));
		assertThat(citizenResult.getEmail(), is(newEmail));
	}
	
	@Test
	public void testDelete(){
		// setup
		Citizen sampleCitizen = prepareCitizen(); 
		
		Citizen citizen = citizenEntity.retrieve(sampleCitizen.getId());
		assertThat(citizen, is(not(nullValue())));
		
		// example
		citizenEntity.delete(citizen);
		
		// verification
		try {
			citizenEntity.retrieve(sampleCitizen.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			assertTrue(true); 
		}
	}
	
	@Test
	public void testCountByMeroId(){
		// setup
		Citizen sampleCitizen = prepareCitizen();
		
		// example
		int citizenCount = citizenEntity.countByMetroId(sampleCitizen.getMetroId());
		
		// verification
		assertThat(citizenCount, is(1));
	}
	
	@Test
	public void testAuthenticate(){
		// setup
		Citizen sampleCitizen = prepareCitizen();
		
		String loginId = sampleCitizen.getLoginId();
		String password = sampleCitizen.getPassword();
		
		// verification
		boolean authResult = citizenEntity.authenticate(loginId, password);
		assertTrue(authResult);
		
		boolean authFailResult = citizenEntity.authenticate(loginId+Integer.MAX_VALUE, password+Integer.MAX_VALUE);
		assertFalse(authFailResult);
	}
	
	@Test
	public void testExistsByLoginId(){
		// setup
		Citizen sampleCitizen = prepareCitizen();
		
		String loginId = sampleCitizen.getLoginId();
		
		// verification
		boolean exists = citizenEntity.existByLoginId(loginId);
		assertTrue(exists);
		
		boolean notExists = citizenEntity.existByLoginId(loginId+Integer.MAX_VALUE);
		assertFalse(notExists);
	}
	
	//--------------------------------------------------------------------------
	
	@Autowired CastellanEntity castellanEntity;
	@Autowired CitizenEntity citizenEntity;
	@Autowired NationEntity nationEntity; 
	@Autowired MetroEntity metroEntity; 
	@Autowired TownIdGenerator idGen; 
	
	//--------------------------------------------------------------------------
	
	private Citizen prepareCitizen() {
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
    	
    	return citizen; 
    }
	
}
