package com.vizend.town.reign.people.facade;

import java.util.List;

import com.vizend.town.reign.people.domain.Towner;

public interface TownerEntity{
	//
	public String create(Towner towner);
	public Towner retrieve(String townerId);
	public Towner retrieve(String townId, String citizenId);
	public List<Towner> retrieveByCitizenId(String citizenId);
	public List<Towner> retrieveByTownId(String townId, int offset, int pageSize);
	public void delete(Towner towner);
	public void deleteByCitizenId(String citizenId);
	public int countByTownId(String townId);
}