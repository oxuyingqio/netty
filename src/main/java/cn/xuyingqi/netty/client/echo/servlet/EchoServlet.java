package cn.xuyingqi.netty.client.echo.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;

/**
 * 应答Servlet
 * 
 * @author XuYQ
 *
 */
public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		System.out.println("来了");
	}

	@Override
	public void destroy() {

	}
}
