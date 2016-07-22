package cn.xuyingqi.netty.server.connector;

import java.util.Iterator;
import java.util.Random;

import cn.xuyingqi.net.server.container.ServletContainer;
import cn.xuyingqi.netty.server.container.ServerServletContainer;
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
	 * Servlet容器
	 */
	private ServletContainer servletContainer = ServerServletContainer.getInstance();

	/**
	 * 属性值:session
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

		// 获取该链接的session属性
		Attribute<Integer> attr = ctx.attr(sessionKey);
		// 设置随机数
		int i = new Random().nextInt();
		attr.set(i);
		System.out.println("给你这个客户端分配的是" + i);

		// 继续后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端断开连接");

		// 继续后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 获取session属性值
		System.out.println("我这个客户端的是" + ctx.attr(sessionKey).get());

		// 获取Servlet名称集合
		Iterator<String> it = this.servletContainer.getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 调用Servlet
			this.servletContainer.getServlet(it.next()).service(null, null);
		}

		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
}
