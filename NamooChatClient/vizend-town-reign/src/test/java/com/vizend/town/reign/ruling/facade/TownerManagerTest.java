package com.vizend.town.reign.ruling.facade;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.facade.TownEntity;
import com.vizend.town.reign.nation.shared.helper.NationHelper;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.TownerEntity;
import com.vizend.town.reign.people.shared.dto.TownerRDto;
import com.vizend.town.reign.people.shared.helper.PeopleHelper;
 
/**
 *
 * @author <a href="mailto:vaslife@gmail.com">ahn, young-su</a>
 * @since Apr 19, 2013
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class TownerManagerTest extends SpringDbUnitTestTemplate {
	//
	@Autowired
	private TownerManager townerManager;
	
	@Autowired
	private TownIdGenerator idGen; 
	
	@Autowired NationEntity nationEntity; 
	@Autowired CastellanEntity castellanEntity; 
	@Autowired MetroEntity metroEntity; 
	@Autowired CitizenEntity citizenEntity; 
	@Autowired TownEntity townEntity; 
	@Autowired TownerEntity townerEntity; 

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
    	
    	String townId2 = idGen.generateTownId(metroId); 
    	Town town2 = NationHelper.getSampleTown(metro, townId2, 2);
    	townEntity.create(town2); 

    	Towner towner = PeopleHelper.getSampleTowner(townId, citizen, 1);
    	townerEntity.create(towner); 
 
    	Towner towner2 = PeopleHelper.getSampleTowner(townId2, citizen, 2);
    	townerEntity.create(towner2); 

    	return towner; 
    }

	//--------------------------------------------------------------------------

    @Transactional
	@Test
	public void testFindTownerByCitizenId(){
		// setup
    	Towner sampleTowner = prepareTowner(); 
		
		// example
		List<TownerRDto> townerDtos = townerManager.findTownersByCitizenId(sampleTowner.getCitizen().getId());
		
		// verification
		assertThat(townerDtos.size(), is(2));
	}
	
    @Transactional
	@Test
	public void testFindTownerByTownId(){
		// setup
    	Towner sampleTowner = prepareTowner(); 
		int offset = 0; 
		int pageSize = 10; 
		
		// example
		List<TownerRDto> townerDtos = 
				townerManager.findTownersByTownId(sampleTowner.getTownId(), offset, pageSize);
		
		// verification
		assertThat(townerDtos.size(), is(1));
	}
    
}
