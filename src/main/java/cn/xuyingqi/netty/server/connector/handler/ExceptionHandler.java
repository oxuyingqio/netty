package cn.xuyingqi.netty.server.connector.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 异常处理
 * 
 * @author XuYQ
 *
 */
public class ExceptionHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 打印日志
		LOGGER.error("远程地址(" + ctx.channel().remoteAddress() + ")程序异常,将中断连接.异常信息:" + cause.getMessage());
		// 打印异常
		cause.printStackTrace();

		// 关闭连接
		ctx.close();
	}
}