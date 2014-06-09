/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.nation.facade;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.vizend.dream.test.util.SpringDbUnitTestTemplate;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.shared.helper.NationHelper;

/**
 * Metro entity test 
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 11. 22.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class MetroEntityTest extends SpringDbUnitTestTemplate {
    //
    @Test
    public void testCreateMetro(){
    	// 
    	Metro resultMetro = metroEntity.retrieve(sampleMetro.getId());
    	assertThat(resultMetro, is(not(nullValue())));
    }
     
    @Test
    public void testRetrieveByNationId(){
    	// example
    	List<Metro> metros = metroEntity.retrieveByNationId(sampleMetro.getNation().getId());
    	
    	// verification
    	assertThat(metros, is(not(nullValue())));
    	assertThat(metros.size(), is(1));
    }
    
    @Test
    public void testRetrieveByNameLike(){
    	//
    	String nameLike = "Next";
    	
    	// example
    	List<Metro> metros = metroEntity.retrieveByNameLike(sampleMetro.getNation().getId(), nameLike);
    	
    	// verification
    	assertThat(metros, is(not(nullValue())));
    	assertThat(metros.size(), is(1));
    }
    
    @Test
    public void testUpdate(){
    	//
    	Metro metro = metroEntity.retrieve(sampleMetro.getId());
    	
    	// example
    	String newName = "New Nextree Metro";
    	metro.setName(newName);
    	metroEntity.update(metro);
    	
    	// verification
    	assertThat(metro.getName(), is(newName));
    }
    
    @Test
    public void testDeleteMetro(){
    	// 
    	Metro metro = metroEntity.retrieve(sampleMetro.getId());
    	assertThat(metro, is(not(nullValue())));
    	
    	// example
    	metroEntity.delete(metro);
    	
    	// verification
    	try {
    		metroEntity.retrieve(sampleMetro.getId());
			assertTrue(false); 
    	} catch (Exception e) {
    		assertTrue(true); 
    	}
    }
    
    @Test
    @Transactional
    public void testCountByNationId(){
    	// example
    	int result = metroEntity.countByNationId(sampleMetro.getNation().getId());
    	
    	// verification
    	assertThat(result, is(1));
    }
    
    @Test
    public void testCountByNameLike(){
    	// 
    	String nameLike = "Nextree";
    	
    	// example
    	int count = metroEntity.countByNameLike(sampleMetro.getNation().getId(), nameLike);
    	
    	// verification
    	assertThat(count, is(1));
    }
	
	//--------------------------------------------------------------------------
	
    @Autowired MetroEntity metroEntity;
    @Autowired NationEntity nationEntity;
    @Autowired TownIdGenerator idGen; 
    
    private Metro sampleMetro;
    
    //--------------------------------------------------------------------------
    
    @Before
    public void prepareMetro() {
    	// 
    	String nationId = idGen.generateNationId();
    	Nation sampleNation = NationHelper.getSampleNation(nationId); 
    	nationEntity.create(sampleNation); 
    	
    	String metroId = idGen.generateMetroId(sampleNation.getId()); 
    	Metro metro = NationHelper.getSampleMetro(sampleNation, metroId, 1); 
    	metroEntity.create(metro); 
    	
    	sampleMetro = metro;
    }
    
}