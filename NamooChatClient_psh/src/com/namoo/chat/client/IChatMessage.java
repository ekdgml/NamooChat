package com.namoo.chat.client;

import java.util.List;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.Message;

public interface IChatMessage {
	
	void appendMessage(Message message);

	void refreshList(List<Member> memberList);

}


