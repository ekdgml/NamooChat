package com.namoo.chat.client.actions;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import com.namoo.chat.client.IChatRoom;
import com.namoo.chat.client.socket.ChatClient;
import com.namoo.chat.client.thread.MessageReceiver;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class ConnectAction extends Action {
	//
	private ChatClient chatClient;
	private IChatRoom chatRoom;
	
	public ConnectAction(ChatClient chatClient, IChatRoom chatRoom) {
		//
		super("&Connect@Ctrl+O");
		this.chatClient = chatClient;
		this.chatRoom = chatRoom;
		
		setToolTipText("Connect");
		try {
			setImageDescriptor(ImageDescriptor.createFromURL(new URL("file:icons/connect.ico")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//
		if (chatClient.isConnected()) {
			chatClient.disconnect();
		}
		
		InputDialog dialog = new InputDialog(Display.getCurrent().getActiveShell(), 
				"Connect to server", 
				"Enter your nickname", "", null);
		
		if (dialog.open() == Window.OK) {
			//
			String nickname = dialog.getValue();
			if (chatClient.connectServer(nickname)) {
				//
				// join process handshake
				// step1. send RequestJoin message
				Message joinMsg = new Message(MessageType.RequestJoin);
				chatClient.sendMessage(joinMsg);
				
				// step2. receive result (AcceptJoin or RejectJoin)
				Message respMsg = chatClient.receiveMessage();
				if (respMsg.getType() == MessageType.RejectJoin) {
					MessageDialog.openError(
							Display.getCurrent().getActiveShell(), 
							"Error", 
							respMsg.getBody());
				} else {
					chatRoom.appendMessage(respMsg);
				}
				
				Thread thread = new Thread(new MessageReceiver(chatClient, chatRoom));
				thread.setDaemon(true);
				thread.start();
				
				// 대화방 참가자 목록 요청
				Message message = new Message(MessageType.RequestList);
				chatClient.sendMessage(message);
				
				// menu control
				chatRoom.disableConnectionMenu();
				
			} else {
				//
				MessageDialog.openError(
						Display.getCurrent().getActiveShell(), 
						"Error", 
						"Connect to server is failed!");
				
				// menu control
				chatRoom.enableConnectionMenu();
			}
		}
	}
}
