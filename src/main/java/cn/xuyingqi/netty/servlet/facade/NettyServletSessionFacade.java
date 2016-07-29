package cn.xuyingqi.netty.servlet.facade;

import java.net.InetAddress;
import java.util.Set;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.servlet.NettyServletSession;

/**
 * ServerServlet会话外观类
 * 
 * @author XuYQ
 *
 */
public class NettyServletSessionFacade implements ServletSession {

	/**
	 * 包装的ServerServlet会话
	 */
	private NettyServletSession session;

	/**
	 * ServerServlet会话外观类
	 * 
	 * @param session
	 */
	public NettyServletSessionFacade(NettyServletSession session) {

		this.session = session;
	}

	@Override
	public ServletContext getServletContext() {

		return this.session.getServletContext();
	}

	@Override
	public String getId() {

		return this.session.getId();
	}

	@Override
	public long getCreationTime() {

		return this.session.getCreationTime();
	}

	@Override
	public long getLastAccessedTime() {

		return this.session.getLastAccessedTime();
	}

	@Override
	public InetAddress getLocalAddr() {

		return this.session.getLocalAddr();
	}

	@Override
	public String getLocalHost() {

		return this.session.getLocalHost();
	}

	@Override
	public int getLocalPort() {

		return this.session.getLocalPort();
	}

	@Override
	public String getProtocol() {

		return this.session.getProtocol();
	}

	@Override
	public InetAddress getRemoteAddr() {

		return this.session.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {

		return this.session.getRemoteHost();
	}

	@Override
	public int getRemotePort() {

		return this.session.getRemotePort();
	}

	@Override
	public Set<String> getAttributeNames() {

		return this.session.getAttributeNames();
	}

	@Override
	public Object getAttribute(String name) {

		return this.session.getAttribute(name);
	}

	@Override
	public ServletSession removeAttribute(String name) {

		return this.session.removeAttribute(name);
	}

	@Override
	public ServletSession setAttribute(String name, Object object) {

		return this.session.setAttribute(name, object);
	}
}
