package com.vizend.town.reign.enrollment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Enrollment
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Taegook,Song</a>
 * @since 2013. 1. 15.
 */
@Entity
@SequenceGenerator(name="Enrollment_SEQ",sequenceName="Enrollment_SEQ")
public class Enrollment {
	//
	@Id
	@GeneratedValue(generator="Enrollment_SEQ")
    @Column(name = "ENROLLMENT_OID")
    private Long objectId;
	
	@Column(nullable = false)
	private String loginId; 
	
	@Column(nullable = false)
	private String loginName; 
	
	@Column(nullable = false)
	private String citizenId;

	@Column(nullable = false)
	private Long memberId; 

	@Column
	private String roleName; 
	
	@Column(nullable = false)
	private Long registDate;

	@ManyToOne
	private Guild guild; 
	
	//--------------------------------------------------------------------------
	// constructor

	public Enrollment() {
	}

	public Enrollment(String loginId, 
			String loginName, 
			String citizenId, 
			Long memberId, 
			String roleName) {
		//
		this.loginId = loginId; 
		this.loginName = loginName; 
		
		this.citizenId = citizenId; 
		this.memberId = memberId; 
		this.roleName = roleName; 
		this.registDate = System.currentTimeMillis(); 
	}

	//--------------------------------------------------------------------------
	// methods
	
	public Long getOId() {
		return objectId; 
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Long registDate) {
		this.registDate = registDate;
	}

	public Guild getGuild() {
		return guild;
	}

	public void setGuild(Guild guild) {
		this.guild = guild;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}