package cn.xuyingqi.netty.server.servlet;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;
import cn.xuyingqi.netty.protocol.datagram.NettyDatagram;
import cn.xuyingqi.netty.protocol.datagram.NettyHeader;
import cn.xuyingqi.netty.protocol.datagram.NettyPayload;

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
	private NettyHeader header;

	/**
	 * 报体
	 */
	private NettyPayload payload;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServletSession servletSession, NettyDatagram datagram) {

		super(servletSession);

		this.header = (NettyHeader) datagram.getHeader();
		this.payload = (NettyPayload) datagram.getPayload();
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
