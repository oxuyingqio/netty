package cn.xuyingqi.netty.server.servlet.facade;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.netty.server.servlet.ServerServletResponse;

/**
 * ServerServlet响应外观类
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponseFacade implements ServletResponse {

	private ServerServletResponse response;

	/**
	 * ServerServlet响应外观类
	 * 
	 * @param response
	 */
	public ServerServletResponseFacade(ServerServletResponse response) {

		this.response = response;
	}

	@Override
	public ServletRequest getServletRequest() {

		return this.response.getServletRequest();
	}

	@Override
	public ServletResponse addHeader(String name, Object value) {

		return this.response.addHeader(name, value);
	}

	@Override
	public boolean containsHeader(String name) {

		return this.response.containsHeader(name);
	}

	@Override
	public ServletResponse setHeader(String name, Object value) {

		return this.response.setHeader(name, value);
	}

	@Override
	public ServletResponse addParameter(String name, Object value) {

		return this.response.addParameter(name, value);
	}

	@Override
	public boolean containsParameter(String name) {

		return this.response.containsParameter(name);
	}

	@Override
	public ServletResponse setParameter(String name, Object value) {

		return this.response.setParameter(name, value);
	}
}
