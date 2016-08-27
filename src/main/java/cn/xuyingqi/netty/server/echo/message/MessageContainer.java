package cn.xuyingqi.netty.server.echo.message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import io.netty.channel.Channel;

public class MessageContainer {

	private static MessageContainer messageContainer;

	private MessageContainer() {

		channelMessage = new HashMap<Channel, Queue<Message>>();
	}

	public static final MessageContainer getInstance() {

		if (messageContainer == null) {
			messageContainer = new MessageContainer();
		}

		return messageContainer;
	}
	
	private Map<Channel, Queue<Message>> channelMessage;

	public void add(Channel channel, Message message) {

		Queue<Message> messages = channelMessage.get(channel);
		if (messages == null) {
			messages = new LinkedList<Message>();
			channelMessage.put(channel, messages);
		}

		while (messages.size() >= 10) {
			System.out.println("移除了消息");
			messages.poll();
		}

		System.out.println("添加了消息");
		messages.add(message);
	}
	
	
	public Queue<Message> getMessages(Channel channel){
		
		return channelMessage.get(channel);
	}
	
}
