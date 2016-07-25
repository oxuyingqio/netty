package cn.xuyingqi.netty.server.selfservice.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.servlet.ServerServletRequest;
import cn.xuyingqi.netty.server.servlet.ServerServletResponse;

/**
 * 自助缴费Servlet
 * 
 * @author XuYQ
 *
 */
public class SelfServiceServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	/**
	 * 服务
	 * 
	 * @param request
	 * @param response
	 */
	private void service(ServerServletRequest request, ServerServletResponse response) {

		System.out.println(request.getContentLength());
	}

	@Override
	public void destroy() {

	}

	@Override
	public String getServletInfo() {
		return null;
	}
}
