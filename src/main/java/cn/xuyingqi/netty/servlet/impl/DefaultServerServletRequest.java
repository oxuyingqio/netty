package cn.xuyingqi.netty.servlet.impl;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletRequest;
import cn.xuyingqi.netty.protocol.datagram.Datagram;
import cn.xuyingqi.netty.protocol.datagram.Header;
import cn.xuyingqi.netty.protocol.datagram.Payload;

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
	private Header header;

	/**
	 * 报体
	 */
	private Payload payload;

	/**
	 * 默认的服务器端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 * @param datagram
	 *            数据报文
	 */
	public DefaultServerServletRequest(ServletSession servletSession, Datagram datagram) {

		super(servletSession);

		this.header = (Header) datagram.getHeader();
		this.payload = (Payload) datagram.getPayload();
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

		return this.payload.getPayload(name);
	}
}
