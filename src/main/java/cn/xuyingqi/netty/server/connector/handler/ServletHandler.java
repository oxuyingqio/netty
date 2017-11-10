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
	 * 属性:Servlet会话
	 */
	private static final AttributeKey<DefaultServletSession> SERVLET_SESSION_ATTR = AttributeKey
			.valueOf(Constant.SERVLET_SESSION);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 创建Servlet会话对象
		DefaultServletSession servletSession = new DefaultServletSession(ctx.channel().localAddress(),
				ctx.channel().remoteAddress());
		/**
		 * 设置最大间隔时间,未实现
		 */
		servletSession.setMaxInactiveInterval(0);
		/**
		 * 设置协议名称,未实现
		 */
		servletSession.setProtocol("");

		// 获取Servlet会话属性
		Attribute<DefaultServletSession> servletSessionAttr = ctx.attr(ServletHandler.SERVLET_SESSION_ATTR);
		// 设置Servlet会话
		servletSessionAttr.set(servletSession);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// Servlet会话
		DefaultServletSession servletSession = ctx.attr(SERVLET_SESSION_ATTR).get();
		// 修改最后一次请求时间
		servletSession.updateLastAccessedTime();
		// Servlet会话外观类
		ServletSession servletSessionFacade = new ServletSessionFacade(servletSession);

		// Servlet请求
		DefaultServerServletRequest request = new DefaultServerServletRequest(servletSessionFacade);
		// 设置数据报文
		request.setDatagram((Datagram) msg);
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
			ctx.writeAndFlush(response.getDatagram());
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}
}