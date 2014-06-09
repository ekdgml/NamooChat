package com.namoo.chat.client.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

import com.namoo.chat.client.IChatRoom;
import com.namoo.chat.client.socket.ChatClient;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class DisconnectAction extends Action{
	//
	private ChatClient chatClient;
	private IChatRoom chatRoom;
	
	public DisconnectAction(ChatClient chatClient, IChatRoom chatRoom) {
		//
		super("&Disconnect@Ctrl+D");
		this.chatClient = chatClient;
		this.chatRoom = chatRoom;
		
		setToolTipText("Disconnect");
		try {
			setImageDescriptor(ImageDescriptor.createFromURL(new URL("file:icons/disconnect.ico")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//
		if (chatClient.isConnected()) {
			chatClient.disconnect();
			
			Message message = new Message(MessageType.SimpleMessage);
			message.setBody("Disconnected...");
			chatRoom.appendMessage(message);
			chatRoom.refreshParticipants(null);
			chatRoom.enableConnectionMenu();
		}
		
	}
}
