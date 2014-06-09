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
			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				ConnectedClient client = new ConnectedClient(socket, this);
				Thread thread = new Thread(client);
				thread.start();
				
				String clientAddress = socket.getInetAddress().getHostAddress();
				System.out.println("Ŭ���̾�Ʈ["+clientAddress+"]�� �����Ͽ����ϴ�.");
			}
			
		} catch (IOException e) {
			// 
			System.out.println("Exception cause!! " + e.getClass());
		} finally {
			// ��� Ŭ���̾�Ʈ�� ��������
			for (ConnectedClient client : clientsMap.values()) {
				client.close();
			}
			// ���� ���� �ݱ�
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
		case SimpleMessage: // �ܹ��޽���
			handleSimpleMessage(message);
			break;
		case DirectMessage: // �ӼӸ�
			handleDirectMessage(message);
			System.out.println();
			break;
		case RequestList: // ä�������� �����ȸ ��û
			handleRequestList();
			break;
		default:
			break;
		}
	}

	private void handleDirectMessage(Message message) {
		//
		System.out.println(message.getSender() +"���� "+message.getReceiver()+"�Բ� ������ �ӼӸ� : " + message.getBody());

		ConnectedClient receiver = clientsMap.get(message.getReceiver());
		if (receiver != null) {
			receiver.sendMessage(message);
		}
		
		// sender���Ե� ����
		ConnectedClient sender = clientsMap.get(message.getSender());
		if (sender != null && !receiver.equals(sender)) {
			sender.sendMessage(message);
		}
	}

	private void handleSimpleMessage(Message message) {
		//
		System.out.println(message.getSender() +"���� �޽��� : " + message.getBody());
		broadcast(message);
	}

	private void handleRequestList() {
		// 
		// ��ȭ�� ������ ��� ����
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
			rejectMsg.setBody("�ش� �г���["+nickname+"]�� �̹� ��� ���Դϴ�. �ٸ� �г������� �����ϼ���.");
			client.sendMessage(rejectMsg);
			client.close();
			return;
		}
		
		synchronized (clientsMap) {
			clientsMap.put(client.getNickName(), client);
		}
		
		System.out.println(client.getNickName() + "���� ��ȭ�濡 �����Ͽ����ϴ�.");
		
		// ����� ������ ����� ä�� �����ڿ��� ����
		handleRequestList();
	}
	
	@Override
	public void disconnect(ConnectedClient client) {
		// 
		synchronized (clientsMap) {
			clientsMap.remove(client.getNickName());
		}
		
		System.out.println(client.getNickName() + "���� ��ȭ�濡�� �������ϴ�.");
		System.out.println("���� ������ ["+clientsMap.keySet().size()+"��] : " + clientsMap.keySet());
		
		// ����� ������ ����� ä�� �����ڿ��� ����
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
