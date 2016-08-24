package cn.xuyingqi.netty.server.echo.servlet;

import java.io.UnsupportedEncodingException;

import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	@SuppressWarnings("unchecked")
	private void service(ServerServletRequest request, ServerServletResponse response) {

		System.out.println("来了========");

		try {
			Thread.sleep(1000);

			byte[] data = "hahaha".getBytes("GBK");
			ChannelContainer.getInstance().getChannel(request.getServletSessionId())
					.writeAndFlush(Unpooled.buffer(data.length).writeBytes(data)).addListeners(new ChannelFutureListener(){
						@Override
						public void operationComplete(ChannelFuture future) throws Exception {
							if (future.channel().isOpen() && !future.isSuccess()) {
								System.out.println("Send packet failure");
							}
						}
					});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}
