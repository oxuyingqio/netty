package cn.xuyingqi.netty.server.connector.handler;

import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.server.connector.Session;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 客户端通道
 * 
 * @author XuYQ
 *
 */
public class ChannelContainerHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	/**
	 * 属性:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf(Constant.SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(ChannelContainerHandler.sessionAttr);
		// 获取会话
		Session session = sessionAttr.get();

		// 添加客户端通道
		ChannelContainer.getInstance().addChannel(session.getId(), ctx.channel());
		// 打印日志
		this.logger.info("添加通道.\n会话: " + session + ";\n通道: " + ctx.channel());

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(ChannelContainerHandler.sessionAttr);
		// 获取会话
		Session session = sessionAttr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(session.getId());
		// 打印日志
		this.logger.info("移除通道.\n会话: " + session + ";\n通道: " + ctx.channel());

		// 后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(ChannelContainerHandler.sessionAttr);
		// 获取会话
		Session session = sessionAttr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(session.getId());
		// 打印日志
		this.logger.info("移除通道.\n会话: " + session + ";\n通道: " + ctx.channel());

		// 关闭连接
		ctx.close();
	}
}