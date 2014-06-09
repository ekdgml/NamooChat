package com.vizend.town.reign.nation.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 18, 2013
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class NationEntityTest extends SpringDbUnitTestTemplate {
	//
	@Test
	public void testCreate() {
		// setup
		Nation sampleNation = prepareNation();  
		
		// verification
		Nation resultNation = nationEntity.retrieve(sampleNation.getId());
		assertThat(resultNation, is(not(nullValue())));
	}
	
	@Test
	public void testRetrieveByMetroId(){
		// setup
		Nation nation = prepareNation();
		Metro metro = prepareMetro(nation);

		// verification
		Nation nationResult = nationEntity.retrieveByMetroId(metro.getId());
		assertNotNull(nationResult);
		assertThat(nationResult.getName(), is(nation.getName()));
	}
	
	@Test
	public void testUpdate(){
		// setup
		Nation sampleNation = prepareNation();  
		
		String newName = "NewSoft Nation";
		
		// example
		Nation modifiedNation = nationEntity.retrieve(sampleNation.getId());
		modifiedNation.setName(newName);
		nationEntity.update(modifiedNation);
		
		// verification
		Nation resultNation = nationEntity.retrieve(sampleNation.getId());
		assertThat(resultNation, is(not(nullValue())));
		assertThat(resultNation.getName(), is(newName));
	}

	@Test
	public void testDelete(){
		// setup
		Nation sampleNation = prepareNation();  

		Nation targetNation = nationEntity.retrieve(sampleNation.getId());
		assertThat(targetNation, is(not(nullValue())));
		
		// example
		nationEntity.delete(targetNation);
		
		// verification
		try {
			nationEntity.retrieve(sampleNation.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			// 
			assertTrue(true); 
		}
	}
	
	@Test
	public void testDeleteByNationId(){
		// setup
		Nation sampleNation = prepareNation();  
		
		Nation targetNation = nationEntity.retrieve(sampleNation.getId());
		assertThat(targetNation, is(not(nullValue())));
		
		// example
		nationEntity.delete(targetNation.getId());
		
		// verification
		try {
			nationEntity.retrieve(sampleNation.getId());
			assertTrue(false); 
		} catch (VizendTownException e) {
			// 
			assertTrue(true); 
		}
	}
	
	@Test
	public void testExistByNationId(){
		// setup
		Nation nation = prepareNation();
		
		// verification
		boolean nationExists = nationEntity.existsByNationId(nation.getId());
		assertTrue(nationExists);
	}
	
	//--------------------------------------------------------------------------
	
	@Autowired NationEntity nationEntity;
	@Autowired TownIdGenerator idGen; 
	@Autowired MetroEntity metroEntity;
	
	private Metro prepareMetro(Nation nation){
		//
		String metroId = idGen.generateMetroId(nation.getId());
		int metroCount = 1;
		Metro targetMetro = NationHelper.getSampleMetro(nation, metroId, metroCount);
		metroEntity.create(targetMetro);
		
		Metro resultMetro = metroEntity.retrieve(targetMetro.getId());
		assertNotNull(resultMetro);
		
		return resultMetro;
	}
	
	private Nation prepareNation() {
		//
		String nationId = idGen.generateNationId(); 
		Nation nation = NationHelper.getSampleNation(nationId); 
		nationEntity.create(nation);
		
		Nation resultNation = nationEntity.retrieve(nation.getId());
		assertNotNull(resultNation);
		
		return resultNation; 
	}
	
}