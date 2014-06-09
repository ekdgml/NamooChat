package com.namoo.chat.client;
import java.util.List;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.Message;

public interface IChatRoom {
	//
	void appendMessage(Message message);
	void refreshParticipants(List<Member> members);
	
	// Menu
	void enableConnectionMenu();
	void disableConnectionMenu();
}
