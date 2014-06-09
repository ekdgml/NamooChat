package com.vizend.town.reign.enrollment.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vizend.dream.util.enumtype.GuildType;

/**
 * GuildDto
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2013. 1. 15.
 */
@Entity
public class Guild {
	//
	@Id
	@Column(nullable = false, name="GUILD_ID")
	private String id;

	@Column(nullable = false)
	private String townId;

	@Column(nullable = false)
	private String guildName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private GuildType guildType;

	@Column(nullable = false)
	private Long registDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guild")
	private List<Enrollment> enrollments = new ArrayList<Enrollment>();

	//--------------------------------------------------------------------------
	// constructor

	public Guild() {
	}

	public Guild(String townId, String guildId, String guildName, GuildType guildType) {
		//
		this.townId = townId;
		this.id = guildId;
		this.guildName = guildName;
		this.guildType = guildType;
		this.registDate = System.currentTimeMillis();
	}

	//--------------------------------------------------------------------------
	// methods

	public void addEnrollment(Enrollment enrollment) {
		//
		enrollments.add(enrollment);
		enrollment.setGuild(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String guildId) {
		this.id = guildId;
	}

	public GuildType getGuildType() {
		return guildType;
	}

	public void setGuildType(GuildType guildType) {
		this.guildType = guildType;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Long registDate) {
		this.registDate = registDate;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}
}