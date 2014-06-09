package com.vizend.town.reign.provider.shared.dto;

import java.io.Serializable;

import com.vizend.town.reign.enrollment.domain.Enrollment;

public class EnrollmentDto implements Serializable {
	//
	private static final long serialVersionUID = 3942512378410184811L;
	//
	private String citizenId;
	private String guildId;
	private Long memberId;
	private String roleName;

	//--------------------------------------------------------------------------
	// constructor

	public EnrollmentDto() {
	}

	public EnrollmentDto(
			String citizenId,
			String guildId,
			Long memberId,
			String roleName) {
		//
		this.citizenId = citizenId;
		this.memberId = memberId;
		this.guildId = guildId;
		this.roleName = roleName;
	}

	public Enrollment createDomain(String loginId, String loginName) {
		//
		Enrollment enrollment = new Enrollment(loginId, loginName, citizenId, memberId, roleName);

		return enrollment;
	}

	//--------------------------------------------------------------------------
	// methods

	public String getRoleName() {
		return roleName;
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

	public String getGuildId() {
		return guildId;
	}

	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}