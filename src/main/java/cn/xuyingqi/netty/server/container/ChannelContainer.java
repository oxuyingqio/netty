package cn.xuyingqi.netty.server.container;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;
import io.netty.channel.Channel;

/**
 * 客户端通道容器
 * 
 * @author XuYQ
 *
 */
public final class ChannelContainer {

	/**
	 * 客户端通道容器
	 */
	private static ChannelContainer channelContainer;

	/**
	 * 客户端通道列表
	 */
	private Map<String, Channel> channels = MapFactory.newInstance();

	/**
	 * 客户端通道容器
	 */
	private ChannelContainer() {

	}

	/**
	 * 获取客户端通道容器
	 * 
	 * @return
	 */
	public static ChannelContainer getInstance() {

		if (channelContainer == null) {
			channelContainer = new ChannelContainer();
		}

		return channelContainer;
	}

	/**
	 * 添加客户端通道
	 * 
	 * @return
	 */
	public ChannelContainer addChannel(String id, Channel channel) {

		channels.put(id, channel);

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

		channels.remove(id);

		return this;
	}
}
