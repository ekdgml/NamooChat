package com.namoo.chat.server;

import com.namoo.chat.core.Message;

public interface ChatBroker {
	
	void delegateMessage(Message message);

	void joinRoom(ConnectedClient client);

	void disconnect(ConnectedClient connectedClient);
	
}
