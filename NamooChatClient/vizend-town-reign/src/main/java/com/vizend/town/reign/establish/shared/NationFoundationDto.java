/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.establish.shared;

import java.io.Serializable;

import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.PublicServant;
import com.vizend.town.reign.shared.ProvisionMode;

/**
 * Information for founding a nation
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 12. 04.
 */
public class NationFoundationDto implements Serializable {
	//
	private static final long serialVersionUID = 2781179844045324830L;

	// nation info
	private String nationName;
	private String provisionModeStr;

	// root admin info
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private String adminPhoneNumber;

    //--------------------------------------------------------------------------
    // constructor

	public NationFoundationDto(
    		String nationName,
    		String provisionModeStr,
    		String adminName,
    		String adminEmail,
    		String adminPassword,
    		String adminPhoneNumber) {
    	//
    	this.nationName = nationName;
    	this.provisionModeStr = provisionModeStr;
    	this.adminName = adminName;
    	this.adminEmail = adminEmail;
    	this.adminPassword = adminPassword;
    	this.adminPhoneNumber = adminPhoneNumber;
    }

	public Nation createNation(String nationId) {
		//
		Nation nation = new Nation(nationId, nationName);
		if (provisionModeStr != null) {
			nation.setProvisionMode(ProvisionMode.valueOf(provisionModeStr)); 
		}
		
		return nation; 
	}

	public PublicServant createServant(String nationId) {
		//
		PublicServant servant = new PublicServant(nationId, adminName, adminEmail, adminPassword);
		servant.setPhoneNumber(adminPhoneNumber);

		return servant;
	}

	public Castellan createCastellan(String nationId, String castellanId) {
		//
		Castellan castellan = new Castellan(nationId, castellanId, adminName, adminEmail);
		castellan.setAuthPhone(adminPhoneNumber); 
		
		return castellan; 
	}

    //--------------------------------------------------------------------------
    // 2. getter/setter

	public String getNationName() {
		return nationName;
	}

	public String getProvisionModeStr() {
		return provisionModeStr;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

}
