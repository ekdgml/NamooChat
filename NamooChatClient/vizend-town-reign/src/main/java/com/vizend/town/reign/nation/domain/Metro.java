package com.vizend.town.reign.nation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.enumtype.PidProperty;
import com.vizend.dream.util.enumtype.UserIdType;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdTarget;

/**
 * Metro, the second of the ruling parties, can control many towns.
 *
 * @author <a href="mailto:syhan@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class Metro {
	//
	@Id
	@Column(name = "METRO_ID")
	private String id;	// NNNM

	@Column(name = "METRO_NAME", nullable = true)
	private String name;

	@Column
	private String intro;

	@Column(nullable = true)
	private String flagImageId; // Metro flag id

	@Column(nullable = false)
	private Long registDate;

	@Embedded
	private MetroPolicy policy;

	@ManyToOne
	private Nation nation;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "metro")
	private List<Town> towns = new ArrayList<Town>();

	//--------------------------------------------------------------------------
	// constructor

	protected Metro() {
		//
    }

	public Metro(Nation nation, String metroId, String metroName, MetroPolicy metroPolicy) {
		//
		this();
		this.nation = nation; 
		this.id = metroId; 
		this.name = metroName;
		this.policy = metroPolicy;
		this.registDate = System.currentTimeMillis();
	}
	
	//--------------------------------------------------------------------------
	// methods

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("## Metro ##");
		builder.append(", id:" + id);
		builder.append(", name:" + name);
		builder.append(", intro:" + intro);
		builder.append(", flagImageId:" + flagImageId);
		builder.append(", registDate:" + registDate);
		builder.append(", policy:" + policy.toString());

		return builder.toString();
	}

	public void setId(String id) {
		//
		if (id.length() != TownIdTarget.Metro.length()) {
			throw new VizendTownException("Metro id is not valid in length. -> " + id); 
		}

		this.id = id;
	}

	public void setValues(List<NameValue> pairs) {
		//
		for (NameValue attrValue : pairs) {
			String name = attrValue.name();

			if (StringUtils.isBlank(name)) {
				continue;
			}

			Object value = attrValue.value();

			if (name.equals("name")) {
				this.name = (String)value;
			}
			else if (name.equals("intro")) {
				this.intro = (String)value;
			}
			else if (name.equals("flagImageId")) {
				this.flagImageId = (String)value;
			}
			else if (name.equals("userIdTypeStr")){
				String enumStr = (String)value;
				this.policy.setUserIdType(UserIdType.valueOf(enumStr));
			}
			else if (name.equals("userIdType")){
				this.policy.setUserIdType((UserIdType)value);
			}
			else if (name.equals("pidPropertyStr")){
				String enumStr = (String)value;
				this.policy.setPidProperty(PidProperty.valueOf(enumStr));
			}
			else if (name.equals("pidProperty")){
				this.policy.setPidProperty((PidProperty)value);
			}
			else {
				throw new VizendTownException("No such an attr name. --> " + name);
			}
		}
	}

	public List<Town> getTowns() {
		return towns;
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

	public Nation getNation() {
		return nation;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public MetroPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(MetroPolicy policy) {
		this.policy = policy;
	}

}