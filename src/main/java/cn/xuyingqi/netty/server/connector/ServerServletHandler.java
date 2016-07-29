package cn.xuyingqi.netty.server.connector;

import java.util.Iterator;

import cn.xuyingqi.net.server.container.ServletContainer;
import cn.xuyingqi.net.server.servlet.ServletHandler;
import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.protocol.datagram.NettyDatagram;
import cn.xuyingqi.netty.server.servlet.ServerServletRequest;
import cn.xuyingqi.netty.server.servlet.ServerServletResponse;
import cn.xuyingqi.netty.server.servlet.facade.ServerServletRequestFacade;
import cn.xuyingqi.netty.server.servlet.facade.ServerServletResponseFacade;
import cn.xuyingqi.netty.servlet.NettyServletSession;
import cn.xuyingqi.netty.servlet.facade.NettyServletSessionFacade;
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
	private AttributeKey<NettyServletSession> serverSessionKey = AttributeKey.valueOf("session");

	@Override
	public void init(ServletContainer servletContainer) {

		// 获取Servlet容器
		this.servletContainer = servletContainer;
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
		NettyServletSession serverSession = new NettyServletSession(context, ctx.channel().localAddress(),
				ctx.channel().remoteAddress());

		// 设置该链接的session属性
		Attribute<NettyServletSession> attr = ctx.attr(serverSessionKey);
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
		NettyServletSession serverSession = ctx.attr(serverSessionKey).get();
		// 修改最后一次请求时间
		serverSession.updateLastAccessedTime();
		// 会话外观类
		ServletSession session = new NettyServletSessionFacade(serverSession);

		// 服务请求
		ServerServletRequest serverRequest = new ServerServletRequest(session, (NettyDatagram) msg);
		// 请求外观类
		ServletRequest request = new ServerServletRequestFacade(serverRequest);

		// 服务响应
		ServerServletResponse serverResponse = new ServerServletResponse(request, ((NettyDatagram) msg).newResponse());
		// 响应外观类
		ServletResponse response = new ServerServletResponseFacade(serverResponse);

		// 获取Servlet名称集合
		Iterator<String> it = this.servletContainer.getServletNames().iterator();
		// 遍历Servlet名称集合
		while (it.hasNext()) {
			// 调用Servlet
			this.servletContainer.getServlet(it.next()).service(request, response);
		}
		// 写入响应数据报文
		ctx.write(serverResponse.getServerDatagram());

		// 后续处理
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 打印错误信息
		cause.printStackTrace();
		// 关闭连接
		ctx.close();
	}
}
