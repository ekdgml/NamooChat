package com.vizend.town.reign.nation.shared.dto;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.enumtype.PidProperty;
import com.vizend.dream.util.enumtype.UserIdType;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.MetroPolicy;
import com.vizend.town.reign.nation.domain.Nation;

public class MetroCDto implements Serializable {

	private static final long serialVersionUID = 4668234763314578970L;

	// metro info
	private String name;
	private String userIdTypeStr;

    //--------------------------------------------------------------------------
    // constructor

	public MetroCDto(){
	}

	public MetroCDto(
    		String metroName,
    		String userIdTypeStr) {
    	//
    	this.name = metroName;
    	this.userIdTypeStr = userIdTypeStr;
    }

	public MetroCDto(Metro metro) {
		// 
		this.name = metro.getName(); 
		this.userIdTypeStr = metro.getPolicy().getUserIdType().name(); 
	}
	
	public Metro createMetro(Nation nation, String metroId) {
		//
		MetroPolicy metroPolicy = null; 
		
		if(StringUtils.isBlank(userIdTypeStr)){
			metroPolicy = new MetroPolicy(); 
		} else {
			metroPolicy = new MetroPolicy(UserIdType.valueOf(userIdTypeStr), PidProperty.Email); 
		}
		
		return new Metro(nation, metroId, name, metroPolicy);
	}

    //--------------------------------------------------------------------------
    // 2. getter/setter

	public String getName() {
		return name;
	}

	public String getUserIdTypeStr() {
		return userIdTypeStr;
	}
}
