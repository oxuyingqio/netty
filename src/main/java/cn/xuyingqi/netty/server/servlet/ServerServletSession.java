package cn.xuyingqi.netty.server.servlet;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletSession;

/**
 * Servlet会话
 * 
 * @author XuYQ
 *
 */
public class ServerServletSession extends AbstractServletSession {

	/**
	 * 服务器地址
	 */
	private InetSocketAddress local;

	/**
	 * 客户端地址
	 */
	private InetSocketAddress remote;

	/**
	 * Servlet会话
	 * 
	 * @param servletContext
	 *            Servlet上下文
	 * @param local
	 *            服务器地址
	 * @param remote
	 *            客户端地址
	 */
	public ServerServletSession(ServletContext servletContext, SocketAddress local, SocketAddress remote) {

		super(servletContext);

		this.local = (InetSocketAddress) local;
		this.remote = (InetSocketAddress) remote;
	}

	@Override
	public InetAddress getLocalAddr() {

		return this.local.getAddress();
	}

	@Override
	public String getLocalHost() {

		return this.local.getHostName();
	}

	@Override
	public int getLocalPort() {

		return this.local.getPort();
	}

	@Override
	public String getProtocol() {

		return "";
	}

	@Override
	public InetAddress getRemoteAddr() {

		return this.remote.getAddress();
	}

	@Override
	public String getRemoteHost() {

		return this.remote.getHostName();
	}

	@Override
	public int getRemotePort() {

		return this.remote.getPort();
	}
}
