package com.vizend.town.reign.people.shared.helper;

import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domain.Towner;

/**
 * Test data helper for people 
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 04. 22.
 */
public class PeopleHelper {
	//
	public static Castellan getSampleCastellan(String nationId, String castellanId, int count) {
		//
		String displayName = "[" + count + "]" + "Song,Taegook"; 
		String authEmail = "tsong" + count + "@nextree.co.kr"; 
		String authPhone = "010-9999-1111"; 
		String photoId = null; 
		
		Castellan sample = new Castellan(nationId, castellanId, displayName, authEmail); 
		sample.setAuthPhone(authPhone); 
		sample.setPhotoId(photoId); 
		
		return sample; 
	}
	
	public static Citizen getSampleCitizen(String metroId, Castellan castellan, String citizenId, int count) {
		//
		int loginIdInt = 00000 + count; 
		String loginId = String.valueOf(loginIdInt); 	// GsId, Group specified Id
		String password = "123456"; 

		Citizen sample = new Citizen(
				castellan, 
				metroId, 
				citizenId, 
				loginId); 
		
		sample.setPassword(password); // no encryption in sample
		
		return sample; 
	}
	
	public static Towner getSampleTowner(String townId, Citizen citizen, int count) {
		// 
		String positionCode = "VP"; 
		String empStatusCode = "FullTime";

		Towner sample = new Towner(citizen, townId); 
		sample.setPositionCode(positionCode); 
		sample.setEmpStatusCode(empStatusCode); 
		
		return sample; 
	}
}