package com.vizend.town.reign.ruling.logic;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdGenerator;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.facade.CastellanEntity;
import com.vizend.town.reign.people.shared.dto.CastellanCDto;
import com.vizend.town.reign.people.shared.dto.CastellanRDto;
import com.vizend.town.reign.ruling.facade.CastellanManager;

/**
 * RecycleBin manager logic, process component
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 5. 10.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CastellanManagerLogic implements CastellanManager {
    //
    @Autowired
    private CastellanEntity castellanEntity;

    @Autowired
    private TownIdGenerator townIdGenerator;

    //--------------------------------------------------------------------------
    // method

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String registCastellan(String nationId, CastellanCDto castellanCDto) {
    	//
        String castellanId = townIdGenerator.generateCastellanId(nationId);
        Castellan castellan = castellanCDto.createDomain(nationId, castellanId);

        castellanEntity.create(castellan);
        return castellan.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CastellanRDto findCastellan(String castellanId) {
    	//
    	Castellan castellan = castellanEntity.retrieve(castellanId);

    	CastellanRDto castellanRDto = CastellanRDto.createRDto(castellan);

    	return castellanRDto;
    }



	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CastellanRDto findCastellanByAuthEmail (String authEmail) {
    	//
    	Castellan castellan = castellanEntity.retrieveByAuthEmail(authEmail);

    	CastellanRDto castellanRDto = CastellanRDto.createRDto(castellan);

    	return castellanRDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CastellanRDto findCastellanByAuthPhone (String authPhone) {
    	//
    	Castellan castellan = castellanEntity.retrieveByAuthPhone(authPhone);

    	CastellanRDto castellanRDto = CastellanRDto.createRDto(castellan);
 
    	return castellanRDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyCastellan(String castellanId, List<NameValue> pairs) {
    	//
    	Castellan castellan = castellanEntity.retrieve(castellanId);
    	castellan.setValues(pairs);

    	castellanEntity.update(castellan);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
	public void removeCastellan(String castellanId) { 
    	// REVIEWME
    	Castellan castellan = castellanEntity.retrieve(castellanId);
    	castellan.setDeleted(true); 
    	castellanEntity.update(castellan); 
    }

    @Override
    public boolean existByAuthEmail(String authEmail) {
    	//
    	if(StringUtils.isBlank(authEmail)){
    		return false;
    	}
    	return castellanEntity.existByAuthEmail(authEmail);
    }

	@Override
	public boolean existByAuthPhone(String authPhone) {
		//
		if(StringUtils.isBlank(authPhone)){
			return false;
		}
		return castellanEntity.existByAuthPhone(authPhone);
	}

}
