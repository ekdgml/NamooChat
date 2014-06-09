package com.vizend.town.reign.people.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang.StringUtils;

import com.vizend.dream.util.exception.VizendTownException;
import com.vizend.dream.util.type.NameValue;

/**
 * Public servant, worker work for ruling parties </br>
 *
 * @author <a href="tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class PublicServant {
    //
    @Id
    @Column(name = "SERVANT_ID")
    private String id;

    @Column(nullable = false)
    private String nationId;

    @Column(nullable = false)
    private String loginEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String loginName;

    @Column
    private String phoneNumber;

    @Column(nullable = true)
    private String photoId;

    @Column(nullable = false)
    private Long registDate;

    @ManyToOne
    private Castellan castellan; 
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "servant")
	private List<CivicAdmin> civicAdmins = new ArrayList<CivicAdmin>();

    //--------------------------------------------------------------------------
    // constructor

    public PublicServant() {
    }

    public PublicServant(String nationId, String name, String loginEmail, String password) {
        //
    	this.nationId = nationId;
    	this.loginName = name;
        this.loginEmail = loginEmail;
        this.password = password;
        this.registDate = System.currentTimeMillis();
    }

    //--------------------------------------------------------------------------
    // methods

    @Override
    public String toString(){
    	//
    	StringBuilder builder = new StringBuilder(200);
    	
    	builder.append("## PublicServant ##");
    	builder.append(", id:"+id);
    	builder.append(", nationId:"+nationId);
    	builder.append(", loginEmail:"+loginEmail);
    	builder.append(", password:"+password);
    	builder.append(", loginName:"+loginName);
    	builder.append(", phoneNumber:"+phoneNumber);
    	builder.append(", photoId:"+photoId);
    	builder.append(", registDate:"+registDate);
    	
    	return builder.toString();
    }
    
    public String getId() {
    	return id; 
    }
    
    public void setId(String id) {
    	this.id = id; 
    }
    
    public List<CivicAdmin> getCivicAdmins() {
    	//
    	return civicAdmins;
    }

    public void setValues(List<NameValue> pairs) {
        //
        for (NameValue attrValue : pairs) {
            String name = attrValue.name();

            if (StringUtils.isBlank(name)) {
                continue;
            }

            String value = (String) attrValue.value();

            if (name.equals("password")) {
                this.password = value;
            } else if (name.equals("loginName")) {
                this.loginName = value;
            } else if (name.equals("phoneNumber")) {
                this.phoneNumber = value;
            } else if (name.equals("photoId")) {
                this.photoId = value;
            } else {
                throw new VizendTownException("No such an attr name. --> " + name);
            }
        }
    }

	public Long getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Long registDate) {
        this.registDate = registDate;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getNationId() {
		return nationId;
	}

	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	public Castellan getCastellan() {
		return castellan;
	}

	public void setCastellan(Castellan castellan) {
		this.castellan = castellan;
	}

}
