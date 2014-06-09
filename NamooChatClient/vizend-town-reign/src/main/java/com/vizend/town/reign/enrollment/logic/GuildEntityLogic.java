package com.vizend.town.reign.enrollment.logic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vizend.dream.util.SafeQuery;
import com.vizend.dream.util.enumtype.GuildType;
import com.vizend.town.reign.enrollment.domain.Guild;
import com.vizend.town.reign.enrollment.facade.GuildEntity;

/**
 * GuildEntityLogic
 *
 * @author  <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 15.
 */
@Repository
public class GuildEntityLogic implements GuildEntity {
	//
	@PersistenceContext
	private EntityManager	em;

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String create(Guild guild) {
		//
		em.persist(guild);
		return guild.getId();
	}

	@Override
	public Guild retrieve(String guildId) {
		//
		return em.find(Guild.class, guildId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guild> retrieveByGuildType(GuildType guildType) {
		//
		String queryStr = "FROM Guild WHERE guildType = :guildType ";
		Query query = em.createQuery(queryStr);
		query.setParameter("guildType", guildType);

		return (List<Guild>) query.getResultList();
	}

	@Override
	public void update(Guild guild) {
		//
		em.merge(guild);
	}

	@Override
	public void delete(Guild guild) {
		//
		if (guild == null) {
			return;
		}
		em.remove(guild);
	}

	@Override
	public boolean exists(String guildId) {
		//
		String queryStr = " FROM Guild WHERE id = :guildId ";
		Query query = em.createQuery(queryStr);
		query.setParameter("guildId", guildId);
		return SafeQuery.exist(query);
	}
}