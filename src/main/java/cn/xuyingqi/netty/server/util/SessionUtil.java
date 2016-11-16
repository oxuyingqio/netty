package cn.xuyingqi.netty.server.util;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * 会话工具类
 * 
 * @author XuYQ
 *
 */
public class SessionUtil {

	/**
	 * 会话ID
	 */
	private String session;

	/**
	 * 会话工具类
	 * 
	 * @param session
	 *            session ID
	 */
	public SessionUtil(String session) {
		this.session = session;
	}

	/**
	 * 获取会话通道
	 * 
	 * @return
	 */
	public Channel getChannel() {

		return ChannelContainer.getInstance().getChannel(this.session);
	}

	/**
	 * 获取ServletSession会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession() {

		return (ServletSession) this.getChannel().attr(AttributeKey.valueOf(Constant.SERVLET_SESSION)).get();
	}
}
