package com.vizend.town.reign.nation.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.vizend.dream.util.enumtype.PidProperty;
import com.vizend.dream.util.enumtype.UserIdType;

/**
 * MetroPolicy 
 * 
 * @author <a href="mailto:ysahn@nextree.co.kr">ysahn</a>
 * @since 2012. 11. 25.
 */
@Embeddable
public class MetroPolicy {

	@Column
	@Enumerated(EnumType.STRING)
	private UserIdType userIdType;

	@Column
	@Enumerated(EnumType.STRING)
	private PidProperty pidProperty;

	//--------------------------------------------------------------------------
	// 1. default public constructor

	public MetroPolicy(){
		//
		this.userIdType = UserIdType.Email;
		this.pidProperty = PidProperty.Email;
	}

	public MetroPolicy(UserIdType userIdType, PidProperty pidProperty){
		//
		this.userIdType = userIdType;
		this.pidProperty = pidProperty; 	// default
	}

	//--------------------------------------------------------------------------
	// 2. getter/setter

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("## MetroPolicy ##");
		builder.append(", userIdType:" + userIdType.name());
		builder.append(", pidProperty:" + pidProperty.name());

		return builder.toString();
	}

	public UserIdType getUserIdType() {
		return userIdType;
	}

	public PidProperty getPidProperty() {
		return pidProperty;
	}

	public void setUserIdType(UserIdType userIdType) {
		this.userIdType = userIdType;
	}

	public void setPidProperty(PidProperty pidProperty) {
		this.pidProperty = pidProperty;
	}
}
