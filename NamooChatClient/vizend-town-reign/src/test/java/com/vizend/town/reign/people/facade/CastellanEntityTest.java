package com.vizend.town.reign.people.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;

/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ahn, young-su</a>
 * @date 2013. 1. 26.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class CastellanEntityTest extends SpringDbUnitTestTemplate  {
	//
	
	@Test
    public void testCreate(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	// verification
    	Castellan target = castellanEntity.retrieve(sampleCastellan.getId());
    	assertThat(target.getAuthEmail(), is(sampleCastellan.getAuthEmail()));
    	assertThat(target.getAuthPhone(), is(sampleCastellan.getAuthPhone()));
    	assertThat(target.getNationId(), is(sampleCastellan.getNationId()));
    }

    @Test
    public void testRetrieveByAuthEmail(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();

    	// example
    	Castellan resultCastellan = castellanEntity.retrieveByAuthEmail(sampleCastellan.getAuthEmail());
    	
    	// verification
    	assertThat(resultCastellan, is(not(nullValue())));
    	assertThat(resultCastellan.getAuthEmail(), is(sampleCastellan.getAuthEmail()));
    }
    
    @Test
    public void testRetrieveByAuthPhone(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	// example
    	Castellan resultCastellan = castellanEntity.retrieveByAuthPhone(sampleCastellan.getAuthPhone());
    	
    	// verification
    	assertThat(resultCastellan.getAuthPhone(), is(not(nullValue())));
    }
    
    @Test
    public void testUpdate(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	Castellan newCastellan = castellanEntity.retrieve(sampleCastellan.getId());
    	
    	String newAuthEmail = "newAuth@nextree.co.kr";
    	String newAuthPhone = "011-8790-0900";
    	
    	// example
    	newCastellan.setAuthEmail(newAuthEmail);
    	newCastellan.setAuthPhone(newAuthPhone);
    	castellanEntity.update(newCastellan);
    	
    	// verification
    	Castellan modified = castellanEntity.retrieve(sampleCastellan.getId());
    	assertThat(modified, is(not(nullValue())));
    	assertThat(modified.getAuthEmail(), is(newAuthEmail));
    	assertThat(modified.getAuthPhone(), is(newAuthPhone));
    	
    }
    
    @Test
    public void testDelete(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	Castellan newCastellan = castellanEntity.retrieve(sampleCastellan.getId());
    	assertThat(newCastellan, is(not(nullValue())));
    	
    	// example
    	castellanEntity.delete(newCastellan);
    	
    	// verification
    	try {
			castellanEntity.retrieve(sampleCastellan.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			assertTrue(true); 
		}
    }
    
    @Test
    public void testExistByAuthEmail(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	// example
    	Castellan resultCastellan = castellanEntity.retrieveByAuthEmail(sampleCastellan.getAuthEmail());
    	
    	// verification
    	assertThat(resultCastellan, is(not(nullValue())));
    }
    
    @Test
    public void testExistByAuthPhone(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	// example
    	boolean result = castellanEntity.existByAuthPhone(sampleCastellan.getAuthPhone());
    	
    	// verification
    	assertThat(result, is(true));
    }
    
    @Test
    public void testExistByCastellanId(){
    	// setup
    	Castellan sampleCastellan = prepareCastellan();
    	
    	// example
    	boolean existsCastellan = castellanEntity.existByCastellanId(sampleCastellan.getId());
    	
    	// verification
    	assertTrue(existsCastellan);
    }
    
    //--------------------------------------------------------------------------
    
	@Autowired CastellanEntity castellanEntity;
	@Autowired TownIdGenerator idGen; 
	@Autowired NationEntity nationEntity; 
	
	//--------------------------------------------------------------------------
	
	private Castellan prepareCastellan() {
		// 
		String nationId = idGen.generateNationId(); 
		Nation nation = NationHelper.getSampleNation(nationId); 
		nationEntity.create(nation);

		String castellanId = idGen.generateCastellanId(nationId);
		Castellan castellan = PeopleHelper.getSampleCastellan(nationId, castellanId, 1);
		castellanEntity.create(castellan); 
		return castellan; 
	}
	
}