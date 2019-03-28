package cn.xuyingqi.netty.server.connector.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;

/**
 * 连接通道
 * 
 * @author XuYQ
 *
 */
public final class ChannelContainerHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);
	/**
	 * 属性:会话
	 */
	private static final AttributeKey<DefaultServletSession> SESSION = AttributeKey.valueOf(Constant.SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<DefaultServletSession> sessionAttr = ctx.attr(ChannelContainerHandler.SESSION);
		// 获取会话
		DefaultServletSession session = sessionAttr.get();

		// 添加客户端通道
		ChannelContainer.getInstance().addChannel(session.getId(), ctx.channel());
		// 打印日志
		LOGGER.info("\n【Netty】[服务器-连接通道]新增通道(ID：{}；通道：{}).", session.getId(), ctx.channel());

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<DefaultServletSession> sessionAttr = ctx.attr(ChannelContainerHandler.SESSION);
		// 获取会话
		DefaultServletSession session = sessionAttr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(session.getId());
		// 打印日志
		LOGGER.info("\n【Netty】[服务器-连接通道]移除通道(ID：{}；通道：{}).", session.getId(), ctx.channel());

		// 后续处理
		ctx.fireChannelInactive();
	}
}