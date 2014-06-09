package com.namoo.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;
import com.namoo.chat.core.NamooChatConsts;

public class ChatServer implements ChatBroker {

	private static final int PORT = 9090;
	private Map<String, ConnectedClient> clientsMap;

	//------------------------------------------------------------------
	
	public ChatServer() {
		//
		clientsMap = new HashMap<String, ConnectedClient>();
	}

	//------------------------------------------------------------------
	
	public void start() {
		//
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				ConnectedClient client = new ConnectedClient(socket, this);
				Thread thread = new Thread(client);
				thread.start();
				
				String clientAddress = socket.getInetAddress().getHostAddress();
				System.out.println("클라이언트["+clientAddress+"]가 접속하였습니다.");
			}
			
		} catch (IOException e) {
			// 
			System.out.println("Exception cause!! " + e.getClass());
		} finally {
			// 모든 클라이언트와 연결해제
			for (ConnectedClient client : clientsMap.values()) {
				client.close();
			}
			// 서버 소켓 닫기
			IOUtils.closeQuietly(serverSocket);
		}
	}
	
	private void broadcast(Message message) {
		//
		for (ConnectedClient client : clientsMap.values()) {
			client.sendMessage(message);
		}
	}
	
	//----------------------------------------------------------
	// ChatBroker implementation
	
	@Override
	public void delegateMessage(Message message) {
		//
		System.out.println("["+message.getSender()+"] " + message.getType());
		switch (message.getType()) {
		case SimpleMessage: // 단문메시지
			handleSimpleMessage(message);
			break;
		case DirectMessage: // 귓속말
			handleDirectMessage(message);
			System.out.println();
			break;
		case RequestList: // 채팅참가자 목록조회 요청
			handleRequestList();
			break;
		default:
			break;
		}
	}

	private void handleDirectMessage(Message message) {
		//
		System.out.println(message.getSender() +"님이 "+message.getReceiver()+"님께 보내는 귓속말 : " + message.getBody());

		ConnectedClient receiver = clientsMap.get(message.getReceiver());
		if (receiver != null) {
			receiver.sendMessage(message);
		}
		
		// sender에게도 보냄
		ConnectedClient sender = clientsMap.get(message.getSender());
		if (sender != null && !receiver.equals(sender)) {
			sender.sendMessage(message);
		}
	}

	private void handleSimpleMessage(Message message) {
		//
		System.out.println(message.getSender() +"님의 메시지 : " + message.getBody());
		broadcast(message);
	}

	private void handleRequestList() {
		// 
		// 대화방 접속자 목록 구성
		List<Member> memberList = new ArrayList<>();
		for (ConnectedClient client : clientsMap.values()) {
			//
			memberList.add(new Member(client.getNickName()));
		}
		
		MemberListMessage listMessage = new MemberListMessage();
		listMessage.setMemberList(memberList);
		
		broadcast(listMessage);
	}

	@Override
	public void joinRoom(ConnectedClient client) {
		//
		String nickname = client.getNickName();
		
		if (clientsMap.containsKey(nickname)) {
			//
			Message rejectMsg = new Message(MessageType.RejectJoin, NamooChatConsts.SENDER_SERVER);
			rejectMsg.setBody("해당 닉네임["+nickname+"]은 이미 사용 중입니다. 다른 닉네임으로 접속하세요.");
			client.sendMessage(rejectMsg);
			client.close();
			return;
		}
		
		synchronized (clientsMap) {
			clientsMap.put(client.getNickName(), client);
		}
		
		System.out.println(client.getNickName() + "님이 대화방에 접속하였습니다.");
		
		// 변경된 참가자 목록을 채팅 참여자에게 전송
		handleRequestList();
	}
	
	@Override
	public void disconnect(ConnectedClient client) {
		// 
		synchronized (clientsMap) {
			clientsMap.remove(client.getNickName());
		}
		
		System.out.println(client.getNickName() + "님이 대화방에서 나갔습니다.");
		System.out.println("현재 접속자 ["+clientsMap.keySet().size()+"명] : " + clientsMap.keySet());
		
		// 변경된 참가자 목록을 채팅 참여자에게 전송
		handleRequestList();
	}
	
	//----------------------------------------------------------
	// Runner
	
	public static void main(String[] args) {
		//
		ChatServer server = new ChatServer();
		server.start();
	}

}
