package com.vizend.town.reign.nation.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.DateUtil;
import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdTarget;
import com.vizend.town.reign.shared.ProvisionMode;

/**
 * Nation, the top of the ruling parties, can control many Metros.
 *
 * @author <a href="mailto:syhan@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class Nation {
	//
	@Id
	@Column(name = "NATION_ID")
	private String id;			// NNN

	@Column(name = "NATION_NAME", nullable = false)
	private String name;

	@Column(nullable = true)
	private String intro;

	@Column(nullable = true)
	private String flagImageId; // for national flag

	@Column(nullable = false)
	private Long registDate;

	@Column
	@Enumerated(EnumType.STRING)
	private ProvisionMode provisionMode;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "nation")
	private List<Metro> metros = new ArrayList<Metro>();

	//--------------------------------------------------------------------------
	// constructor

	public Nation() {
		//
	}

	public Nation(String nationId, String name) {
		// 
		this(nationId, name, ProvisionMode.InternalService); 
	}
	
	public Nation(String nationId, String name, ProvisionMode provisionMode) {
		//
		this.id = nationId; 
		this.name = name; 
		this.provisionMode = provisionMode; 
		this.registDate = System.currentTimeMillis(); 
	}

	//--------------------------------------------------------------------------
	// methods

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("## Nation ##");
		builder.append(", id:" + id);
		builder.append(", name:" + name);
		builder.append(", intro:" + intro);
		builder.append(", flagImageId:" + flagImageId);
		builder.append(", provisionMode:" + provisionMode);
		builder.append(", registDate:" + DateUtil.getBaseString(registDate)); 

		return builder.toString();
	}

	public void setId(String id) {
		//
		if (id.length() != TownIdTarget.Nation.length()) {
			throw new VizendTownException("Nation id is not valid in length. -> " + id); 
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
			} else if (name.equals("intro")) {
				this.intro = (String)value;
			} else if (name.equals("flagImageId")) {
				this.flagImageId = (String)value;
			} else {
				throw new VizendTownException("Not valid attr name. --> " + name);
			}
		}
	}

	public Iterator<Metro> getMetroIterator() {
		//
		return this.metros.iterator();
	}

	public List<Metro> getMetros() {
		return this.metros;
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

	public void setFlagImageId(String flatImageId) {
		this.flagImageId = flatImageId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public ProvisionMode getProvisionMode() {
		return provisionMode;
	}

	public void setProvisionMode(ProvisionMode provisionMode) {
		this.provisionMode = provisionMode;
	}

}