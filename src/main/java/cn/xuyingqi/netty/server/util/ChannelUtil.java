package cn.xuyingqi.netty.server.util;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.server.connector.Constant;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * 通道工具类
 * 
 * @author XuYQ
 *
 */
public class ChannelUtil {

	/**
	 * 通道
	 */
	private Channel channel;

	/**
	 * 通道工具类
	 * 
	 * @param channel
	 */
	public ChannelUtil(Channel channel) {
		this.channel = channel;
	}

	/**
	 * 获取ServletSession会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession() {

		return (ServletSession) this.channel.attr(AttributeKey.valueOf(Constant.SERVLET_SESSION)).get();
	}
}
