package com.vizend.town.reign.people.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.people.domain.Citizen;
import com.vizend.town.reign.people.domainbackup.CitizenBackup;
import com.vizend.town.reign.people.facade.CitizenEntity;

/**
 * EnrollmentDto Entity - Interface for the entity type component
 *
 * @author <a href="mailto:dohkim@nextree.co.kr">DoHyun</a>
 * @since 2012. 10. 20.
 */
@Repository
public class CitizenEntityLogic implements CitizenEntity {
	//
	@PersistenceContext
	private EntityManager em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Citizen citizen) {
		//
		em.persist(citizen);
		return citizen.getId();
	}
	
	@Override
	public void createBackup(CitizenBackup citizenBackup) {
		//
		em.persist(citizenBackup);
	}

	@Override
	public Citizen retrieve(String citizenId) {
		//
		
    	String queryStr = "FROM Citizen WHERE id = :citizenId";
		Query query = em.createQuery(queryStr);
		query.setParameter("citizenId", citizenId);

		return SafeQuery.getUniqueResult(query);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Citizen> retrieveByMetroId(String metroId, int offset, int pageSize) {
		// 
		String queryStr = "FROM Citizen WHERE metroId = :metroId";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);
		query.setFirstResult(offset); 
		query.setMaxResults(pageSize); 

		return (List<Citizen>)query.getResultList();
	}
	
	@Override
	public Citizen retrieveByLoginId(String loginId) {
		//
		String queryStr = " FROM Citizen WHERE loginId = :loginId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("loginId", loginId);

		return SafeQuery.getUniqueResult(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Citizen> retrieveByIds(List<String> citizenIds) {
		//
		String queryStr = "FROM Citizen WHERE id IN (:citizenIds)";
		Query query = em.createQuery(queryStr);
		query.setParameter("citizenIds", citizenIds);

		return (List<Citizen>)query.getResultList();
	}

	@Override
	public void update(Citizen citizen) {
		//
		em.merge(citizen);
	}

	@Override
	public void delete(Citizen citizen) {
		//
		em.remove(citizen);
	}
	
	@Override
	public boolean existByLoginId(String loginId) {
		//
		String queryStr = "SELECT id FROM Citizen WHERE loginId = :loginId";
		Query query = em.createQuery(queryStr);
		query.setParameter("loginId", loginId);

		return SafeQuery.exist(query);
	}

	@Override
	public boolean authenticate(String loginId, String password) {
		//
		String queryStr = "SELECT c.id FROM Citizen c " +
					" WHERE c.loginId = :loginId AND password = :password";

		Query query = em.createQuery(queryStr);
		query.setParameter("loginId", loginId);
		query.setParameter("password", password);

		return SafeQuery.exist(query);
	}

	@Override
	public int countByMetroId(String metroId) {
		//
		String queryStr = " SELECT COUNT(id) FROM Citizen WHERE metroId = :metroId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("metroId", metroId);

		return SafeQuery.count(query);
	}
}
