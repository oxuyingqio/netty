package cn.xuyingqi.netty.server.servlet.facade;

import java.net.InetAddress;
import java.util.Set;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.netty.server.servlet.ServerServletRequest;

/**
 * ServerServlet请求外观类
 * 
 * @author XuYQ
 *
 */
public class ServerServletRequestFacade implements ServletRequest {

	/**
	 * ServerServlet请求
	 */
	private ServerServletRequest request;

	/**
	 * ServerServlet请求外观类
	 * 
	 * @param request
	 */
	public ServerServletRequestFacade(ServerServletRequest request) {

		this.request = request;
	}

	@Override
	public ServletSession getServletSession() {

		return this.request.getServletSession();
	}

	@Override
	public String getServletSessionId() {

		return this.request.getServletSessionId();
	}

	@Override
	public InetAddress getLocalAddr() {

		return this.request.getLocalAddr();
	}

	@Override
	public String getLocalHost() {

		return this.request.getLocalHost();
	}

	@Override
	public int getLocalPort() {

		return this.request.getLocalPort();
	}

	@Override
	public String getProtocol() {

		return this.request.getProtocol();
	}

	@Override
	public InetAddress getRemoteAddr() {

		return this.request.getRemoteAddr();
	}

	@Override
	public String getRemoteHost() {

		return this.request.getRemoteHost();
	}

	@Override
	public int getRemotePort() {

		return this.request.getRemotePort();
	}

	@Override
	public Set<String> getHeaderNames() {

		return this.request.getHeaderNames();
	}

	@Override
	public Object getHeader(String name) {

		return this.request.getHeader(name);
	}

	@Override
	public Set<String> getParameterNames() {

		return this.request.getParameterNames();
	}

	@Override
	public Object getParameter(String name) {

		return this.request.getParameter(name);
	}

	@Override
	public Set<String> getAttributeNames() {

		return this.request.getAttributeNames();
	}

	@Override
	public Object getAttribute(String name) {

		return this.request.getAttribute(name);
	}

	@Override
	public ServletRequest removeAttribute(String name) {

		return this.request.removeAttribute(name);
	}

	@Override
	public ServletRequest setAttribute(String name, Object object) {

		return this.request.setAttribute(name, object);
	}
}
