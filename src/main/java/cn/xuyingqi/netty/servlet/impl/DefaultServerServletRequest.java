package cn.xuyingqi.netty.servlet.impl;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServerServletRequest;

/**
 * 默认的服务器端Servlet请求
 * 
 * @author XuYQ
 *
 */
public class DefaultServerServletRequest extends AbstractServerServletRequest {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * 默认的服务器端Servlet请求
	 * 
	 * @param servletSession
	 *            Servlet会话
	 */
	public DefaultServerServletRequest(ServletSession servletSession) {

		super(servletSession);
	}

	@Override
	public Datagram getDatagram() {

		return this.datagram;
	}

	/**
	 * 设置数据报文
	 * 
	 * @param datagram
	 */
	public void setDatagram(Datagram datagram) {

		this.datagram = datagram;
	}
}