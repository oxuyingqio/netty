package cn.xuyingqi.netty.server.connector.handler;

import java.util.Iterator;

import cn.xuyingqi.net.server.container.ServletContainer;
import cn.xuyingqi.net.server.servlet.ServletHandler;
import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.protocol.datagram.Datagram;
import cn.xuyingqi.netty.servlet.facade.ServerServletRequestFacade;
import cn.xuyingqi.netty.servlet.facade.ServerServletResponseFacade;
import cn.xuyingqi.netty.servlet.facade.ServletSessionFacade;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletRequest;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletResponse;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Servlet处理
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
	 * 属性:会话ID
	 */
	private AttributeKey<String> sessionIdAttr = AttributeKey.valueOf("sessionId");

	/**
	 * 属性值:session
	 */
	private AttributeKey<DefaultServletSession> sessionAttr = AttributeKey.valueOf("session");

	@Override
	public void init(ServletContainer servletContainer) {

		// 获取Servlet容器
		this.servletContainer = servletContainer;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话ID属性
		Attribute<String> sessionIdAttr = ctx.attr(this.sessionIdAttr);
		// 获取会话ID
		String sessionId = sessionIdAttr.get();

		// 设置该链接的session属性
		Attribute<DefaultServletSession> attr = ctx.attr(sessionAttr);

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
		DefaultServletSession serverSession = new DefaultServletSession(context, sessionId,
				ctx.channel().localAddress(), ctx.channel().remoteAddress());

		attr.set(serverSession);

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

		// 服务会话
		DefaultServletSession session = ctx.attr(sessionAttr).get();
		// 修改最后一次请求时间
		session.updateLastAccessedTime();
		// 会话外观类
		ServletSession sessionFacade = new ServletSessionFacade(session);

		// 服务请求
		DefaultServerServletRequest request = new DefaultServerServletRequest(sessionFacade, (Datagram) msg);
		// 请求外观类
		ServerServletRequest requestFacade = new ServerServletRequestFacade(request);

		// 服务响应
		DefaultServerServletResponse response = new DefaultServerServletResponse(requestFacade,
				((Datagram) msg).newResponse());
		// 响应外观类
		ServerServletResponse responseFacade = new ServerServletResponseFacade(response);

		// 获取Servlet名称集合
		Iterator<String> it = this.servletContainer.getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 调用Servlet
			this.servletContainer.getServlet(it.next()).service(requestFacade, responseFacade);
		}
		// 写入响应数据报文
		ctx.write(response.getDatagram());

		// 后续处理
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		System.out.println("ServerServletHandler=================================");

		ctx.fireExceptionCaught(cause);
	}
}
