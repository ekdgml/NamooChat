package com.namoo.chat.core;

import java.util.List;

public class MemberListMessage extends Message {
	
	private static final long serialVersionUID = 7319092207646932545L;

	public MemberListMessage() {
		super(MessageType.ResponseList, NamooChatConsts.SENDER_SERVER);
	}
	
	private List<Member> memberList;

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
}
