package cn.xuyingqi.netty.servlet.impl;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletResponse;
import cn.xuyingqi.netty.protocol.datagram.NettyDatagram;
import cn.xuyingqi.netty.protocol.datagram.NettyHeader;
import cn.xuyingqi.netty.protocol.datagram.NettyPayload;

/**
 * 默认的服务器端Servlet响应
 * 
 * @author XuYQ
 *
 */
public class DefaultServerServletResponse extends AbstractServerServletResponse {

	/**
	 * 数据报文
	 */
	private NettyDatagram datagram;

	/**
	 * 默认的服务器端Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 */
	public DefaultServerServletResponse(ServletRequest servletRequest, NettyDatagram datagram) {

		super(servletRequest);

		this.datagram = datagram;
	}

	@Override
	public ServletResponse addHeader(String name, Object value) {

		((NettyHeader) this.datagram.getHeader()).addHeader(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return ((NettyHeader) this.datagram.getHeader()).containsHeader(name);
	}

	@Override
	public ServletResponse setHeader(String name, Object value) {

		((NettyHeader) this.datagram.getHeader()).setHeader(name, value);

		return this;
	}

	@Override
	public ServletResponse addPayload(String name, Object value) {

		((NettyPayload) this.datagram.getPayload()).addParameter(name, value);

		return this;
	}

	@Override
	public boolean containsPayload(String name) {

		return ((NettyPayload) this.datagram.getPayload()).containsParameter(name);
	}

	@Override
	public ServletResponse setPayload(String name, Object value) {

		((NettyPayload) this.datagram.getPayload()).setParameter(name, value);

		return this;
	}

	/**
	 * 返回数据报文对象
	 * 
	 * @return
	 */
	public NettyDatagram getServerDatagram() {

		return this.datagram;
	}
}
