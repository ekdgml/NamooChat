package com.namoo.chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;
import com.namoo.chat.core.NamooChatConsts;

public class ConnectedClient implements Runnable {
	
	private ChatBroker chatBroker;
	private Member member;
	private Socket socket;
	private	ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ConnectedClient(Socket socket, ChatBroker chatBroker) {
		//
		this.chatBroker = chatBroker;
		this.socket = socket;
	}
	
	public ConnectedClient(String nickName, Socket socket, ChatBroker chatBroker) {
		//
		this(socket, chatBroker);
		this.member = new Member(nickName);
	}

	@Override
	public void run() {
		// 
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());

			// 첫 메시지는 Client의 접속요청 메시지이어야 한다.
			boolean isAccepted = acceptClient(ois, oos);
			if (isAccepted == false) {
				this.socket.close();
				return;
			}
			
			// 
			while (true) {
				Message message = (Message) ois.readObject();
				
				if (message.getType() == MessageType.Exit) {
					chatBroker.disconnect(this);
				} else {
					this.chatBroker.delegateMessage(message);
				}
				
			}
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("[" + member.getNickName() + "] Exception cause!! " + e.getClass());
			
			if (e instanceof StreamCorruptedException) {
				e.printStackTrace();
			}
			
			this.chatBroker.disconnect(this);
		}
	}

	private boolean acceptClient(ObjectInputStream ois, ObjectOutputStream oos) {
		//
		try {
			Message receivedMessage = (Message) ois.readObject();
			if (receivedMessage.getType() == MessageType.RequestJoin) {
				//
				Member member = new Member(receivedMessage.getSender());
				this.member = member;
				
				Message message = new Message(MessageType.AcceptJoin, NamooChatConsts.SENDER_SERVER);
				message.setBody(member.getNickName() + "님 환영합니다. 대화방에 참여하였습니다.");
				oos.writeObject(message);
				oos.flush();
				
				chatBroker.joinRoom(this);
				return true;
			} else {
				//
				Message message = new Message(MessageType.RejectJoin, NamooChatConsts.SENDER_SERVER);
				message.setBody("대화방에 참가하려면 RequestJoin 메시지를 보내야 합니다.");
				
				oos.writeObject(message);
				oos.flush();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("["+getNickName()+"] + Exception cause!! " + e.getClass());
		} catch (IOException e) {
			System.out.println("["+getNickName()+"] + Exception cause!! " + e.getClass());
		}
		return false;
	}
	
	public void close() {
		//
		IOUtils.closeQuietly(socket);
	}

	public String getNickName() {
		// 
		return (member != null) ? member.getNickName() : null;
	}

	public void sendMessage(Message message) {
		// 
		synchronized (oos) {
			try {
				oos.writeObject(message);
				oos.flush();
			} catch (IOException e) {
				System.out.println("["+getNickName()+"] Exception cause!! " + e.getClass());
			}
		}
	}
	

}
