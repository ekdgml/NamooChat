package com.vizend.town.reign.people.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.vizend.town.reign.shared.CivicAdminLevel;
import com.vizend.town.reign.shared.CivicUnit;

/**
 * PublicServant Role, a role in ruling parties </br>
 *
 * @author <a href="tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class CivicAdmin {
    //
    @Id
    @Column(name = "CIVICADMIN_ID")
    private String id;	// servantId + "-" + authorityId; 

    @Column(nullable = false)
    private String authorityId;

    @Column
    @Enumerated(EnumType.STRING)
    private CivicUnit civicUnit;

    @Column
    @Enumerated(EnumType.STRING)
    private CivicAdminLevel adminLevel;

    @Column(nullable = false)
    private Long registDate;

    @ManyToOne
    private PublicServant servant;

    //--------------------------------------------------------------------------
    // constructor

    public CivicAdmin() {
    }

    public CivicAdmin(String authorityId, String civicUnitStr) {
        //
    	this.authorityId = authorityId;
        this.civicUnit = CivicUnit.valueOf(civicUnitStr);
        this.adminLevel = CivicAdminLevel.Primary;  	// default
        this.registDate = System.currentTimeMillis();
    }

    public CivicAdmin(String authorityId, String civicUnitStr, String adminLevelStr) {
        //
    	this(authorityId, civicUnitStr);
        this.adminLevel = CivicAdminLevel.valueOf(adminLevelStr);
    }

    //--------------------------------------------------------------------------
    // methods

    @Override
    public String toString(){
    	//
    	StringBuilder builder = new StringBuilder(200);
    	
    	builder.append("## CivicAdmin ##");
    	builder.append(", id:"+id);
    	builder.append(", authorityId:"+authorityId);
    	builder.append(", civicUnit:"+civicUnit.name());
    	builder.append(", adminLevel:"+adminLevel.name());
    	builder.append(", registDate:"+registDate);
    	
    	return builder.toString();
    }
    
	public void setId(String servantId, String authorityId) {
		this.id = servantId + "-" + authorityId;
	}
	
    public Long getRegistDate() {
        return registDate;
    }

	public CivicUnit getCivicUnit() {
		return civicUnit;
	}

	public void setCivicUnit(CivicUnit civicUnit) {
		this.civicUnit = civicUnit;
	}

	public CivicAdminLevel getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(CivicAdminLevel adminLevel) {
		this.adminLevel = adminLevel;
	}

	public PublicServant getServant() {
		return servant;
	}

	public void setServant(PublicServant servant) {
		this.servant = servant;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getId() {
		return id;
	}

}
