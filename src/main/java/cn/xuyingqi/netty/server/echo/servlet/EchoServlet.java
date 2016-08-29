package cn.xuyingqi.netty.server.echo.servlet;

import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	private void service(ServerServletRequest request, ServerServletResponse response) {

		System.out.println("来了========");

		try {
			Thread.sleep(1000);

			String sessionId = request.getServletSessionId();
			ChannelContainer.getInstance().getChannel(request.getServletSessionId()).closeFuture()
					.addListener(new ChannelFutureListener() {

						@Override
						public void operationComplete(ChannelFuture future) throws Exception {
							System.out.println(sessionId + "断开了");
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}
