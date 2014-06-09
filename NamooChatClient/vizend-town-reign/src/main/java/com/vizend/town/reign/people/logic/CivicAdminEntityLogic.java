package com.vizend.town.reign.people.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.town.reign.people.domain.CivicAdmin;
import com.vizend.town.reign.people.facade.CivicAdminEntity;
import com.vizend.town.reign.shared.CivicAdminLevel;
import com.vizend.town.reign.shared.CivicUnit;

/**
*
* @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
* @since 2012. 10. 27.
*/
@Repository
public class CivicAdminEntityLogic implements CivicAdminEntity {
    //
    @PersistenceContext
    private EntityManager em;

    //--------------------------------------------------------------------------
    // methods

    @Override
    public String create(CivicAdmin civicAdmin) {
        //
        em.persist(civicAdmin);
        return civicAdmin.getId();
    }

    @Override
    public CivicAdmin retrieve(String id) {
        //
        return em.find(CivicAdmin.class, id);
    }

    @Override
    public CivicAdmin retrieveByCivicUnit(String authorityId, CivicAdminLevel adminLevel, CivicUnit civicUnit) {
    	//
    	String queryStr = " FROM CivicAdmin WHERE authorityId = :authorityId AND adminLevel = :adminLevel AND civicUnit = :civicUnit ";
    	Query query = em.createQuery(queryStr);
    	query.setParameter("authorityId", authorityId);
    	query.setParameter("adminLevel", adminLevel);
    	query.setParameter("civicUnit", civicUnit);

    	return (CivicAdmin)SafeQuery.getResult(query);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<CivicAdmin> retrieveByCivicUnit (String authorityId, CivicUnit civicUnit) {
    	//
    	String queryStr = " FROM CivicAdmin WHERE authorityId = :authorityId AND civicUnit = :civicUnit ";
    	Query query = em.createQuery(queryStr);
		query.setParameter("authorityId", authorityId);
		query.setParameter("civicUnit", civicUnit);

		return (List<CivicAdmin>)query.getResultList();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<CivicAdmin> retrieveByServantId(String servantId) {
		//
	   	final String queryStr = " FROM CivicAdmin WHERE servant.id = :servantId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("servantId", servantId);

		return (List<CivicAdmin>) query.getResultList();
	}

    @Override
    public void update(CivicAdmin civicAdmin) {
    	//
		em.merge(civicAdmin);
    }

    @Override
    public void delete(String id) {
        //
        CivicAdmin civicAdmin = this.retrieve(id);
        if (civicAdmin == null) {
        	return;
        }

        em.remove(civicAdmin);
    }

    public boolean identifyAdmin(String servantId, CivicUnit civicUnit) {
    	//
	   	final String queryStr = " SELECT id FROM CivicAdmin "
    			+ "WHERE servant.id = :servantId AND civicUnit = :civicUnit ";

 		Query query = em.createQuery(queryStr);
		query.setParameter("servantId", servantId);
		query.setParameter("civicUnit", civicUnit);

		return SafeQuery.exist(query);
    }

    @Override
    public boolean existRootAdmin(String nationId) {
    	//
    	String queryStr = " SELECT id FROM CivicAdmin WHERE servant.nationId = :nationId " +
    			"AND adminLevel = :adminLevel";
		Query query = em.createQuery(queryStr);
		query.setParameter("nationId", nationId);
		query.setParameter("adminLevel", CivicAdminLevel.Primary);

		return SafeQuery.exist(query);
    }
}