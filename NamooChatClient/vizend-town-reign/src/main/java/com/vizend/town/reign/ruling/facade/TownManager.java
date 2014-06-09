package com.vizend.town.reign.ruling.facade;

import java.util.List;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.nation.shared.dto.TownCDto;
import com.vizend.town.reign.nation.shared.dto.TownRDto;

/**
 * TOWN Entity Interface
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 11. 21.
 */
public interface TownManager {
	//
	public String registTown(String metroId, TownCDto townCDto); 
	public TownRDto findTown(String townId);
	public List<TownRDto> findTownsInMetro(String metroId); 
	public void modifyTown(String townId, List<NameValue> pairs);
	public boolean hasTownName(String metroId, String townName);
}
