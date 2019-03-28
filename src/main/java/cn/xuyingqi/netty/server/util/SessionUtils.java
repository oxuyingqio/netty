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
public final class SessionUtils {

	/**
	 * 私有构造方法
	 */
	private SessionUtils() {

	}

	/**
	 * 获取会话通道
	 * 
	 * @param session
	 *            会话ID
	 * @return
	 */
	public static final Channel getChannel(String session) {

		return ChannelContainer.getInstance().getChannel(session);
	}

	/**
	 * 获取ServletSession会话
	 * 
	 * @param session
	 *            会话ID
	 * @return
	 */
	public static final ServletSession getServletSession(String session) {

		return SessionUtils.getChannel(session) == null ? null
				: (ServletSession) SessionUtils.getChannel(session).attr(AttributeKey.valueOf(Constant.SESSION)).get();
	}
}