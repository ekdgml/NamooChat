package com.vizend.town.reign.people.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.people.domain.Towner;
import com.vizend.town.reign.people.facade.TownerEntity;

@Repository
public class TownerEntityLogic implements TownerEntity{
	//
	@PersistenceContext
	private EntityManager em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Towner towner) {
		//
		em.persist(towner);
		return towner.getId();
	}

	@Override
	public Towner retrieve(String townerId) {
		//
		String queryStr = " FROM Towner WHERE id = :townerId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("townerId", townerId);

		return SafeQuery.getUniqueResult(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Towner> retrieveByCitizenId(String citizenId) {
		//
		String queryStr = "FROM Towner WHERE citizen.id = :citizenId";
		Query query = em.createQuery(queryStr);
		query.setParameter("citizenId", citizenId);

		return (List<Towner>)query.getResultList();
	}

	@Override
	public Towner retrieve(String townId, String citizenId) {
		//
		String queryStr = "FROM Towner WHERE townId=:townId AND citizen.id=:citizenId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("townId", townId);
		query.setParameter("citizenId", citizenId);

		return (Towner)SafeQuery.getUniqueResult(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Towner> retrieveByTownId(String townId, int offset, int pageSize) {
		//
		String queryStr = "FROM Towner WHERE townId = :townId";
		Query query = em.createQuery(queryStr);
		query.setParameter("townId", townId);
		query.setFirstResult(offset); 
		query.setMaxResults(pageSize); 

		return (List<Towner>)query.getResultList();
	}

	@Override
	public void delete(Towner servant) {
		//
		if (servant == null) {
			return;
		}

		em.remove(servant);
		em.flush(); 
	}
	
	/* (non-Javadoc)
	 * @see com.vizend.town.reign.people.facade.TownerEntity#deleteByCitizenId(java.lang.String)
	 */
	@Override
	public void deleteByCitizenId(String citizenId) {
		//
		String queryStr = "DELETE FROM Towner WHERE citizen.id = :citizenId";
		Query query = em.createQuery(queryStr);
		query.setParameter("citizenId", citizenId);
		query.executeUpdate();
	}

	@Override
	public int countByTownId(String townId) {
		//
		String queryStr = "SELECT COUNT(id) FROM Towner WHERE townId = :townId";
		Query query = em.createQuery(queryStr);
		query.setParameter("townId", townId);

		return SafeQuery.count(query);
	}
}