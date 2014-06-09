package com.namoo.chat.core;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = -1600654252374340460L;

	private MessageType type;
	private String sender;
	private String receiver;
	private String body;
	
	public Message(MessageType type) {
		//
		this.type = type;
	}

	public Message(MessageType type, String sender) {
		//
		this.type = type;
		this.sender = sender;
	}
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
