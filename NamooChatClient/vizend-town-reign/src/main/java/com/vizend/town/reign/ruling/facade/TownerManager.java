package com.vizend.town.reign.ruling.facade;

import java.util.List;

import com.vizend.town.reign.people.shared.dto.TownerRDto;

public interface TownerManager {
	//
	public String registTowner(String townId, String citizenId);
	public TownerRDto findTowner(String townerId);
	public List<TownerRDto> findTownersByTownId(String townId, int offset, int pageSize); 
	public List<TownerRDto> findTownersByCitizenId(String citizenId);
}