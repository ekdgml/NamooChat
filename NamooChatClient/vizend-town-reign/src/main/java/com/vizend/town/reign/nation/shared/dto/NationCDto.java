package com.vizend.town.reign.nation.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.shared.ProvisionMode;

/**
 * Nation creation DTO
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 10. 20.
 */
public class NationCDto implements Serializable {
	//
	private static final long serialVersionUID = 4572294910444008359L;

	private String name;
	private String provisionModeStr;

    //--------------------------------------------------------------------------
    // 1. constructor

	protected NationCDto(){
	}

	public NationCDto(
    		String nationName,
    		String provisionModeStr) {
    	//
    	this.name = nationName;
    	this.provisionModeStr = provisionModeStr;
    }

	public NationCDto(Nation nation) {
		// 
		this.name = nation.getName();
		this.provisionModeStr = nation.getProvisionMode().name(); 
	}

	//--------------------------------------------------------------------------
	// 2. methods 
	
	public Nation createNation(String nationId) {
		//
		Nation nation = new Nation(nationId, name, ProvisionMode.valueOf(provisionModeStr));
		
		return nation;
	}

    //--------------------------------------------------------------------------
    // 3. getter/setter

	public String getName() {
		return name;
	}

	public String getProvisionModeStr() {
		return provisionModeStr;
	}
}
