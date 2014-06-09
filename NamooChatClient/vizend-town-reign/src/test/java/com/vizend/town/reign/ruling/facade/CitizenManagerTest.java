package com.vizend.town.reign.ruling.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.shared.dto.CitizenRDto;
import com.vizend.town.reign.ruling.fillup.ReignFiller;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 12. 12.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class CitizenManagerTest extends SpringDbUnitTestTemplate {
	//
	@Autowired
	private CitizenManager citizenManager;
	
	@Autowired
	private ReignFiller filler;

	//--------------------------------------------------------------------------

    @Test
    public void testRegistCitizen(){
    	// create
    	Citizen sampleCitizen = filler.prepareCitizen(); 
    	
    	// verification
    	CitizenRDto result = citizenManager.findCitizen(sampleCitizen.getId());
    	assertThat(result.getLoginId(), is(sampleCitizen.getLoginId()));
    	assertThat(result.getEmail(), is(sampleCitizen.getEmail()));
    }

    @Test
    public void testRegistCitizens() {
    	// create
    	Citizen sampleCitizen = filler.prepareCitizen();
    	int offset = 0; 
    	int pageSize = 10; 

    	// verification
    	List<CitizenRDto> citizenDtos = citizenManager.findCitizensByMetroId(sampleCitizen.getMetroId(), offset, pageSize);
    	assertThat(citizenDtos.size(), is(1));
    }

    @Test
    public void testFindCitizenByLoginId() {
    	// setup
    	Citizen sampleCitizen = filler.prepareCitizen();

    	// verification
    	CitizenRDto result = citizenManager.findCitizenByLoginId(sampleCitizen.getLoginId());
    	assertThat(result.getLoginId(), is(sampleCitizen.getLoginId()));
    	assertThat(result.getEmail(), is(sampleCitizen.getEmail()));
    }

    @Test
    public void testFindCitizensByMetroId(){
    	// setup
    	Citizen sampleCitizen = filler.prepareCitizen();
    	int offset = 0; 
    	int pageSize = 10; 
    	
    	// example
    	List<CitizenRDto> citizens = citizenManager.findCitizensByMetroId(sampleCitizen.getMetroId(), offset, pageSize);
    	
    	// verification
    	assertThat(citizens.size(), is(1));
    }
    
    @Test
    public void testFindCitizens() {
    	// setup
    	Citizen sampleCitizen = filler.prepareCitizen(); 
    	List<String> citizenIds = new ArrayList<String>(); 
    	citizenIds.add(sampleCitizen.getId()); 

    	// example
    	List<CitizenRDto> citizenDtos = citizenManager.findCitizensByIds(citizenIds);
    	
    	// verification
    	assertThat(1, is(citizenDtos.size()));
    	assertThat(citizenDtos.get(0).getLoginId(), is(sampleCitizen.getLoginId()));
    	assertThat(citizenDtos.get(0).getEmail(), is(sampleCitizen.getEmail()));
    }

    @Test
    public void testModifyCitizen() {
    	// create
    	Citizen sampleCitizen = filler.prepareCitizen();

    	CitizenRDto result = citizenManager.findCitizen(sampleCitizen.getId());
    	assertThat(result.getLoginId(), is(sampleCitizen.getLoginId()));
    	assertThat(result.getEmail(), is(sampleCitizen.getEmail()));

    	String loginIdModified = "modifieduser"; 
    	String emailModified = "modifieduser@nextree.co.kr"; 
    	
    	
    	// modify
    	List<NameValue> pairs = new ArrayList<NameValue>();
    	pairs.add(new NameValue("loginId", loginIdModified));
    	pairs.add(new NameValue("email", emailModified));
    	citizenManager.modifyCitizen(sampleCitizen.getId(), pairs);

    	// verification
    	result = citizenManager.findCitizen(sampleCitizen.getId());
    	assertThat(result.getLoginId(), is(loginIdModified));
    	assertThat(result.getEmail(), is(emailModified));
    }

	@Test
    public void testRemoveCitizen(){
    	// setup
		Citizen sampleCitizen = filler.prepareCitizen();

    	CitizenRDto citizenDto = citizenManager.findCitizen(sampleCitizen.getId());
    	assertNotNull(citizenDto);

    	// example
    	citizenManager.removeCitizen(sampleCitizen.getId());

    	// verification
    	try {
			citizenManager.findCitizen(sampleCitizen.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			assertTrue(true); 
		}
    }
	
}