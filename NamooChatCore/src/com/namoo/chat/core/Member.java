package com.namoo.chat.core;

import java.io.Serializable;

public class Member implements Serializable {
	//
	private static final long serialVersionUID = -3129798598695557638L;

	private String nickName;
	private int claimCount;
	
	public Member(String nickName) {
		// 
		this.nickName = nickName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
}
