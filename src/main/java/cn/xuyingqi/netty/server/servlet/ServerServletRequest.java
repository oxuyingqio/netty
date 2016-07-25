package cn.xuyingqi.netty.server.servlet;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.impl.AbstractServletRequest;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerHeader;

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
	private ServerDatagram datagram;

	/**
	 * 报头Map集合
	 */
	private Map<String, Object> headerMap;

	/**
	 * 报体Map集合
	 */
	private Map<String, Object> payloadMap;

	/**
	 * Servlet请求
	 * 
	 * @param servletSession
	 */
	public ServerServletRequest(ServerServletSession servletSession, ServerDatagram datagram) {

		super(servletSession);
		// 更新最后一次请求时间
		servletSession.updateLastAccessedTime();

		this.datagram = datagram;
		this.headerMap = this.datagram.getHeader().convertMap();
		this.payloadMap = this.datagram.getPayload().convertMap();
	}

	@Override
	public Set<String> getHeaderNames() {

		return this.headerMap.keySet();
	}

	@Override
	public Object getHeader(String name) {

		return this.headerMap.get(name);
	}

	@Override
	public String getCharacterEncoding() {

		return ((ServerHeader) this.datagram.getHeader()).getCharacterEncoding();
	}

	@Override
	public String getContentType() {

		return ((ServerHeader) this.datagram.getHeader()).getContentType();
	}

	@Override
	public int getContentLength() {

		return ((ServerHeader) this.datagram.getHeader()).getContentLength();
	}

	@Override
	public Set<String> getParameterNames() {

		return this.payloadMap.keySet();
	}

	@Override
	public Object getParameter(String name) {

		return this.payloadMap.get(name);
	}
}
