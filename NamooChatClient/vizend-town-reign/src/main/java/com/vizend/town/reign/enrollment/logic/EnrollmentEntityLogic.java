package com.vizend.town.reign.enrollment.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.dream.util.type.IdName;
import com.vizend.dream.util.type.IdNameList;
import com.vizend.town.reign.enrollment.domain.Enrollment;
import com.vizend.town.reign.enrollment.facade.EnrollmentEntity;

/**
 * EnrollmentEntityLogic
 *
 * @author  <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 15.
 */
@Repository
public class EnrollmentEntityLogic implements EnrollmentEntity {
	//
	@PersistenceContext
	private EntityManager	em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public Long create(Enrollment enrollment) {
		//
		em.persist(enrollment);
		return enrollment.getOId();
	}

	@Override
	public Enrollment retrieve(Long enrollmentOId) {
		//
		return em.find(Enrollment.class, enrollmentOId);
	}

	@Override
	public Enrollment retrieve(String guildId, Long memberId) {
		//
		String queryStr = "FROM Enrollment WHERE guild.id = :guildId " +
					" AND memberId = :memberId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("guildId", guildId);
		query.setParameter("memberId", memberId);

		return (Enrollment) SafeQuery.getUniqueResult(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> retrieveByCitizenId(String citizenId) {
		//
		String queryStr = "FROM Enrollment WHERE citizenId = :citizenId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("citizenId", citizenId);

		return (List<Enrollment>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollment> retrieveByGuildId(String guildId) {
		//
		String queryStr = "FROM Enrollment WHERE guild.id = :guildId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("guildId", guildId);

		return (List<Enrollment>) query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public IdNameList retrieveGuildIdNames(String loginNameLike, String guildId) {
		//
		String queryStr = "SELECT NEW com.vizend.dream.util.type.IdName(e.loginId, e.loginName) " +
				" FROM Enrollment e " +
				" WHERE e.loginName LIKE :loginNameLike AND e.guild.id = :guildId";
		Query query = em.createQuery(queryStr);
		query.setParameter("loginNameLike", loginNameLike);
		query.setParameter("guildId", guildId);
		List<IdName> idNames = (List<IdName>) query.getResultList();

		return new IdNameList(idNames);
	}

	@Override
	public void update(Enrollment enrollment) {
		//
		em.merge(enrollment);
	}

	@Override
	public void delete(Enrollment enrollment) {
		//
		if (enrollment == null) {
			return;
		}
		em.remove(enrollment);
	}
}
