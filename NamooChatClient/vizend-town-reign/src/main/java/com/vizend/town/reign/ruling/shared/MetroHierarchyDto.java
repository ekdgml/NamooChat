package com.vizend.town.reign.ruling.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.nation.domain.Metro;

/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @date 2013. 1. 19.
 */
public class MetroHierarchyDto implements Serializable{
	//
	private static final long serialVersionUID = 3521489528032326802L;

	private String metroId;
	private String metroName;

	private List<TownHierarchyDto> townHirDtos;

	//--------------------------------------------------------------------------
	// constructor

	private MetroHierarchyDto(Metro metro){
		//
		this.metroId = metro.getId();
		this.metroName = metro.getName();
	}

	//--------------------------------------------------------------------------
	// create dto

	public static MetroHierarchyDto createDto(Metro metro) {
		//
		return new MetroHierarchyDto(metro);
	}

	//--------------------------------------------------------------------------
	// methods

	public void addTownHirDto(TownHierarchyDto townHirDto) {
		//
		if(townHirDtos == null){
			townHirDtos = new ArrayList<TownHierarchyDto>();
		}
		townHirDtos.add(townHirDto);
	}

	//--------------------------------------------------------------------------
	// getters/setters

	public String getMetroId() {
		return metroId;
	}

	public String getMetroName() {
		return metroName;
	}

	public List<TownHierarchyDto> getTownHirDtos() {
		return townHirDtos;
	}

}
