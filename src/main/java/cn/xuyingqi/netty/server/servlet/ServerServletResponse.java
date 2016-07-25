package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServletResponse;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public abstract class ServerServletResponse extends AbstractServletResponse {

	/**
	 * Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 */
	public ServerServletResponse(ServletRequest servletRequest) {

		super(servletRequest);
	}

	@Override
	public abstract ServletResponse addHeader(String name, Object value);

	@Override
	public abstract boolean containsHeader(String name);

	@Override
	public abstract ServletResponse setHeader(String name, Object value);

	@Override
	public abstract ServletResponse setContentLength(int len);

	@Override
	public abstract ServletResponse setStatus(int status);

	@Override
	public abstract boolean isCommitted();
}
