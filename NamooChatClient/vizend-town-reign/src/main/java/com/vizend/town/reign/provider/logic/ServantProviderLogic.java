/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.provider.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.PublicServant;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.people.facade.CivicAdminEntity;
import com.vizend.town.reign.people.facade.ServantEntity;
import com.vizend.town.reign.provider.facade.ServantProvider;
import com.vizend.town.reign.shared.CivicUnit;

/**
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 22.
 */
@Repository
public class ServantProviderLogic implements ServantProvider {
	//
	@Autowired
	private CitizenEntity citizenEntity;

	@Autowired
	private ServantEntity servantEntity;

	@Autowired
	private CivicAdminEntity civicAdminEntity;

	//--------------------------------------------------------------------------
	//  constructor

	@Override
	@Deprecated
	public boolean identifyTownAdmin(String loginId) {
		//
		try {
			Citizen citizen = citizenEntity.retrieveByLoginId(loginId);
			PublicServant servant = servantEntity.retrieveByCastellanId(citizen.getCastellan().getId());
			return civicAdminEntity.identifyAdmin(servant.getLoginEmail(), CivicUnit.Town);

		} catch (VizendTownException e) {
			return false;
		}
	}
}