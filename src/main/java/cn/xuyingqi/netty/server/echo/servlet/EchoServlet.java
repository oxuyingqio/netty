package cn.xuyingqi.netty.server.echo.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) {

		throw new RuntimeException("测试异常");
	}

	@Override
	public void destroy() {

	}
}
