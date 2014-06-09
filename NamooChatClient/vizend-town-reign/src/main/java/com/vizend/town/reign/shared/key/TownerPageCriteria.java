package com.vizend.town.reign.shared.key;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.page.PageCriteria;

@Deprecated
public class TownerPageCriteria extends PageCriteria{
	//
	private static final long serialVersionUID = 3068515891587615261L;

	private static final int defaultLimit = 10;

	private String townId;

	//--------------------------------------------------------------------------
	// 1. default constructor

	public TownerPageCriteria(int page){
		super(page, defaultLimit);
	}

	public TownerPageCriteria(String serializedCritera){
		init(defaultLimit, serializedCritera);
	}


	//--------------------------------------------------------------------------
	// 2. methods

	public boolean hasTownId() {
		//
		if(!StringUtils.isBlank(townId)){
			return true;
		}
		return false;
	}

	//--------------------------------------------------------------------------
	// 3. getters/setters

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}


}
