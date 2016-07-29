package cn.xuyingqi.netty.server.servlet;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;
import cn.xuyingqi.netty.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.protocol.datagram.ServerHeader;
import cn.xuyingqi.netty.protocol.datagram.ServerPayload;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public class ServerServletRequest extends AbstractServletRequest {

	/**
	 * 报头
	 */
	private ServerHeader header;

	/**
	 * 报体
	 */
	private ServerPayload payload;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServletSession servletSession, ServerDatagram datagram) {

		super(servletSession);

		this.header = (ServerHeader) datagram.getHeader();
		this.payload = (ServerPayload) datagram.getPayload();
	}

	@Override
	public Set<String> getHeaderNames() {

		return this.header.toMap().keySet();
	}

	@Override
	public Object getHeader(String name) {

		return this.header.getHeader(name);
	}

	@Override
	public Set<String> getParameterNames() {

		return this.payload.toMap().keySet();
	}

	@Override
	public Object getParameter(String name) {

		return this.payload.getParameter(name);
	}
}
