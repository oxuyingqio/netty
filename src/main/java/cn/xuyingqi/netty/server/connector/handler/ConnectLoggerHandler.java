package cn.xuyingqi.netty.server.connector.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 连接日志
 * 
 * @author XuYQ
 *
 */
public final class ConnectLoggerHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 打印日志
		LOGGER.info("\n【Netty】[服务器-连接日志]远程地址({})已连接.", ctx.channel().remoteAddress());

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 打印日志
		LOGGER.info("\n【Netty】[服务器-连接日志]远程地址({})已断开.", ctx.channel().remoteAddress());

		// 后续处理
		ctx.fireChannelInactive();
	}
}