package com.vizend.town.reign.people.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;
import com.vizend.town.idgen.autogen.facade.TownIdTarget;

/**
 * Castellan, people in a nation.
 *
 * @author <a href="mailto:syhan@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 5. 12.
 */
@Entity
public class Castellan {
    //
    @Id
    @Column(name = "CASTELLAN_ID")
    private String id;				// nationId-[ABCDE](10,6)

    @Column(nullable = false)
    private String nationId;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = true)
    private String authEmail;

    @Column(nullable = true)
    private String authPhone;

    @Column(nullable = true)
    private String photoId;

    @Column(nullable = false)
    private boolean deleted; 
    
    @Column(nullable = false)
    private long registDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "castellan")
	private List<PublicServant> servants = new ArrayList<PublicServant>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "castellan")
	private List<Citizen> citizens = new ArrayList<Citizen>();

    //--------------------------------------------------------------------------

	protected Castellan() {
    }

    public Castellan(String nationId, String castellanId, String displayName, String authEmail) {
        //
    	this.deleted = false;
    	
    	this.id = castellanId; 
        this.displayName = displayName;
        this.authEmail = authEmail;
        this.registDate = System.currentTimeMillis();
    	this.setNationId(nationId);
    }

    //--------------------------------------------------------------------------

    @Override
    public String toString() {
    	//
        StringBuilder builder = new StringBuilder(300);

        builder.append("## Castellan ##");
        builder.append(", id:" + id);
        builder.append(", nationId:" + nationId);
        builder.append(", displayName:" + displayName);
        builder.append(", authEmail:" + authEmail);
        builder.append(", authPhone:" + authPhone);
        builder.append(", photoId:" + photoId);
        builder.append(", deleted:" + deleted);
        builder.append(", registDate:" + registDate);

        return builder.toString();
    }

    public void setId(String id) {
    	//
    	if (!id.startsWith(nationId)) {
    		throw new VizendTownException("Castellan's id should start with a nationId. id -> " + id); 
    	}
    	
        this.id = id;
    }

	public void setNationId(String nationId) {
		//
    	if (nationId.length() != TownIdTarget.Nation.length()) {
    		throw new VizendTownException("Nation id length is not valid. nation id length -> " + nationId.length()); 
    	}

		this.nationId = nationId;
	}

    public void setValues(List<NameValue> pairs) {
        //
        for (NameValue attrValue : pairs) {
            String name = attrValue.name();

            if (name == null || name.equals("") || name.equals("null")) {
                continue;
            }

            String value = (String) attrValue.value();

            if (name.equals("authEmail")) {
                this.authEmail = value;
            } else if (name.equals("authPhone")) {
                this.authPhone = value;
            } else if (name.equals("displayName")) {
            	this.displayName = value;
            } else if (name.equals("photoId")) {
            	this.photoId = value;
            }
        }
    }

    public Long getRegistDate() {
        return registDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

	public String getAuthEmail() {
		return authEmail;
	}

	public void setAuthEmail(String authEmail) {
		this.authEmail = authEmail;
	}

	public String getAuthPhone() {
		return authPhone;
	}

	public void setAuthPhone(String authPhone) {
		this.authPhone = authPhone;
	}

	public String getNationId() {
		return nationId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}

	public List<PublicServant> getServants() {
		return servants;
	}

	public void setServants(List<PublicServant> servants) {
		this.servants = servants;
	}
}