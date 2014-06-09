package com.vizend.town.reign.nation.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.nation.domain.Town;
import com.vizend.town.reign.nation.facade.TownEntity;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 20.
 */
@Repository
public class TownEntityLogic implements TownEntity {
	//
	@PersistenceContext
	private EntityManager em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Town town) {
		//
		em.persist(town);
		em.flush(); 
		return town.getId();
	}

	@Override
	public Town retrieve(String townId) {
		//
		String queryStr = " FROM Town WHERE id = :townId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("townId", townId);

		return SafeQuery.getUniqueResult(query);
	}

	@Override
	public int countByMetroId(String metroId) {
		//
		String queryStr = "SELECT COUNT(id) FROM Town WHERE metro.id = :metroId";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);

		return SafeQuery.count(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Town> retrieveByMetroId(String metroId) {
		//
		String queryStr = "FROM Town WHERE metro.id = :metroId";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);

		return (List<Town>)query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Town> retrieveByNameLike(String metroId, String nameLike) {
		//
		String queryStr = "FROM Town WHERE metro.id = :metroId AND name LIKE :nameLike";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);
		query.setParameter("nameLike", nameLike);

		return (List<Town>)query.getResultList();
	}

	@Override
	public void update(Town town) {
		//
		em.merge(town);
	}

	@Override
	public void delete(String townId) {
		//
		Town town = this.retrieve(townId);
		this.delete(town);
	}

	@Override
	public void delete(Town town) {
		//
		if (town == null) {
			return;
		}

		em.remove(town);
	}

	@Override
	public boolean existByTownName(String metroId, String townName) {
		//
		String queryStr = "SELECT id FROM Town WHERE metro.id=:metroId AND name=:townName";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);
		query.setParameter("townName", townName);

		return SafeQuery.exist(query);
	}

	@Override
	public boolean existDefaultTown(String metroId) {
		//
		String queryStr = " SELECT id FROM Town WHERE metro.id=:metroId AND defaultTown=:defaultTown ";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);
		query.setParameter("defaultTown", true);

		return SafeQuery.exist(query);
	}
}
