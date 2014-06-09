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
import com.vizend.town.reign.nation.facade.MetroEntity;
import com.vizend.town.reign.nation.facade.NationEntity;
import com.vizend.town.reign.nation.shared.dto.MetroRDto;
import com.vizend.town.reign.nation.shared.helper.NationHelper;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 21.
 */
@Transactional
@DatabaseSetup("/com/vizend/town/reign/sequencedataset.xml")
public class MetroManagerTest extends SpringDbUnitTestTemplate {

    @Autowired
    private MetroManager metroManager;
    
    @Autowired
    private TownIdGenerator idGen; 

    @Autowired private NationEntity nationEntity; 
    @Autowired private MetroEntity metroEntity; 
    
    //--------------------------------------------------------------------------
    private Metro prepareMetro() {
    	// 
    	String nationId = idGen.generateNationId();
    	Nation sampleNation = NationHelper.getSampleNation(nationId); 
    	nationEntity.create(sampleNation); 
    	
    	String metroId = idGen.generateMetroId(sampleNation.getId()); 
    	Metro metro = NationHelper.getSampleMetro(sampleNation, metroId, 1); 
    	metroEntity.create(metro); 
    	
    	return metro; 
    }
    
    //--------------------------------------------------------------------------

    @Test
    public void testFindMetro(){
    	// examination
    	Metro sampleMetro = prepareMetro(); 
     	
    	MetroRDto metroDto = metroManager.findMetro(sampleMetro.getId());

    	// verification.
    	assertThat(metroDto.getName(), is(sampleMetro.getName()));
    	assertThat(metroDto.getMetroId(), is(sampleMetro.getId()));
    }

    @Test
    public void testFindNationMetors(){
    	//
    	Metro sampleMetro = prepareMetro(); 
    	String nationId = sampleMetro.getNation().getId(); 
    	
    	// examination
    	List<MetroRDto> metroDtos = metroManager.findMetrosInNation(nationId);

    	// verification
    	assertThat(metroDtos.size(), is(1));
    }

    @Test
    public void testModifyMetro(){
    	// setup
    	Metro sampleMetro = prepareMetro(); 

    	String newMetroName = "NewSoft Metro";

    	List<NameValue> values = new ArrayList<NameValue>();
    	values.add(new NameValue("name", newMetroName));

    	// examination
    	metroManager.modifyMetro(sampleMetro.getId(), values);

    	// verificaton
    	MetroRDto modified = metroManager.findMetro(sampleMetro.getId());
    	assertThat(modified.getName(), is(newMetroName));
    }

    @Test
    public void testRemoveMetro(){
    	// setup
    	Metro sampleMetro = prepareMetro(); 

    	MetroRDto metroDto = metroManager.findMetro(sampleMetro.getId());
    	assertNotNull(metroDto);

    	// examination
    	metroManager.remove(sampleMetro.getId());

    	// verification
    	try {
			metroDto = metroManager.findMetro(sampleMetro.getId());
			assertTrue(false); 
		} catch (Exception e) {
			assertTrue(true); 
		}
    }
    
}