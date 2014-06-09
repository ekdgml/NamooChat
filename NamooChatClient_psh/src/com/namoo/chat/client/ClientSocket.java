package com.namoo.chat.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.namoo.chat.core.Message;

public class ClientSocket {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ClientSocket(Socket socket) {
		//
		this.socket = socket;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObjectOutputStream getOos() {
		return oos;
	}

	public ObjectInputStream getOis() {
		return ois;
	}

	public void sendMessage(Message message) {
		//
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message readMessage() {

		// 세마포어! 한번에 한사람만 쓸수있도록.
		synchronized (ois) {
			try {
				return (Message) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			return null;

		}

	}

}
