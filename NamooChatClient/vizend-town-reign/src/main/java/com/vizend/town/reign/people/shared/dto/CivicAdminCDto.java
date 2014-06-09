package com.vizend.town.reign.people.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.people.domain.CivicAdmin;

/**
 * CivicAdminCDto
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song,Taegook</a>
 * @since 2013. 1. 24.
 */
public class CivicAdminCDto implements Serializable {
	//
	private static final long serialVersionUID = -4771805665447138237L;

    private String authorityId;
    private String civicUnitStr;
    private String adminLevelStr;

    private String servantId; // PublicServant.loginEmail

	//--------------------------------------------------------------------------
	// 0. default public constructor

    public CivicAdminCDto() {
    }

	//--------------------------------------------------------------------------
	// 1. private constructor

    protected CivicAdminCDto(CivicAdmin civicAdmin) {
    	//
    	this.authorityId = civicAdmin.getAuthorityId();
    	this.civicUnitStr = civicAdmin.getCivicUnit().name();
    	this.adminLevelStr = civicAdmin.getAdminLevel().name();
    	this.servantId = civicAdmin.getServant().getLoginEmail();
    }

    public CivicAdminCDto(String authoriyId, String civicUnitStr, String adminLevelStr, String servantId) {
    	//
    	this.authorityId = authoriyId;
    	this.civicUnitStr= civicUnitStr;
    	this.adminLevelStr = adminLevelStr;
    	this.servantId = servantId;
    }

    //--------------------------------------------------------------------------
    // 2. Domain object creation from this DTO

	public CivicAdmin createDomain() {
        //
		CivicAdmin civicAdmin = new CivicAdmin(authorityId, civicUnitStr, adminLevelStr);

    	return civicAdmin;
    }

    //--------------------------------------------------------------------------
    // 3. getter/setter

	public String getAuthorityId() {
		return authorityId;
	}

	public String getCivicUnitStr() {
		return civicUnitStr;
	}

	public String getAdminLevelStr() {
		return adminLevelStr;
	}

	public String getServantId() {
		return servantId;
	}
}
