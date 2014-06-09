package com.vizend.town.reign.people.facade;

import java.util.List;

import com.vizend.town.reign.people.domain.CivicAdmin;
import com.vizend.town.reign.shared.CivicAdminLevel;
import com.vizend.town.reign.shared.CivicUnit;

/**
*
* @author <a href="mailto:jinason@nextree.co.kr">Son JinAh</a>
* @since 2012. 10. 27.
*/
public interface CivicAdminEntity {
    //
    public String create(CivicAdmin civicAdmin);

    public CivicAdmin retrieve(String id);
    public CivicAdmin retrieveByCivicUnit(String nationId, CivicAdminLevel adminLevel, CivicUnit civicUnit);
    public List<CivicAdmin> retrieveByCivicUnit(String authorityId, CivicUnit rootadmin);
    public List<CivicAdmin> retrieveByServantId(String servantId);

    public void update(CivicAdmin civicAdmin);
    public void delete(String id);

    public boolean existRootAdmin(String nationId);
    public boolean identifyAdmin(String servantId, CivicUnit civicUnit);


}
