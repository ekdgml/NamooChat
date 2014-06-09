package com.vizend.town.reign.ruling.facade;

import java.util.List;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.nation.shared.dto.MetroCDto;
import com.vizend.town.reign.nation.shared.dto.MetroRDto;

/**
 * Metro CRUD
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 21.
 */
public interface MetroManager {
	//
	public String registMetro(String nationId, MetroCDto metroCDto);
	public MetroRDto findMetro(String metroId);
	public List<MetroRDto> findMetrosInNation(String nationId);
	public void modifyMetro(String metroId, List<NameValue> pairs);
	public void remove(String metroId);
}
