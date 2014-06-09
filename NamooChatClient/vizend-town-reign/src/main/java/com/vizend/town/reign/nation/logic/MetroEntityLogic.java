package com.vizend.town.reign.nation.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.nation.domain.Metro;
import com.vizend.town.reign.nation.facade.MetroEntity;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 20.
 */
@Repository
public class MetroEntityLogic implements MetroEntity {
	//
	@PersistenceContext
	private EntityManager em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Metro metro) {
		//
		em.persist(metro);
		em.flush(); 
		return metro.getId();
	}

	@Override
	public Metro retrieve(String metroId) {
		//
		String queryStr = " FROM Metro WHERE id = :metroId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);

		return SafeQuery.getUniqueResult(query);
	}

	@Override
	public int countByNationId (String nationId) {
		//
    	String queryStr = "SELECT COUNT(id) FROM Metro WHERE nation.id = :nationId";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);

		return SafeQuery.count(query);
	}

	@Override
	public int countByNameLike(String nationId, String nameLike) {
		//
    	String queryStr = "SELECT COUNT(id) FROM Metro WHERE nation.id = :nationId AND name LIKE :nameLike";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);
		query.setParameter("nameLike", "%"+nameLike+"%");

		return SafeQuery.count(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Metro> retrieveByNationId (String nationId) {
		//
    	String queryStr = "FROM Metro WHERE nation.id = :nationId";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);

		return (List<Metro>) query.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Metro> retrieveByNameLike(String nationId, String nameLike) {
		//
    	String queryStr = "FROM Metro WHERE nation.id = :nationId AND name LIKE :nameLike";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);
		query.setParameter("nameLike", "%"+nameLike+"%");

		return (List<Metro>) query.getResultList();
	}

	@Override
	public void update(Metro metro) {
		//
		em.merge(metro);
	}

	@Override
	public void delete(Metro metro) {
		//
		if (metro == null) {
			return;
		}
		em.remove(metro);
	}
}