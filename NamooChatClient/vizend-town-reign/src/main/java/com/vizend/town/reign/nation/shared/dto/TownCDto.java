package com.vizend.town.reign.nation.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.domain.TownType;

/**
 * Town create DTO 
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 12. 04.
 */
public class TownCDto implements Serializable {
	//
	private static final long serialVersionUID = 6375474107412762675L;

	// metro info
	private String name;
	private TownType townType; 
	private boolean defaultTown;

    //--------------------------------------------------------------------------
    // constructor

	public TownCDto(){
	}

	public TownCDto(
    		String townName,
    		boolean defaultTown, 
    		TownType townType) {
    	//
		this.name = townName;
		this.defaultTown = defaultTown;
		this.townType = townType; 
	}
	
	public TownCDto(Town town) {
		// 
		this.name = town.getName(); 
		this.defaultTown = town.isDefaultTown(); 
		this.townType = town.getTownType(); 
	}
	
	//--------------------------------------------------------------------------
	// 2. methods

	public Town createTown(Metro metro, String townId) {
		//
		Town town = new Town(metro, townId, name, defaultTown, townType); 

		return town;
	}

    //--------------------------------------------------------------------------
    // 3. getter/setter

	public boolean isDefaultTown() {
		return defaultTown;
	}

	public String getName() {
		return name;
	}

	public TownType getTownType() {
		return townType;
	}
}
