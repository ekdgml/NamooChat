package com.vizend.town.reign.nation.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.nation.domain.Nation;
import com.vizend.town.reign.nation.facade.NationEntity;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 20.
 */
@Repository
public class NationEntityLogic implements NationEntity {
	//
	@PersistenceContext
	private EntityManager em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Nation nation) {
		//
		em.persist(nation);
		em.flush();
		return nation.getId();
	}

	@Override
	public Nation retrieve(String nationId) {
		//
		String queryStr = " FROM Nation WHERE id = :nationId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);

		return SafeQuery.getUniqueResult(query);
	}
	
	@Override
	public Nation retrieveByMetroId(String metroId) {
		//
		String queryStr = "SELECT m.nation FROM Metro m WHERE m.id = :metroId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);
		
		return (Nation)SafeQuery.getUniqueResult(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Nation> retrievAll() {
		//
		Query query = em.createQuery("FROM Nation");
		return (List<Nation>) query.getResultList();
	}

	@Override
	public void update(Nation nation) {
		//
		em.merge(nation);
	}

	@Override
	public void delete(String nationId) {
		//
		Nation nation = this.retrieve(nationId);
		em.remove(nation);
	}

	@Override
	public boolean existsByNationId(String nationId) {
		//
		Nation nation = em.find(Nation.class, nationId);
		return nation != null;
	}

	@Override
	public void delete(Nation nation) {
		//
		if (nation == null) {
			return; 
		}
		em.remove(nation);
	}
}
