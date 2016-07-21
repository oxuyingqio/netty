package cn.xuyingqi.netty.server.connector;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Servlet处理类
 * 
 * @author XuYQ
 *
 */
public class ServletHandler extends ChannelHandlerAdapter {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常则关闭连接
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端连接");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端断开连接");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
}
