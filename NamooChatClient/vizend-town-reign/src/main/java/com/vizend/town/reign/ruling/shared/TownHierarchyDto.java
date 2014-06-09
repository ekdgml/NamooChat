package com.vizend.town.reign.ruling.shared;

import java.io.Serializable;

import com.vizend.town.reign.nation.domain.Town;

/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @date 2013. 1. 19.
 */
public class TownHierarchyDto implements Serializable {
	//
	private static final long serialVersionUID = -1885545141440986387L;

	private String townName;
	private String townId;

	private TownHierarchyDto(Town town){
		//
		this.townName = town.getName();
		this.townId = town.getId();
	}

	public static TownHierarchyDto createDto(Town town) {
		//
		return new TownHierarchyDto(town);
	}

	public String getTownName() {
		return townName;
	}

	public String getTownId() {
		return townId;
	}
}
