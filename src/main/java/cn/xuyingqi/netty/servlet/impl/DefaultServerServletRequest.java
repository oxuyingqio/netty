package cn.xuyingqi.netty.servlet.impl;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletRequest;
import cn.xuyingqi.netty.protocol.datagram.NettyDatagram;
import cn.xuyingqi.netty.protocol.datagram.NettyHeader;
import cn.xuyingqi.netty.protocol.datagram.NettyPayload;

/**
 * 默认的服务器端Servlet请求
 * 
 * @author XuYQ
 *
 */
public class DefaultServerServletRequest extends AbstractServerServletRequest {

	/**
	 * 报头
	 */
	private NettyHeader header;

	/**
	 * 报体
	 */
	private NettyPayload payload;

	/**
	 * 默认的服务器端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 */
	public DefaultServerServletRequest(ServletSession servletSession, NettyDatagram datagram) {

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
	public Set<String> getPayloadNames() {

		return this.payload.toMap().keySet();
	}

	@Override
	public Object getPayload(String name) {

		return this.payload.getParameter(name);
	}
}
