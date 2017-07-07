package cn.xuyingqi.netty.server.container;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;
import io.netty.channel.Channel;

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
	private static ChannelContainer container;

	/**
	 * 通道集合
	 */
	private Map<String, Channel> channels = MapFactory.newInstance();

	/**
	 * 通道容器
	 */
	private ChannelContainer() {

	}

	/**
	 * 获取通道容器
	 * 
	 * @return
	 */
	public static final synchronized ChannelContainer getInstance() {

		if (container == null) {

			container = new ChannelContainer();
		}

		return container;
	}

	/**
	 * 添加客户端通道
	 * 
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