package cn.xuyingqi.netty.client.connector.handler;

import cn.xuyingqi.netty.client.connector.Constant;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import cn.xuyingqi.netty.session.Session;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Servlet处理
 * 
 * @author XuYQ
 *
 */
public final class ServletHandler extends ChannelHandlerAdapter {

	/**
	 * 属性:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf(Constant.SESSION);

	/**
	 * 属性:Servlet会话
	 */
	private static AttributeKey<DefaultServletSession> servletSessionAttr = AttributeKey
			.valueOf(Constant.SERVLET_SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(ServletHandler.sessionAttr);
		// 获取会话
		Session session = sessionAttr.get();

		// 创建Servlet会话对象
		DefaultServletSession serverSession = new DefaultServletSession(session.getId(), ctx.channel().localAddress(),
				ctx.channel().remoteAddress());

		// 获取Servlet会话属性
		Attribute<DefaultServletSession> servletSessionAttr = ctx.attr(ServletHandler.servletSessionAttr);
		// 设置Servlet会话
		servletSessionAttr.set(serverSession);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 后续处理
		ctx.fireChannelRead(msg);
	}
}
