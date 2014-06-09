package com.namoo.chat.client.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Properties;

import com.namoo.chat.core.Message;

public class ChatClient {
	//
	private String SERVER_HOST;
	private int SERVER_PORT;
	
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private boolean connected;
	private String sender;
	
	public ChatClient() {
		//
		Properties prop = new Properties();
		try {
			prop.load(new URL("file:conf/config.properties").openStream());
			SERVER_HOST = (String) prop.get("server.host");
			SERVER_PORT = Integer.parseInt((String) prop.get("server.port"));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("서버 설정정보를 찾을 수 없습니다.");
		}
	}
	
	public boolean connectServer(String sender) {
		//
		this.sender = sender;
		try {
			//
			this.socket = new Socket(SERVER_HOST, SERVER_PORT);
			this.output = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
			this.connected = true;
			
		} catch (IOException e ) {
			e.printStackTrace();
			this.connected = false;
		}
		return connected;
	}

	public boolean sendMessage(Message message) {
		//
		if (connected == true) {
			message.setSender(sender);
			try {
				output.writeObject(message);
				output.flush();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public Message receiveMessage() {
		//
		if (connected) {
			try {
				Object resp = input.readObject();
				return (Message) resp;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void disconnect() {
		//
		if (connected == true) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		connected = false;
	}
	
	public boolean isConnected() {
		//
		return connected;
	}
	
}
