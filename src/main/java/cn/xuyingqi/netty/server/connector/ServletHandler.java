package cn.xuyingqi.netty.server.connector;

import java.util.List;

import cn.xuyingqi.netty.server.container.ServletContainer;
import cn.xuyingqi.netty.server.servlet.Servlet;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Servlet处理类
 * 
 * @author XuYQ
 *
 */
public class ServletHandler extends ChannelHandlerAdapter {

	/**
	 * Servlet集合
	 */
	private List<Servlet> servlets = ServletContainer.getInstance().getAllServlets();

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常则关闭连接
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端连接");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端断开连接");
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		for (int i = 0, length = servlets.size(); i < length; i++) {

			servlets.get(i).service(null, null);
		}

		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
}
