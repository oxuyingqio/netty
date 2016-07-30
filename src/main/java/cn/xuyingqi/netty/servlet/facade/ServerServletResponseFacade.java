package cn.xuyingqi.netty.servlet.facade;

import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.netty.servlet.impl.DefaultServerServletResponse;

/**
 * 服务器端Servlet响应外观类
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponseFacade implements ServerServletResponse {

	/**
	 * 服务器端Servlet响应
	 */
	private ServerServletResponse response;

	/**
	 * 服务器端Servlet响应外观类
	 * 
	 * @param response
	 *            Servlet响应
	 */
	public ServerServletResponseFacade(DefaultServerServletResponse response) {

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
	public ServletResponse addPayload(String name, Object value) {

		return this.response.addPayload(name, value);
	}

	@Override
	public boolean containsPayload(String name) {

		return this.response.containsPayload(name);
	}

	@Override
	public ServletResponse setPayload(String name, Object value) {

		return this.response.setPayload(name, value);
	}
}
