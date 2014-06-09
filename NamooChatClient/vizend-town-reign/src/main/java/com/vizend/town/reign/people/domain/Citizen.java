package com.vizend.town.reign.people.domain;

import java.util.Date;
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
 * EnrollmentDto
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2012. 10. 15.
 */
@Entity
public class Citizen {
	//
	@Id
	@Column(name = "CITIZEN_ID")
	private String id;	// MetroId-ABCDE

	@Column(nullable = false)
	private String metroId;

	@Column(nullable = false)
	private String loginId;

	@Column(nullable = true)
	private String loginName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = true)
	private String email;

	@Column(nullable = true)
	private String phoneNumber;

	@Column(nullable = true)
	private String photoId;

	@Column(nullable = false)
	private Long registDate;

	@Column
    private Date updateDate;

    @ManyToOne
    private Castellan castellan; 
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "citizen")
	private List<Towner> towners;

	//--------------------------------------------------------------------------
	// constructor

	protected Citizen() {
	}
	
	public Citizen(
			Castellan castellan,
			String metroId,
			String citizenId, 
			String loginId) {
		//
		this.castellan = castellan;
		this.metroId = metroId;
		this.setId(citizenId); 
		this.loginId = loginId;
		
		this.loginName = castellan.getDisplayName();
		this.email = castellan.getAuthEmail();
		this.phoneNumber = castellan.getAuthPhone(); 
		this.registDate = System.currentTimeMillis();
	}

	//--------------------------------------------------------------------------
	// methods
	
	@Override
	public String toString(){
		//
		StringBuilder builder = new StringBuilder(200);
		
		builder.append("## Citizen ##");
		builder.append(", metroId:"+metroId);
		builder.append(", id:"+id);
		builder.append(", loginId:"+loginId);
		builder.append(", loginName:"+loginName);
		builder.append(", password:"+password);
		builder.append(", email:"+email);
		builder.append(", phoneNumber:"+phoneNumber);
		builder.append(", photoId:"+photoId);
		builder.append(", registDate:"+registDate);
		builder.append(", updateDate:"+updateDate);
		
		return builder.toString();
	}
	
	public void setId(String id) {
		//
		if (!id.startsWith(metroId)) {
    		throw new VizendTownException("citizen's id should start with a metroId. id -> " + id); 
		}
		
		this.id = id;
	}

	public void setValues(List<NameValue> pairs) {
		for (NameValue attr : pairs) {
			String name = attr.name();

			if (StringUtils.isBlank(name)) {
				continue;
			}

			String value = (String) attr.value();

			if (name.equals("loginId")) {
				this.loginId = value;
			} else if (name.equals("loginName")) {
				this.loginName = value;
			} else if (name.equals("photoId")) {
				this.photoId = value;
			} else if (name.equals("email")) {
				this.email = value;
			} else if (name.equals("phoneNumber")) {
				this.phoneNumber = value;
			} else {
				throw new VizendTownException("No such an attr name. --> " + name);
			}
		}
	}

	public String getId() {
		return id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Long registDate) {
		this.registDate = registDate;
	}

	public String getMetroId() {
		return metroId;
	}

	public void setMetroId(String metroId) {
		this.metroId = metroId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Castellan getCastellan() {
		return castellan;
	}

	public void setCastellan(Castellan castellan) {
		this.castellan = castellan;
	}

	public List<Towner> getTowners() {
		return towners;
	}

	public void setTowners(List<Towner> towners) {
		this.towners = towners;
	}

}