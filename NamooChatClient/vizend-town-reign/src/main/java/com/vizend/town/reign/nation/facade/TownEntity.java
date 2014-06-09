package com.vizend.town.reign.nation.facade;

import java.util.List;

import com.vizend.town.reign.nation.domain.Town;

/**
 * Town entity interface  
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 10. 20.
 */
public interface TownEntity {
	//
	public String create(Town town);
	public Town retrieve(String townId);
	public List<Town> retrieveByMetroId(String metroId);
	public List<Town> retrieveByNameLike(String metroId, String nameLike);
	public void update(Town town);
	public void delete(String townId);
	public void delete(Town town);
	public boolean existByTownName(String metroId, String townName);
	public boolean existDefaultTown(String metroId);
	public int countByMetroId(String metroId);
}
