package com.vizend.town.reign.ruling.facade;

import java.util.List;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.nation.shared.dto.NationCDto;
import com.vizend.town.reign.nation.shared.dto.NationRDto;
import com.vizend.town.reign.ruling.shared.NationHierarchyDto;

/**
 * Nation manager
 * - No remove
 *
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 27.
 */
public interface NationManager {
	//
	public String buildNation(NationCDto nationCDto);
	public NationRDto findNation(String nationId);
	public NationRDto findNationOfTown(String townId);
	public NationRDto findParentNation(String metroId);
	public void modify(String nationId, List<NameValue> pairs);
	public NationHierarchyDto findNationHierarchy(String nationId);
}
