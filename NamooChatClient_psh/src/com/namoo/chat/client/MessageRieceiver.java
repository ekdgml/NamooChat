package com.namoo.chat.client;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class MessageRieceiver implements Runnable {
	
	private ClientSocket clientSocket; 
	private IChatMessage chatMessage;
	
	public MessageRieceiver(ClientSocket clientSocket, IChatMessage chatMessage) {
		//
		this.clientSocket = clientSocket;
		this.chatMessage = chatMessage;
	}
	
	@Override
	public void run() {
		//
		while (true) {
			//
			Message message = clientSocket.readMessage();
			if (message != null) {
				if (message.getType() == MessageType.ResponseList) {
					// 서버에서 전송된 멤버목록
					MemberListMessage memberListMsg = (MemberListMessage) message;
					chatMessage.refreshList(memberListMsg.getMemberList());
				} else {
					chatMessage.appendMessage(message);
				}
			}
		}
			
		
	}


}
