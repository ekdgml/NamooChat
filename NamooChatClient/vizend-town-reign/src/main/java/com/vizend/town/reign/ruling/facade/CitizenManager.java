/*******************************************************************************
 * Copyright(c) 2012-2017 ACE Life. All rights reserved.
 * This software is the proprietary information of ACE Life.
 *******************************************************************************/
package com.vizend.town.reign.ruling.facade;

import java.util.List;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.people.shared.dto.CitizenCDto;
import com.vizend.town.reign.people.shared.dto.CitizenRDto;

/**
 *
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 12. 12.
 */
public interface CitizenManager {
	//
	public String registCitizen(String metroId, CitizenCDto citizenDto);
	public void registCitizens(String metroId, List<CitizenCDto> citizenDtos);
	public CitizenRDto findCitizen(String citizenId);
	public CitizenRDto findCitizenByLoginId(String loginId);
	public List<CitizenRDto> findCitizensByMetroId(String metroId, int offset, int pageSize);
	public List<CitizenRDto> findCitizensByIds(List<String> citizenIds);
	public void modifyCitizen(String citizenId, List<NameValue> pairs);
	public void removeCitizen(String citizenId);
	public void removeCitizens(List<String> citizenIds);
}
