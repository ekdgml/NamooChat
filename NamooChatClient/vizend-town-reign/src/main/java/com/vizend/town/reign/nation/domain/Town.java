package com.vizend.town.reign.nation.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdTarget;

/**
 * Town, the leaf of the ruling parties, is a autonomous body
 *
 * @author <a href="mailto:syhan@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class Town {
	//
	@Id
	@Column(name = "TOWN_ID")
	private String id;	//NNNMT

	@Column(name = "TOWN_NAME", nullable = true)
	private String name;

	@Column(nullable = false)
	private boolean defaultTown; 
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TownType townType; 
	
	@Column
	private String intro;

	@Column(nullable = true)
	private String flagImageId;

	@Column(nullable = false)
	private Long registDate;

	@ManyToOne
	private Metro metro;

	//--------------------------------------------------------------------------
	// constructor

	protected Town() {
	}

	public Town(Metro metro, String townId, String townName, boolean defaultTown, TownType townType) {
		//
		this.metro = metro; 
		this.id = townId; 
		this.name = townName;
		this.defaultTown = defaultTown;
		this.townType = TownType.Org;
		this.registDate = System.currentTimeMillis(); 
	}
	
	//--------------------------------------------------------------------------
	// methods

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("## Town ##");
		builder.append(", id:" + id);
		builder.append(", name:" + name);
		builder.append(", defaultTown:" + defaultTown);
		builder.append(", intro:" + intro);
		builder.append(", flagImageId:" + flagImageId);
		builder.append(", registDate:" + registDate);
		
		return builder.toString();
	}
	
	public void setValues(List<NameValue> pairs) {
		//
		for (NameValue attrValue : pairs) {
			String name = attrValue.name();

			if (StringUtils.isBlank(name)) {
				continue;
			}

			Object value = (Object) attrValue.value();

			if (name.equals("name")) {
				this.name = (String) value;
			} else if (name.equals("intro")) {
				this.intro = (String) value;
			} else if (name.equals("flagImageId")) {
				this.flagImageId = (String) value;
			} else {
				throw new VizendTownException("No such an attr name. --> " + name);
			}
		}
	}

	public Long getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Long registDate) {
		this.registDate = registDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		//
		if (id.length() != TownIdTarget.Town.length()) {
			throw new VizendTownException("Town id is not valid in length. -> " + id); 
		}

		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlagImageId() {
		return flagImageId;
	}

	public void setFlagImageId(String flagImageId) {
		this.flagImageId = flagImageId;
	}

	public Metro getMetro() {
		return metro;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public boolean isDefaultTown() {
		return defaultTown;
	}

	public void setDefaultTown(boolean defaultTown) {
		this.defaultTown = defaultTown;
	}

	public TownType getTownType() {
		return townType;
	}

	public void setTownType(TownType townType) {
		this.townType = townType;
	}
}