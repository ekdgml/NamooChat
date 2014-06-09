package com.vizend.town.reign.nation.facade;

import java.util.List;

import com.vizend.town.reign.nation.domain.Metro;

/**
 * Metro structure building
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 20.
 */
public interface MetroEntity {
	//
	public String create(Metro metro);
	public Metro retrieve(String metroId);
	public List<Metro> retrieveByNationId(String nationId);
	public List<Metro> retrieveByNameLike(String nationId, String nameLike);
	public void update(Metro metro);
	public void delete(Metro metro);
	public int countByNationId(String nationId);
	public int countByNameLike(String nationId, String nameLike);
}
