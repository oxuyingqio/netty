package cn.xuyingqi.netty.server.connector.handler;

import cn.xuyingqi.netty.server.connector.Session;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 会话
 * 
 * @author XuYQ
 *
 */
public class SessionHandler extends ChannelHandlerAdapter {

	/**
	 * 属性:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf("session");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> attr = ctx.attr(sessionAttr);
		// 设置一个新的会话
		attr.set(new Session());

		// 后续处理
		ctx.fireChannelActive();
	}
}
