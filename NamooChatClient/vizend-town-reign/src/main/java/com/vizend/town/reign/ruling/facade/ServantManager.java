package com.vizend.town.reign.ruling.facade;

import com.vizend.town.reign.people.shared.dto.ServantRDto;

/**
 *
 * @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
 * @since 2012. 10. 27.
 */
public interface ServantManager {
    //
    //--------------------------------------------------------------------------
    //PublicServant

    public ServantRDto findServantByEmail(String adminEmail);
	public ServantRDto findAdminServant(String authorityId);

}
