package cn.xuyingqi.netty.server.connector.handler;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
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
import cn.xuyingqi.netty.servlet.impl.AbstractNettyServlet;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletRequest;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletResponse;
import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;

/**
 * 应用程序处理
 * 
 * @author XuYQ
 *
 */
public final class ServletHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);
	/**
	 * 属性:会话
	 */
	private static final AttributeKey<DefaultServletSession> SESSION = AttributeKey.valueOf(Constant.SESSION);
	/**
	 * 线程池
	 */
	private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 创建Servlet会话对象
		DefaultServletSession session = new DefaultServletSession(ctx.channel().localAddress(),
				ctx.channel().remoteAddress());
		/**
		 * 设置最大间隔时间,未实现
		 */
		session.setMaxInactiveInterval(0);
		/**
		 * 设置协议名称,未实现
		 */
		session.setProtocol("");

		// 获取会话属性
		Attribute<DefaultServletSession> sessionAttr = ctx.attr(ServletHandler.SESSION);
		// 设置会话
		sessionAttr.set(session);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 打印日志
		LOGGER.info("\n【Netty】[服务器-应用程序处理]开始.");

		// 会话
		DefaultServletSession session = ctx.attr(ServletHandler.SESSION).get();
		// 修改最后一次请求时间
		session.updateLastAccessedTime();
		// Servlet会话外观类
		ServletSession sessionFacade = new ServletSessionFacade(session);

		// Servlet请求
		DefaultServerServletRequest request = new DefaultServerServletRequest(sessionFacade);
		// 设置数据报文
		request.setDatagram((Datagram) msg);
		// Servlet请求外观类
		ServerServletRequest requestFacade = new ServerServletRequestFacade(request);

		// Servlet响应
		DefaultServerServletResponse response = new DefaultServerServletResponse(requestFacade);
		// Servlet响应外观类
		ServerServletResponse responseFacade = new ServerServletResponseFacade(response);

		// 线程池处理
		EXECUTOR.execute(new Runnable() {

			@Override
			public void run() {

				// 打印日志
				LOGGER.info("\n【Netty】[服务器-应用程序处理]新开线程,处理应用程序.");

				// 获取Servlet Key集合
				Iterator<String> iterator = ServletDescContainer.getInstance().getServletDescKeys().iterator();
				// 遍历Servlet Key集合
				while (iterator.hasNext()) {

					// 获取Servlet Key
					String key = iterator.next();

					// 打印日志
					LOGGER.info("\n【Netty】[服务器-应用程序处理]应用程序({}),开始处理.", key);

					// 获取当前Servlet
					Servlet servlet = ServletDescContainer.getInstance().getServlet(key);
					// Servet会话中设置当前Servlet上下文
					session.setServletContext(servlet.getServletConfig().getServletContext());
					// 调用Servlet服务方法
					servlet.service(requestFacade, responseFacade);

					// 打印日志
					LOGGER.info("\n【Netty】[服务器-应用程序处理]应用程序({}),处理结束.", key);
				}
				// 判断响应报文不为空
				if (response.getDatagram() != null) {

					// 写入响应数据报文
					ctx.writeAndFlush(response.getDatagram());
				}
			}
		});

		// 后续处理
		ctx.fireChannelRead(msg);

		// 打印日志
		LOGGER.info("\n【Netty】[服务器-应用程序处理]结束.");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 实例化是否进行后续异常处理
		boolean fireExceptionCaught = true;

		// Servlet会话
		DefaultServletSession session = ctx.attr(ServletHandler.SESSION).get();
		// Servlet会话外观类
		ServletSession sessionFacade = new ServletSessionFacade(session);

		// 获取Servlet名称集合
		Iterator<String> iterator = ServletDescContainer.getInstance().getServletDescKeys().iterator();
		// 遍历Servlet名称集合
		while (iterator.hasNext()) {

			// 获取Servlet Key
			String key = iterator.next();

			// 获取当前Servlet
			Servlet servlet = ServletDescContainer.getInstance().getServlet(key);
			// 判断是否为AbstractNettyServlet
			if (servlet instanceof AbstractNettyServlet) {

				// Servet会话中设置当前Servlet上下文
				session.setServletContext(servlet.getServletConfig().getServletContext());
				// 捕获异常
				((AbstractNettyServlet) servlet).exceptionCaught(sessionFacade, cause);

				// 不再进行后续异常处理
				fireExceptionCaught = false;
			}
		}

		// 后续处理
		if (fireExceptionCaught) {

			ctx.fireExceptionCaught(cause);
		}
	}
}