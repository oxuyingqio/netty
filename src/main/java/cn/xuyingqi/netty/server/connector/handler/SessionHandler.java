package cn.xuyingqi.netty.server.connector.handler;

import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.session.Session;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 会话
 * 
 * @author XuYQ
 *
 */
public class SessionHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	/**
	 * 属性Key:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf(Constant.SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(SessionHandler.sessionAttr);
		// 创建一个新的会话,并设置当前会话属性中
		sessionAttr.set(new Session());
		// 打印日志
		this.logger.info("远程地址(" + ctx.channel().remoteAddress() + ")产生会话(" + sessionAttr.get() + ").");

		// 后续处理
		ctx.fireChannelActive();
	}
}
