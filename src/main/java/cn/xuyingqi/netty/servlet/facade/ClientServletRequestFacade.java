package cn.xuyingqi.netty.servlet.facade;

import java.net.InetAddress;
import java.util.Set;

import cn.xuyingqi.net.servlet.ClientServletRequest;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletSession;

/**
 * 客户端Servlet请求外观类
 * 
 * @author XuYQ
 *
 */
public class ClientServletRequestFacade implements ClientServletRequest {

	/**
	 * 客户端Servlet请求
	 */
	private ClientServletRequest request;

	/**
	 * 客户端Servlet请求外观类
	 * 
	 * @param request
	 *            Servlet请求
	 */
	public ClientServletRequestFacade(ClientServletRequest request) {

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

	@Override
	public ClientServletRequest addHeader(String name, Object value) {

		return this.request.addHeader(name, value);
	}

	@Override
	public boolean containsHeader(String name) {

		return this.request.containsHeader(name);
	}

	@Override
	public ClientServletRequest setHeader(String name, Object value) {

		return this.request.setHeader(name, value);
	}

	@Override
	public ClientServletRequest addPayload(String name, Object value) {

		return this.request.addPayload(name, value);
	}

	@Override
	public boolean containsPayload(String name) {

		return this.request.containsPayload(name);
	}

	@Override
	public ClientServletRequest setPayload(String name, Object value) {

		return this.request.setPayload(name, value);
	}
}
