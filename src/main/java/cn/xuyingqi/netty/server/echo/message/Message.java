package cn.xuyingqi.netty.server.echo.message;

import cn.xuyingqi.net.protocol.Datagram;

public class Message {
	
	private MessageType messageType;
	private Datagram datagram;

	public Message(MessageType messageType, Datagram datagram) {
		super();
		this.messageType = messageType;
		this.datagram = datagram;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Datagram getDatagram() {
		return datagram;
	}

	public void setDatagram(Datagram datagram) {
		this.datagram = datagram;
	}
}
