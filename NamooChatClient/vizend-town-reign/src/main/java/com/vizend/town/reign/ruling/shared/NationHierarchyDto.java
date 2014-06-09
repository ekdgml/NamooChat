package com.vizend.town.reign.ruling.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.nation.domain.Nation;

/**
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @date 2013. 1. 19.
 */
public class NationHierarchyDto implements Serializable{
	//
	private static final long serialVersionUID = 8064377931483194247L;

	private String nationId;
	private String nationName;

	private List<MetroHierarchyDto> metroHirDtos;

	//--------------------------------------------------------------------------
	// constructor

	private NationHierarchyDto(Nation nation){
		//
		this.nationId = nation.getId();
		this.nationName = nation.getName();
	}

	//--------------------------------------------------------------------------
	// create dto

	public static NationHierarchyDto createDto(Nation nation) {
		//
		return new NationHierarchyDto(nation);
	}

	//--------------------------------------------------------------------------
	// methods

	public void addMetroHirDto(MetroHierarchyDto metroHirDto){
		//
		if(metroHirDtos == null){
			metroHirDtos = new ArrayList<MetroHierarchyDto>();
		}
		metroHirDtos.add(metroHirDto);
	}

	//--------------------------------------------------------------------------
	// gettters/setters

	public String getNationId() {
		return nationId;
	}

	public String getNationName() {
		return nationName;
	}

	public List<MetroHierarchyDto> getMetroHirDtos() {
		return metroHirDtos;
	}

}
