package cn.xuyingqi.netty.server.servlet;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;
import cn.xuyingqi.netty.server.protocol.datagram.ServerDatagram;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public class ServerServletRequest extends AbstractServletRequest {

	/**
	 * 报头Map集合
	 */
	private Map<String, Object> headerMap;

	/**
	 * 报体Map集合
	 */
	private Map<String, Object> payloadMap;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServletSession servletSession, ServerDatagram datagram) {

		super(servletSession);

		this.headerMap = datagram.getHeader().toMap();
		this.payloadMap = datagram.getPayload().toMap();
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
	public Set<String> getParameterNames() {

		return this.payloadMap.keySet();
	}

	@Override
	public Object getParameter(String name) {

		return this.payloadMap.get(name);
	}
}
