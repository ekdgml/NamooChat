package com.vizend.town.reign.people.facade;

import java.util.List;

import com.vizend.town.reign.people.domain.PublicServant;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 27.
 */
public interface ServantEntity {
    //
    public String create(PublicServant publicServant);
    
    public PublicServant retrieve(Long servantId);
    public PublicServant retrieveByEmail(String loginEmail);
	public PublicServant retrieveByCastellanId(String castellanId);
	public PublicServant retrieveByPhone(String phone);
	public List<PublicServant> retrieveByNationId(String nationId);
	public List<PublicServant> retrieveAdminServant(String authorityId);
	public PublicServant retrievePrimaryAdminServant(String authorityId);

    public void update(PublicServant publicServant);
    
	public boolean existByPhoneNumber(String phoneNumber);
	public boolean existByCastellanId(String castellanId);
	public boolean existByLoginEmail(String loginEmail);
	
	public void delete(String loginEmail);
	public void delete(PublicServant servant);
}
