package cn.xuyingqi.netty.server.selfservice.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.TerminalAuthenticationResponseConstant;

/**
 * 自助缴费Servlet
 * 
 * @author XuYQ
 *
 */
public class SelfServiceServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		response.addParamter(TerminalAuthenticationResponseConstant.WK, "demo");
	}

	@Override
	public void destroy() {

	}
}
