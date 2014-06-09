package com.vizend.town.reign.nation.shared.dto;

import java.util.ArrayList;
import java.util.List;

import com.vizend.town.reign.nation.domain.Town;

/**
 * Town read DTO 
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 11. 27.
 */
public class TownRDto extends TownCDto {

	private static final long serialVersionUID = 7273898563554731852L;

	private String metroId;
	private String townId;
	private String intro; 
	private String flagImageId;
	private Long registDate;

	//--------------------------------------------------------------------------
	// 0. default public constructor

	public TownRDto(){
	}

	//--------------------------------------------------------------------------
	// 1. public constructor

	public TownRDto(Town town){
		//
		super(town); 
		
		this.metroId = town.getMetro().getId();
		this.townId = town.getId();
		this.intro = town.getIntro(); 
		this.flagImageId = town.getFlagImageId();
		this.registDate = town.getRegistDate();	
	}

	//--------------------------------------------------------------------------
	// 2. DTO creation form a domain object

	public static TownRDto createRDto(Town town) {
		//
		return new TownRDto(town);
	}

	public static List<TownRDto> createDtos(List<Town> towns) {
		//
		List<TownRDto> townDtos = new ArrayList<TownRDto>();
		for (Town town : towns) {
			townDtos.add(createRDto(town));
		}

		return townDtos;
	}

	//--------------------------------------------------------------------------
	// 3. getter/setter

	public String getIntro() {
		return intro; 
	}
	
	public String getMetroId() {
		return metroId;
	}

	public String getTownId() {
		return townId;
	}

	public String getFlagImageId() {
		return flagImageId;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public void setMetroId(String metroId) {
		this.metroId = metroId;
	}
}
