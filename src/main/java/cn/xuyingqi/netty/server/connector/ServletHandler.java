package cn.xuyingqi.netty.server.connector;

import java.util.List;
import java.util.Random;

import cn.xuyingqi.netty.server.container.ServletContainer;
import cn.xuyingqi.netty.server.servlet.Servlet;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

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

	/**
	 * 属性session
	 */
	private AttributeKey<Integer> sessionKey = AttributeKey.valueOf("session");

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常则关闭连接
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端连接");

		Attribute<Integer> attr = ctx.attr(sessionKey);
		int i = new Random().nextInt();
		attr.set(i);
		System.out.println("给你这个客户端分配的是" + i);

		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端断开连接");
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("我这个客户端的是" + ctx.attr(sessionKey).get());

		// 遍历Servlet集合
		for (int i = 0, length = servlets.size(); i < length; i++) {

			// 调用Servlet
			servlets.get(i).service(null, null);
		}

		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
}
