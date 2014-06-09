package com.vizend.town.reign.people.domainbackup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.gson.Gson;
import com.vizend.town.reign.people.domain.Citizen;

/**
 * EnrollmentDto
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class CitizenBackup {
	//
	@Id
	@Column(name = "CITIZEN_ID")
	private String id;	// NNNM-ABCDE

	@Column
	private String loginId; 
	
	@Column(nullable = false, length=3000)
	private String json;

	//--------------------------------------------------------------------------
	// constructor

	public CitizenBackup() {
	}

	//--------------------------------------------------------------------------
	// methods

	public CitizenBackup(Citizen citizen) {
		//
		Gson gson = new Gson(); 
		
		this.id = citizen.getId();
		this.loginId = citizen.getLoginId(); 
		
		this.json = gson.toJson(citizen); 
	}

	public Citizen getCitizen () {
		// 
		Gson gson = new Gson(); 

		return gson.fromJson(json, Citizen.class); 
	}

	//--------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getJson() {
		return json;
	}
}