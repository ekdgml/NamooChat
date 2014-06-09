/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.shared.key;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.page.PageCriteria;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 28.
 */
@Deprecated
public class MetroPageCriteria extends PageCriteria{

	/**  */
	private static final long serialVersionUID = 2607230137016599933L;

	private static final int defaultLimit = 10;

	private String metroId;
	private String nationId;
	private String metroName;

	public MetroPageCriteria(int page){
		super(page, defaultLimit);
	}

	public MetroPageCriteria(String serializedCritera){
		init(defaultLimit, serializedCritera);
	}

	public String getMetroId() {
		return metroId;
	}

	public void setMetroId(String metroId) {
		this.metroId = metroId;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public String getMetroName() {
		return metroName;
	}

	public void setMetroName(String metroName) {
		this.metroName = metroName;
	}

	/**
	 * @return
	 */
	public boolean hasNationId() {
		//
		if(!StringUtils.isBlank(getNationId())){
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		//
		StringBuilder sb = new StringBuilder("");
		sb.append(" ( NationId:");
		sb.append(getNationId());
		sb.append(" )");
		sb.append(" ( MetroId:");
		sb.append(getMetroId());
		sb.append(" )");

		return sb.toString();
	}

	/**
	 * @return
	 */
	public boolean hasMetroName() {
		//
		if(!StringUtils.isBlank(metroName)){
			return true;
		}
		return false;
	}
}
