package com.vizend.town.reign.ruling.facade;


import java.util.List;

import com.vizend.dream.util.type.NameValue;
import com.vizend.town.reign.people.shared.dto.CastellanCDto;
import com.vizend.town.reign.people.shared.dto.CastellanRDto;

/**
 * RecycleBin Process Component Interface
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2012. 5. 10.
 */
public interface CastellanManager {
	//
	public String registCastellan(String nationId, CastellanCDto castellanCDto);
	public CastellanRDto findCastellan(String castellanId);
	public CastellanRDto findCastellanByAuthEmail(String authEmail);
	public CastellanRDto findCastellanByAuthPhone(String authPhone);
	public void modifyCastellan(String castellanId, List<NameValue> pairs);
	public void removeCastellan(String castellanId); 
	public boolean existByAuthEmail(String authEmail);
	public boolean existByAuthPhone(String authPhone);
}
