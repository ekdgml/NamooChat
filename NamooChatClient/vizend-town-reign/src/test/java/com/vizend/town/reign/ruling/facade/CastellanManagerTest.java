/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
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
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.shared.dto.CastellanCDto;
import com.vizend.town.reign.people.shared.dto.CastellanRDto;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;

/**
 *
 * @author <a href="mailto:syhan@nextree.co.kr">Seongyoung,Han</a>
 * @since 2013. 1. 27.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class CastellanManagerTest extends SpringDbUnitTestTemplate {
	//
	@Test
	public void testRegistCastellan() {
		// verification
		Castellan sampleCastellan = prepareCastellan(); 
		
		CastellanCDto castellanCDto = new CastellanCDto(
				sampleCastellan.getDisplayName(), 
				sampleCastellan.getAuthEmail());
		String castellanId = castellanManager.registCastellan(sampleCastellan.getNationId(), castellanCDto);
		CastellanRDto castellanDto = castellanManager.findCastellan(castellanId);

		assertThat(castellanDto.getNationId(), is(sampleCastellan.getNationId()));
		assertThat(castellanDto.getDisplayName(), is(sampleCastellan.getDisplayName()));
    	assertThat(castellanDto.getAuthEmail(), is(sampleCastellan.getAuthEmail()));
	}

	@Test
	public void testFindCastellanByAuthEmail() {
		// setup
		Castellan sampleCastellan = prepareCastellan(); 

		// examination
		CastellanRDto castellanDto = castellanManager.findCastellanByAuthEmail(sampleCastellan.getAuthEmail());

		// verification
		assertThat(castellanDto.getNationId(), is(sampleCastellan.getNationId()));
		assertThat(castellanDto.getDisplayName(), is(sampleCastellan.getDisplayName()));
    	assertThat(castellanDto.getAuthEmail(), is(sampleCastellan.getAuthEmail()));
	}
	
	@Test
	public void testFindCastellanByAuthPhone() {
		// setup
		Castellan sampleCastellan = prepareCastellan(); 
		
		// examination
		CastellanRDto castellanDto = castellanManager.findCastellanByAuthPhone(sampleCastellan.getAuthPhone());
		
		// verification
		assertThat(castellanDto.getNationId(), is(sampleCastellan.getNationId()));
		assertThat(castellanDto.getDisplayName(), is(sampleCastellan.getDisplayName()));
		assertThat(castellanDto.getAuthPhone(), is(sampleCastellan.getAuthPhone()));
	}

	@Test
	public void testModifyCastellan() {
		// setup
		Castellan sampleCastellan = prepareCastellan(); 

		final String displayNameModified = "Bill Gates";
		final String authEmailModified = "bill@nextree.co.kr";

		// examination
		List<NameValue> pairs = new ArrayList<NameValue>();
		pairs.add(new NameValue("displayName", displayNameModified));
		pairs.add(new NameValue("authEmail", authEmailModified));
		castellanManager.modifyCastellan(sampleCastellan.getId(), pairs);

		// verification
		CastellanRDto castellanDto = castellanManager.findCastellan(sampleCastellan.getId());
		assertThat(castellanDto.getDisplayName(), is(displayNameModified));
		assertThat(castellanDto.getAuthEmail(), is(authEmailModified));
	}
	
	@Test
	public void testRemoveCastellan(){
		// setup
		Castellan sampleCastellan = prepareCastellan();
		
		CastellanRDto targetCastellan = castellanManager.findCastellan(sampleCastellan.getId());
		assertNotNull(targetCastellan);
		
		// examination
		// FIXME Castellan 삭제 정책이 모호합니다.
		castellanManager.removeCastellan(targetCastellan.getId());
		
		// verification
		try {
			castellanManager.findCastellan(targetCastellan.getId());
			assertTrue(false);
		} catch (VizendTownException e) {
			//
			assertTrue(true);
		}
		
	}

	@Test
	public void testExistByAuthEmail() {
		// setup
		Castellan sampleCastellan = prepareCastellan(); 

		//verification
		assertTrue(castellanManager.existByAuthEmail(sampleCastellan.getAuthEmail()));
	}
	
	//--------------------------------------------------------------------------
	
	@Autowired CastellanManager castellanManager;
	@Autowired CastellanEntity castellanEntity; 
	@Autowired NationEntity nationEntity; 
	@Autowired TownIdGenerator idGen; 

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
