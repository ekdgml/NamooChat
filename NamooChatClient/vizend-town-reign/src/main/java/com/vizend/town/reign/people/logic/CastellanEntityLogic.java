package com.vizend.town.reign.people.logic;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.people.domain.Castellan;
import com.vizend.town.reign.people.facade.CastellanEntity;

/**
 * RecycleBin Entity Logic
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 5. 12.
 */
@Repository
public class CastellanEntityLogic implements CastellanEntity {
    //
    @PersistenceContext
    private EntityManager em;

    // -------------------------------------------------------------------------
    // methods

    @Override
    public String create(Castellan castellan) {
        //
        em.persist(castellan);
        return castellan.getId();
    }

	@Override
    public Castellan retrieve(String castellanId) {
        //
    	String queryStr = "FROM Castellan WHERE id = :castellanId";
		Query query = em.createQuery(queryStr);
		query.setParameter("castellanId", castellanId);

		return SafeQuery.getUniqueResult(query);
    }

    @Override
    public Castellan retrieveByAuthEmail(String authEmail) {
        //
    	String queryStr = "FROM Castellan WHERE authEmail = :authEmail";
    	Query query = em.createQuery(queryStr);
		query.setParameter("authEmail", authEmail);

		return (Castellan)SafeQuery.getUniqueResult(query);
    }

    @Override
    public boolean existByAuthPhone(String authPhone) {
        //
    	String queryStr = "SELECT id FROM Castellan WHERE authPhone = :authPhone";
		Query query = em.createQuery(queryStr);
		query.setParameter("authPhone", authPhone);

		return SafeQuery.exist(query);
    }

    @Override
    public Castellan retrieveByAuthPhone(String authPhone) {
        //
    	String queryStr = "FROM Castellan WHERE authPhone = :authPhone";
		Query query = em.createQuery(queryStr);
		query.setParameter("authPhone", authPhone);

		return SafeQuery.getUniqueResult(query);
    }

    @Override
    public void update(Castellan castellan) {
        //
        em.merge(castellan);
    }

    @Override
    public void delete(Castellan castellan) {
        //
        em.remove(castellan);
    }

    @Override
    public boolean existByAuthEmail(String authEmail) {
        //
    	String queryStr = "SELECT id FROM Castellan WHERE authEmail = :authEmail ";
		Query query = em.createQuery(queryStr);
		query.setParameter("authEmail", authEmail);

		return SafeQuery.exist(query);
    }

    @Override
    public boolean existByCastellanId(String castellanId) {
        //
    	String queryStr = "SELECT c.id FROM Castellan c WHERE c.id = :castellanId ";
    	Query query = em.createQuery(queryStr);
		query.setParameter("castellanId", castellanId);

		return SafeQuery.exist(query);
    }
}