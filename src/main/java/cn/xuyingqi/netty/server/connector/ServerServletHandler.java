package cn.xuyingqi.netty.server.connector;

import java.util.Iterator;

import cn.xuyingqi.net.server.connector.ServletHandler;
import cn.xuyingqi.net.server.container.ServletContainer;
import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.servlet.ServerServletRequest;
import cn.xuyingqi.netty.server.servlet.ServerServletResponse;
import cn.xuyingqi.netty.server.servlet.ServerServletSession;
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
public class ServerServletHandler extends ChannelHandlerAdapter implements ServletHandler {

	/**
	 * Servlet容器
	 */
	private ServletContainer servletContainer;

	/**
	 * 属性值:session
	 */
	private AttributeKey<ServerServletSession> sessionKey = AttributeKey.valueOf("session");

	@Override
	public void init(ServletContainer servletContainer) {

		// 获取Servlet容器
		this.servletContainer = servletContainer;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常则关闭连接
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// Servlet上下文
		ServletContext context = null;

		// 获取Servlet名称集合
		Iterator<String> it = this.servletContainer.getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 获取Servlet上下文
			context = this.servletContainer.getServlet(it.next()).getServletConfig().getServletContext();
		}

		// 创建session对象
		ServerServletSession session = new ServerServletSession(context, ctx.channel().localAddress(),
				ctx.channel().remoteAddress());

		// 设置该链接的session属性
		Attribute<ServerServletSession> attr = ctx.attr(sessionKey);
		attr.set(session);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 创建请求
		ServletRequest request = new ServerServletRequest(ctx.attr(sessionKey).get(), (ServerDatagram) msg);
		// 创建响应
		ServletResponse response = new ServerServletResponse(request, ((ServerDatagram) msg).newInstance());

		// 获取Servlet名称集合
		Iterator<String> it = this.servletContainer.getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 调用Servlet
			this.servletContainer.getServlet(it.next()).service(request, response);
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
}
