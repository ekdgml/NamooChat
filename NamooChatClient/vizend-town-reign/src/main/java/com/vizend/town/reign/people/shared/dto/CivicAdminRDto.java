/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.people.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.people.domain.CivicAdmin;


/**
 * CivicAdminRDto
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 24.
 */
public class CivicAdminRDto extends CivicAdminCDto{
	//
	private static final long serialVersionUID = 7648358232067933635L;
	
	//
	private String id;
    private Long registDate;
 
    //--------------------------------------------------------------------------
    // 0. default public constructor.

    public CivicAdminRDto(){
    }

    //--------------------------------------------------------------------------
    // 1. private constructor.

    private CivicAdminRDto(CivicAdmin civicAdmin){
    	//
    	super(civicAdmin); 
    	
    	this.id = civicAdmin.getId(); 
    	this.registDate = civicAdmin.getRegistDate(); 
    }

    //--------------------------------------------------------------------------
    // 2. DTO createn from a domain object

    public static CivicAdminRDto createDto(CivicAdmin civicAdmin) {
    	//
    	return new CivicAdminRDto(civicAdmin);
    }

    public static List<CivicAdminRDto> createDtos(List<CivicAdmin> civicAdmins) {
        //
        List<CivicAdminRDto> dtos = new ArrayList<CivicAdminRDto>();
        for (CivicAdmin civicAdmin : civicAdmins) {
        	dtos.add(createDto(civicAdmin));
        }

        return dtos;
    }

    //--------------------------------------------------------------------------
    // 3. check and prepare the collections

    //--------------------------------------------------------------------------
    // 4. getter/setter

    public Long getRegistDate() {
    	return registDate;
    }
    
    public String getId() {
    	return id; 
    }
}
