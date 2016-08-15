package cn.xuyingqi.netty.server.echo.servlet;

import java.io.UnsupportedEncodingException;

import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import io.netty.buffer.Unpooled;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	private void service(ServerServletRequest request, ServerServletResponse response) {
		
		System.out.println("来了========");
		
		try {
			Thread.sleep(3000);

			byte[] data = "这是中间说的话".getBytes("GBK");
			ChannelContainer.getInstance().getChannel(request.getServletSessionId())
					.writeAndFlush(Unpooled.buffer(data.length).writeBytes(data));
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
