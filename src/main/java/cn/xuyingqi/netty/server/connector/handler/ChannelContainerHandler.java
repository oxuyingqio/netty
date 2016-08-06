package cn.xuyingqi.netty.server.connector.handler;

import cn.xuyingqi.netty.server.connector.Session;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 客户端通道
 * 
 * @author XuYQ
 *
 */
public class ChannelContainerHandler extends ChannelHandlerAdapter {

	/**
	 * 属性:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf("session");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> attr = ctx.attr(sessionAttr);
		// 获取会话
		Session session = attr.get();

		// 添加客户端通道
		ChannelContainer.getInstance().addChannel(session.getId(), ctx.channel());

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> attr = ctx.attr(sessionAttr);
		// 获取会话
		Session session = attr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(session.getId());

		// 后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 获取会话属性
		Attribute<Session> attr = ctx.attr(sessionAttr);
		// 获取会话
		Session session = attr.get();

		// 移除客户端通道
		ChannelContainer.getInstance().removeChannel(session.getId());

		// 后续处理
		ctx.fireExceptionCaught(cause);
	}
}