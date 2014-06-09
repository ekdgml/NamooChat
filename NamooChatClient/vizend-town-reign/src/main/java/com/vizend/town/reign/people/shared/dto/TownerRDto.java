/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.people.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.people.domain.Towner;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 12. 20.
 */
public class TownerRDto implements Serializable{
	//
	private static final long serialVersionUID = 1655201469457932093L;
	
	//
	private String id;
	private String citizenId;
	private Long registDate;
	private String townId;

	//--------------------------------------------------------------------------
	// 1. private constructor

	private TownerRDto(Towner towner){
		//
		this.citizenId = towner.getCitizen().getId();
		this.id = towner.getId();
		this.registDate = towner.getRegistDate();
		this.townId = towner.getTownId();
	}

	//--------------------------------------------------------------------------
	// 2. DTO createion from a domain object

	public static TownerRDto createDto(Towner towner){
		//
		return new TownerRDto(towner);
	}

	public static List<TownerRDto> createDtos(List<Towner> residencePermitList){
		//
		List<TownerRDto> dtos = new ArrayList<TownerRDto>();
		for(Towner permit : residencePermitList){
			dtos.add(TownerRDto.createDto(permit));
		}
		return dtos;
	}

	//--------------------------------------------------------------------------
	// 3. getter/setter

	public String getCitizenId() {
		return citizenId;
	}

	public String getId() {
		return id;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public String getTownId() {
		return townId;
	}

}
