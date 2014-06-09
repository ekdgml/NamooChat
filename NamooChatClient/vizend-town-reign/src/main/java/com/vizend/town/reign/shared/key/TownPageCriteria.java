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
 * @since 2012. 11. 30.
 */
@Deprecated
public class TownPageCriteria extends PageCriteria{

	/**  */
	private static final long serialVersionUID = 5545166355336918199L;

	private static final int defaultLimit = 10;

	private String metroId;
	private String townName;

	//--------------------------------------------------------------------------
	// 1. default constructor

	public TownPageCriteria(int page){
		super(page, defaultLimit);
	}

	public TownPageCriteria(String serializedCritera){
		init(defaultLimit, serializedCritera);
	}

	//--------------------------------------------------------------------------
	// 2. methods

	@Override
	public String toString(){
		//
		StringBuilder sb = new StringBuilder();
		sb.append("(metroId : ");
		sb.append(getMetroId());
		sb.append(")");

		return sb.toString();
	}

	/**
	 * @return
	 */
	public boolean hasMetroId() {
		//
		if(!StringUtils.isBlank(metroId)){
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public boolean hasTownName() {
		//
		if(!StringUtils.isBlank(townName)){
			return true;
		}
		return false;
	}

	//--------------------------------------------------------------------------
	// 3. getter/setter

	public String getMetroId() {
		return metroId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public void setMetroId(String metroId) {
		this.metroId = metroId;
	}

}
