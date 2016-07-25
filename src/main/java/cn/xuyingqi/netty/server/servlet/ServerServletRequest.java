package cn.xuyingqi.netty.server.servlet;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.server.connector.protocol.datagram.Datagram;
import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public abstract class ServerServletRequest extends AbstractServletRequest {

	/**
	 * 报头Map集合
	 */
	private Map<String, Object> headerMap;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServerServletSession servletSession, Datagram datagram) {

		super(servletSession);
		// 更新最后一次请求时间
		servletSession.updateLastAccessedTime();

		this.headerMap = datagram.getHeader().convertMap();
	}

	@Override
	public Set<String> getHeaderNames() {

		return this.headerMap.keySet();
	}

	@Override
	public Object getHeader(String name) {

		return this.headerMap.get(name);
	}

	@Override
	public abstract String getCharacterEncoding();

	@Override
	public abstract String getContentType();

	@Override
	public abstract int getContentLength();

	@Override
	public abstract Set<String> getParameterNames();

	@Override
	public abstract Object getParameter(String name);
}
