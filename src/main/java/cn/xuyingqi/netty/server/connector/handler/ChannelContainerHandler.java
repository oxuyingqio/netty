package cn.xuyingqi.netty.server.connector.handler;

import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 远程链接通道
 * 
 * @author XuYQ
 *
 */
public class ChannelContainerHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	/**
	 * 属性:Servlet会话
	 */
	private static final AttributeKey<DefaultServletSession> SERVLET_SESSION = AttributeKey
			.valueOf(Constant.SERVLET_SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<DefaultServletSession> servletSessionAttr = ctx.attr(ChannelContainerHandler.SERVLET_SESSION);
		// 获取会话
		DefaultServletSession servletSession = servletSessionAttr.get();

		// 添加客户端通道
		ChannelContainer.getInstance().addChannel(servletSession.getId(), ctx.channel());
		// 打印日志
		LOGGER.info("新增通道(ID:{};通道:{}).", servletSession.getId(), ctx.channel());

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<DefaultServletSession> servletSessionAttr = ctx.attr(ChannelContainerHandler.SERVLET_SESSION);
		// 获取会话
		DefaultServletSession servletSession = servletSessionAttr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(servletSession.getId());
		// 打印日志
		LOGGER.info("移除通道(ID:" + servletSession.getId() + ";通道:" + ctx.channel() + ").");

		// 后续处理
		ctx.fireChannelInactive();
	}
}