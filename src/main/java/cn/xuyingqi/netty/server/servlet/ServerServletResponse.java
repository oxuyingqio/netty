package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServletResponse;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponse extends AbstractServletResponse {

	/**
	 * Servlet请求
	 */
	private ServletRequest request;

	/**
	 * Servlet响应
	 * 
	 * @param servletSession
	 *            Servlet会话
	 * @param servletRequest
	 *            Servlet请求
	 */
	public ServerServletResponse(ServletSession servletSession, ServletRequest servletRequest) {

		super(servletSession);

		this.request = servletRequest;
	}

	@Override
	public ServletRequest getServletRequest() {

		return this.request;
	}

	@Override
	public ServletResponse setContentLength(int len) {

		return this;
	}
}
