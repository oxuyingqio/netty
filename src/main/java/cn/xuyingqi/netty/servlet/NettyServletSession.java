package cn.xuyingqi.netty.servlet;

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
public class NettyServletSession extends AbstractServletSession {

	/**
	 * 最后一次请求的时间
	 */
	private long lastAccessedTime;

	/**
	 * 本机地址
	 */
	private InetSocketAddress local;

	/**
	 * 远程地址
	 */
	private InetSocketAddress remote;

	/**
	 * Servlet会话
	 * 
	 * @param servletContext
	 *            Servlet上下文
	 * @param local
	 *            本机地址
	 * @param remote
	 *            远程地址
	 */
	public NettyServletSession(ServletContext servletContext, SocketAddress local, SocketAddress remote) {

		super(servletContext);

		this.lastAccessedTime = this.getCreationTime();
		this.local = (InetSocketAddress) local;
		this.remote = (InetSocketAddress) remote;
	}

	@Override
	public long getLastAccessedTime() {

		return this.lastAccessedTime;
	}

	/**
	 * 更新最后一次请求时间
	 * 
	 * @return
	 */
	public NettyServletSession updateLastAccessedTime() {

		this.lastAccessedTime = System.currentTimeMillis();

		return this;
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

	/**
	 * 未实现,不影响使用
	 */
	@Override
	public String getProtocol() {

		return null;
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
