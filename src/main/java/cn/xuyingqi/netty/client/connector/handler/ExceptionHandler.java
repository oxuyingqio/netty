package cn.xuyingqi.netty.client.connector.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

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
public final class ExceptionHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常信息
		StringWriter sw = new StringWriter();
		cause.printStackTrace(new PrintWriter(sw));
		// 打印日志
		LOGGER.error("\n【Netty】[客户端-异常处理]远程地址({})程序异常,将中断连接.异常信息：{}", ctx.channel().remoteAddress(),
				sw.getBuffer().toString());

		// 关闭连接
		ctx.close();
	}
}