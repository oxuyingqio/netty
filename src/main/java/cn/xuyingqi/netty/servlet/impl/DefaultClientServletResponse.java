package cn.xuyingqi.netty.servlet.impl;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.impl.AbstractClientServletResponse;
import cn.xuyingqi.netty.protocol.datagram.Datagram;
import cn.xuyingqi.netty.protocol.datagram.Header;
import cn.xuyingqi.netty.protocol.datagram.Payload;

/**
 * 默认的客户端Servlet响应
 * 
 * @author XuYQ
 *
 */
public class DefaultClientServletResponse extends AbstractClientServletResponse {

	/**
	 * 报头
	 */
	private Header header;

	/**
	 * 报体
	 */
	private Payload payload;

	/**
	 * 默认的客户端Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 * @param datagram
	 *            数据报文
	 */
	public DefaultClientServletResponse(ServletRequest servletRequest, Datagram datagram) {

		super(servletRequest);

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
