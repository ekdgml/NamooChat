package com.namoo.chat.client.thread;

import com.namoo.chat.client.IChatRoom;
import com.namoo.chat.client.socket.ChatClient;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;

public class MessageReceiver implements Runnable {
	//
	private ChatClient client;
	private IChatRoom chatRoom;
	
	public MessageReceiver(ChatClient client, IChatRoom chatRoom) {
		//
		this.client = client;
		this.chatRoom = chatRoom;
	}
	
	@Override
	public void run() {
		//
		while (true) {
			//
			if (client.isConnected() == false) break;
			
			Message message = client.receiveMessage();
			if (message != null) {
				System.out.println("received message " + message.getType());
				System.out.println(" - body : " + message.getBody());
				switch (message.getType()) {
				case SimpleMessage:
				case DirectMessage:
				case AcceptJoin:
				case RejectJoin:
				case Notice:
					this.chatRoom.appendMessage(message);
					break;
				case ResponseList:
					MemberListMessage memberListMsg = (MemberListMessage) message;
					this.chatRoom.refreshParticipants(memberListMsg.getMemberList());
					break;
				default:
					break;
				}
			}
		}
		System.out.println("MessageReceiver is finished.");
	}
}
