package com.vizend.town.reign.ruling.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vizend.town.reign.people.domain.PublicServant;
import com.vizend.town.reign.people.facade.ServantEntity;
import com.vizend.town.reign.people.shared.dto.ServantRDto;
import com.vizend.town.reign.ruling.facade.ServantManager;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 27.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ServantManagerLogic implements ServantManager {

	@Autowired
    private ServantEntity servantEntity;

    //--------------------------------------------------------------------------
    // PublicServant

    @Override
    public ServantRDto findServantByEmail(String email) {
    	//
    	PublicServant publicServant = servantEntity.retrieveByEmail(email);
    	return ServantRDto.createDto(publicServant);
    }

    @Override
    public ServantRDto findAdminServant(String authorityId) {
    	//
    	PublicServant servant = servantEntity.retrievePrimaryAdminServant(authorityId);
    	return ServantRDto.createDto(servant);
    }

}
