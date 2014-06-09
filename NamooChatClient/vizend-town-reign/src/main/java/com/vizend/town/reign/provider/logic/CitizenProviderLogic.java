/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.provider.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vizend.dream.util.type.IdName;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.facade.CitizenEntity;
import com.vizend.town.reign.provider.facade.CitizenProvider;

/**
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 22.
 */
@Repository
public class CitizenProviderLogic implements CitizenProvider {
	//
	@Autowired
	private CitizenEntity citizenEntity;

	//--------------------------------------------------------------------------
	// methods


	@Override
	public IdName getCitizenIdName(String citizenId) {
		//
		Citizen citizen = citizenEntity.retrieve(citizenId);
		
		return new IdName(citizen.getId(), citizen.getLoginName()); 
	}

	//
//	@Override
//	public Inep findCitizenInep(String citizenId) {
//		//
//		return citizenEntity.retrieveInep(citizenId);
//	}
//
//	@Override
//	public InepList findCitizensInep(List<String> citizenIds) {
//		//
//		return  citizenEntity.retrieveIneps(citizenIds);
//	}
//
//
//	@Override
//	public IdNameList requestUserNames(String nameLike) {
//		//
//		String loginNameLike = nameLike + "%";
//		return citizenEntity.retrieveIdNamesByName(loginNameLike);
//	}
//
//	@Override
//	public int countCitizensByKeyword(PageSearchKey keyword) {
//		//
//		return citizenEntity.countByKeyword(keyword);
//	}
//
//	@Override
//	public List<Inep> fetchCitizenInepByKeyword(PageSearchKey pageSearchKey) {
//		//
//		return citizenEntity.fetchInepsBySearchKey(pageSearchKey);
//	}
}