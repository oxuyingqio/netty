package cn.xuyingqi.netty.servlet.impl;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletSession;

/**
 * 默认的Servlet会话
 * 
 * @author XuYQ
 *
 */
public class DefaultServletSession extends AbstractServletSession {

	/**
	 * 本机地址
	 */
	private InetSocketAddress local;
	/**
	 * 远程地址
	 */
	private InetSocketAddress remote;

	/**
	 * Servlet上下文,是在不断变化的
	 */
	private ServletContext context;

	/**
	 * 最后一次请求的时间
	 */
	private long lastAccessedTime;

	/**
	 * 最大间隔时间
	 */
	private int maxInactiveInterval;

	/**
	 * 协议名称
	 */
	private String protocol;

	/**
	 * 默认的Servlet会话
	 * 
	 * @param local
	 *            本机地址
	 * @param remote
	 *            远程地址
	 */
	public DefaultServletSession(SocketAddress local, SocketAddress remote) {

		this.local = (InetSocketAddress) local;
		this.remote = (InetSocketAddress) remote;

		// 初始使用创建时间
		this.lastAccessedTime = this.getCreationTime();
	}

	@Override
	public ServletContext getServletContext() {

		return this.context;
	}

	/**
	 * 设置Servlet上下文,多个Servlet共用同一个Session对象,因此存在该方法,不停的去改变Session中Servlet的上下文
	 * 
	 * @param context
	 */
	public void setServletContext(ServletContext context) {

		this.context = context;
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
	public DefaultServletSession updateLastAccessedTime() {

		this.lastAccessedTime = System.currentTimeMillis();

		return this;
	}

	@Override
	public int getMaxInactiveInterval() {

		return this.maxInactiveInterval;
	}

	/**
	 * 设置最大间隔时间
	 * 
	 * @param maxInactiveInterval
	 */
	public void setMaxInactiveInterval(int maxInactiveInterval) {

		this.maxInactiveInterval = maxInactiveInterval;
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

		return this.protocol;
	}

	/**
	 * 设置协议名称
	 * 
	 * @param protocol
	 */
	public void setProtocol(String protocol) {

		this.protocol = protocol;
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