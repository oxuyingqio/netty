package cn.xuyingqi.netty.server.connector.handler;

import java.util.Iterator;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.container.ServletDescContainer;
import cn.xuyingqi.netty.server.connector.Constant;
import cn.xuyingqi.netty.servlet.facade.ServerServletRequestFacade;
import cn.xuyingqi.netty.servlet.facade.ServerServletResponseFacade;
import cn.xuyingqi.netty.servlet.facade.ServletSessionFacade;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletRequest;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletResponse;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import cn.xuyingqi.netty.session.Session;
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
public final class ServletHandler extends ChannelHandlerAdapter {

	/**
	 * 属性:会话
	 */
	private static AttributeKey<Session> sessionAttr = AttributeKey.valueOf(Constant.SESSION);

	/**
	 * 属性:Servlet会话
	 */
	private static AttributeKey<DefaultServletSession> servletSessionAttr = AttributeKey
			.valueOf(Constant.SERVLET_SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话属性
		Attribute<Session> sessionAttr = ctx.attr(ServletHandler.sessionAttr);
		// 获取会话
		Session session = sessionAttr.get();

		// 创建Servlet会话对象
		DefaultServletSession serverSession = new DefaultServletSession(session.getId(), ctx.channel().localAddress(),
				ctx.channel().remoteAddress());

		// 获取Servlet会话属性
		Attribute<DefaultServletSession> servletSessionAttr = ctx.attr(ServletHandler.servletSessionAttr);
		// 设置Servlet会话
		servletSessionAttr.set(serverSession);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// Servlet会话
		DefaultServletSession servletSession = ctx.attr(servletSessionAttr).get();
		// 修改最后一次请求时间
		servletSession.updateLastAccessedTime();
		// Servlet会话外观类
		ServletSession servletSessionFacade = new ServletSessionFacade(servletSession);

		// Servlet请求
		DefaultServerServletRequest request = new DefaultServerServletRequest(servletSessionFacade, (Datagram) msg);
		// Servlet请求外观类
		ServerServletRequest requestFacade = new ServerServletRequestFacade(request);

		// Servlet响应
		DefaultServerServletResponse response = new DefaultServerServletResponse(requestFacade);
		// Servlet响应外观类
		ServerServletResponse responseFacade = new ServerServletResponseFacade(response);

		// 获取Servlet名称集合
		Iterator<String> it = ServletDescContainer.getInstance().getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 获取当前Servlet
			Servlet servlet = ServletDescContainer.getInstance().getServlet(it.next());
			// Servet会话中设置当前Servlet上下文
			servletSession.setServletContext(servlet.getServletConfig().getServletContext());
			// 调用Servlet服务方法
			servlet.service(requestFacade, responseFacade);
		}
		// 判断响应报文不为空
		if (response.getDatagram() != null) {
			// 写入响应数据报文
			ctx.write(response.getDatagram());
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}
}
