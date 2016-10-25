package cn.xuyingqi.netty.server.echo.servlet;

import java.io.UnsupportedEncodingException;

import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.echo.protocol.EchoDatagram;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	private void service(ServerServletRequest request, ServerServletResponse response) {

		System.out.println("客户端发送消息:" + ((EchoDatagram) request.getDatagram()).getMsg());

		try {
			String msg = "123456";
			int length = msg.getBytes("GBK").length;
			response.setDatagram(new EchoDatagram(length, msg));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}
