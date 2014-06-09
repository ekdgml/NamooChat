package com.vizend.town.reign.nation.facade;

import java.util.List;

import com.vizend.town.reign.nation.domain.Nation;

/**
 * Nation structure building
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 5. 16.
 */
public interface NationEntity {
	//
	public String create(Nation nation);
	public Nation retrieve(String nationId);
	public Nation retrieveByMetroId(String metroId);
	public List<Nation> retrievAll();
	public void update(Nation nation);
	public void delete(String nationId);
	public boolean existsByNationId(String nationId);
	public void delete(Nation nation);
}
