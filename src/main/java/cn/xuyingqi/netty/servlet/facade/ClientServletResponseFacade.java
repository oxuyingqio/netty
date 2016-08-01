package cn.xuyingqi.netty.servlet.facade;

import java.util.Set;

import cn.xuyingqi.net.servlet.ClientServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;

/**
 * 客户端Servlet响应外观类
 * 
 * @author XuYQ
 *
 */
public class ClientServletResponseFacade implements ClientServletResponse {

	/**
	 * 客户端Servlet响应
	 */
	private ClientServletResponse response;

	/**
	 * 客户端Servlet响应外观类
	 * 
	 * @param response
	 *            客户端Servlet响应
	 */
	public ClientServletResponseFacade(ClientServletResponse response) {

		this.response = response;
	}

	@Override
	public ServletRequest getServletRequest() {

		return this.response.getServletRequest();
	}

	@Override
	public Set<String> getHeaderNames() {

		return this.response.getHeaderNames();
	}

	@Override
	public Object getHeader(String name) {

		return this.response.getHeader(name);
	}

	@Override
	public Set<String> getPayloadNames() {

		return this.response.getPayloadNames();
	}

	@Override
	public Object getPayload(String name) {

		return this.response.getPayload(name);
	}
}
