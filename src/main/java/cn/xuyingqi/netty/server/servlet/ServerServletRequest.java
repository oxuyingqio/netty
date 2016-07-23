package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.server.connector.protocol.datagram.Datagram;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public class ServerServletRequest extends AbstractServletRequest {

	/**
	 * 数据报文
	 */
	private Datagram datagram;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServletSession servletSession, Datagram datagram) {

		super(servletSession);

		this.datagram = datagram;
	}

	@Override
	public int getContentLength() {

		return this.datagram.getHeader().getContentLength();
	}
}
