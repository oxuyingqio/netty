package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServletResponse;
import cn.xuyingqi.netty.server.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.protocol.datagram.ServerHeader;
import cn.xuyingqi.netty.server.protocol.datagram.ServerPayload;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponse extends AbstractServletResponse {

	/**
	 * 数据报文
	 */
	private ServerDatagram datagram;

	/**
	 * Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 */
	public ServerServletResponse(ServletRequest servletRequest, ServerDatagram datagram) {

		super(servletRequest);

		this.datagram = datagram;
	}

	@Override
	public ServletResponse addHeader(String name, Object value) {

		((ServerHeader) this.datagram.getHeader()).addHeader(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return ((ServerHeader) this.datagram.getHeader()).containsHeader(name);
	}

	@Override
	public ServletResponse setHeader(String name, Object value) {

		((ServerHeader) this.datagram.getHeader()).setHeader(name, value);

		return this;
	}

	@Override
	public ServletResponse addParamter(String name, Object value) {

		((ServerPayload) this.datagram.getPayload()).addParamter(name, value);

		return this;
	}

	@Override
	public boolean containsParamter(String name) {

		return ((ServerPayload) this.datagram.getPayload()).containsParamter(name);
	}

	@Override
	public ServletResponse setParamter(String name, Object value) {

		((ServerPayload) this.datagram.getPayload()).setParamter(name, value);

		return this;
	}

	/**
	 * 返回数据报文对象
	 * 
	 * @return
	 */
	public ServerDatagram getServerDatagram() {

		return this.datagram;
	}
}
