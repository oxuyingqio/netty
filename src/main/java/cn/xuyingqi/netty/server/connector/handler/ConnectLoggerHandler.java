package cn.xuyingqi.netty.server.connector.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 连接日志记录
 * 
 * @author XuYQ
 *
 */
public class ConnectLoggerHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private final InternalLogger logger = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 打印日志
		this.logger.info("远程地址: " + ctx.channel().remoteAddress() + " 已连接.");

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 打印日志
		this.logger.info("远程地址: " + ctx.channel().remoteAddress() + " 已断开.");

		// 后续处理
		ctx.fireChannelInactive();
	}
}
