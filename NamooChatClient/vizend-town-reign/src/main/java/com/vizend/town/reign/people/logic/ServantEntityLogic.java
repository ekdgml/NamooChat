package com.vizend.town.reign.people.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.people.domain.PublicServant;
import com.vizend.town.reign.people.facade.ServantEntity;
import com.vizend.town.reign.shared.CivicAdminLevel;

/**
*
* @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
* @since 2012. 10. 27.
*/
@Repository
public class ServantEntityLogic implements ServantEntity {
    //
    @PersistenceContext
    private EntityManager em;

    //--------------------------------------------------------------------------
    // methods

    @Override
    public String create(PublicServant publicServant) {
        //
        em.persist(publicServant);
        return publicServant.getLoginEmail();
    }

    @Override
    public PublicServant retrieve(Long servantId) {
        //
    	return em.find(PublicServant.class, servantId);
    }

    @Override
    public PublicServant retrieveByEmail(String loginEmail) {
    	//
    	String queryStr = "FROM PublicServant WHERE loginEmail = :loginEmail";
    	Query query = em.createQuery(queryStr);
    	query.setParameter("loginEmail", loginEmail);

    	return (PublicServant)SafeQuery.getUniqueResult(query);
    }

    @Override
    public PublicServant retrieveByPhone(String phoneNumber) {
    	//
    	String queryStr = "FROM PublicServant WHERE phoneNumber = :phoneNumber";
    	Query query = em.createQuery(queryStr);
    	query.setParameter("phoneNumber", phoneNumber);

    	return (PublicServant)SafeQuery.getUniqueResult(query);
    }

    @Override
    public PublicServant retrieveByCastellanId(String castellanId) {
    	//
    	String queryStr = "FROM PublicServant WHERE castellanId = :castellanId";
    	Query query = em.createQuery(queryStr);
    	query.setParameter("castellanId", castellanId);

    	return (PublicServant)SafeQuery.getUniqueResult(query);
    }

	@Override
    @SuppressWarnings("unchecked")
    public List<PublicServant> retrieveByNationId(String nationId) {
        //
		String queryStr = "FROM PublicServant WHERE nationId = :nationId";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);

		return (List<PublicServant>)query.getResultList();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<PublicServant> retrieveAdminServant(String authorityId) {
		//
		String queryStr = "SELECT s FROM PublicServant s JOIN s.civicAdmins a WHERE a.authorityId = :authorityId ";

		Query query = em.createQuery(queryStr);
		query.setParameter("authorityId", authorityId);

		return (List<PublicServant>)query.getResultList();

	}

	@Override
	public PublicServant retrievePrimaryAdminServant(String authorityId) {
		//
		String queryStr = "SELECT ca.servant FROM CivicAdmin ca WHERE ca.authorityId = :authorityId AND ca.adminLevel = :adminLevel ";
		Query query = em.createQuery(queryStr);
		query.setParameter("authorityId", authorityId);
		query.setParameter("adminLevel", CivicAdminLevel.Primary);

		return (PublicServant)SafeQuery.getUniqueResult(query);
	}


    @Override
    public void update(PublicServant publicServant) {
        //
        em.merge(publicServant);
    }

    @Override
    public void delete(String loginEmail) {
        //
        PublicServant servant = this.retrieveByEmail(loginEmail);
        delete(servant);
    }

    @Override
    public void delete(PublicServant servant) {
    	//
    	if (servant == null) {
    		return;
    	}
    	em.remove(servant);
    }

    @Override
    public boolean existByLoginEmail(String loginEmail) {
        //
		String queryStr = "SELECT id FROM PublicServant WHERE loginEmail = :loginEmail";
		Query query = em.createQuery(queryStr);
		query.setParameter("loginEmail", loginEmail);

		return SafeQuery.exist(query);
    }

    @Override
    public boolean existByPhoneNumber(String phoneNumber) {
    	//
		String queryStr = "SELECT id FROM PublicServant WHERE phoneNumber = :phoneNumber";
		Query query = em.createQuery(queryStr);
		query.setParameter("phoneNumber", phoneNumber);

		return SafeQuery.exist(query);
    }

    @Override
    public boolean existByCastellanId(String castellanId) {
    	//
		String queryStr = "SELECT id FROM PublicServant WHERE castellanId = :castellanId";
		Query query = em.createQuery(queryStr);
		query.setParameter("phoneNumber", castellanId);

		return SafeQuery.exist(query);
    }
}