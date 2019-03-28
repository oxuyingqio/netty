package cn.xuyingqi.netty.server.container;

import java.util.Map;

import io.netty.channel.Channel;
import cn.xuyingqi.util.MapFactory;

/**
 * 通道容器
 * 
 * @author XuYQ
 *
 */
public final class ChannelContainer {

	/**
	 * 容器
	 */
	private static ChannelContainer CONTAINER;

	/**
	 * 通道集合
	 */
	private Map<String, Channel> channels;

	/**
	 * 通道容器
	 */
	private ChannelContainer() {

		this.channels = MapFactory.newInstance();
	}

	/**
	 * 获取通道容器
	 * 
	 * @return
	 */
	public synchronized static final ChannelContainer getInstance() {

		if (CONTAINER == null) {

			CONTAINER = new ChannelContainer();
		}

		return CONTAINER;
	}

	/**
	 * 添加客户端通道
	 * 
	 * @param id
	 * @param channel
	 * @return
	 */
	public ChannelContainer addChannel(String id, Channel channel) {

		this.channels.put(id, channel);

		return this;
	}

	/**
	 * 获取客户端通道
	 * 
	 * @param id
	 * @return
	 */
	public Channel getChannel(String id) {

		return channels.get(id);
	}

	/**
	 * 移除客户端通道
	 * 
	 * @param id
	 * @return
	 */
	public ChannelContainer removeChannel(String id) {

		this.channels.remove(id);

		return this;
	}
}