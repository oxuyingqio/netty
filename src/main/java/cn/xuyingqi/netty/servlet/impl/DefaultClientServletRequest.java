package cn.xuyingqi.netty.servlet.impl;

import cn.xuyingqi.net.servlet.ClientServletRequest;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractClientServletRequest;
import cn.xuyingqi.netty.protocol.datagram.Datagram;
import cn.xuyingqi.netty.protocol.datagram.Header;
import cn.xuyingqi.netty.protocol.datagram.Payload;

/**
 * 默认的客户端Servlet请求
 * 
 * @author XuYQ
 *
 */
public class DefaultClientServletRequest extends AbstractClientServletRequest {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的客户端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 * @param datagram
	 *            数据报文
	 */
	public DefaultClientServletRequest(ServletSession servletSession, Datagram datagram) {

		super(servletSession);

		this.datagram = datagram;
	}

	@Override
	public ClientServletRequest addHeader(String name, Object value) {

		((Header) this.datagram.getHeader()).addHeader(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return ((Header) this.datagram.getHeader()).containsHeader(name);
	}

	@Override
	public ClientServletRequest setHeader(String name, Object value) {

		((Header) this.datagram.getHeader()).setHeader(name, value);

		return this;
	}

	@Override
	public ClientServletRequest addPayload(String name, Object value) {

		((Payload) this.datagram.getPayload()).addPayload(name, value);

		return this;
	}

	@Override
	public boolean containsPayload(String name) {

		return ((Payload) this.datagram.getPayload()).containsPayload(name);
	}

	@Override
	public ClientServletRequest setPayload(String name, Object value) {

		((Payload) this.datagram.getPayload()).setPayload(name, value);

		return this;
	}

	/**
	 * 返回数据报文对象
	 * 
	 * @return
	 */
	public Datagram getDatagram() {

		return this.datagram;
	}
}
