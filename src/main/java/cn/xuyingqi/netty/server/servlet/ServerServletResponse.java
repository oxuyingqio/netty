package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServletResponse;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerHeader;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public class ServerServletResponse extends AbstractServletResponse {

	/**
	 * 数据报文
	 */
	private ServerDatagram datagram;

	/**
	 * 是否已提交
	 */
	private boolean commit = false;

	/**
	 * Servlet响应
	 * 
	 * @param servletRequest
	 *            Servlet请求
	 */
	public ServerServletResponse(ServletRequest servletRequest, ServerDatagram datagram) {

		super(servletRequest);

		this.datagram = datagram;
	}

	@Override
	public ServletResponse addHeader(String name, Object value) {

		((ServerHeader) this.datagram.getHeader()).addHeader(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return ((ServerHeader) this.datagram.getHeader()).containsHeader(name);
	}

	@Override
	public ServletResponse setHeader(String name, Object value) {

		((ServerHeader) this.datagram.getHeader()).setHeader(name, value);

		return this;
	}

	@Override
	public String getCharacterEncoding() {

		return ((ServerHeader) this.datagram.getHeader()).getCharacterEncoding();
	}

	@Override
	public ServletResponse setCharacterEncoding(String charset) {

		((ServerHeader) this.datagram.getHeader()).setCharacterEncoding(charset);

		return this;
	}

	@Override
	public String getContentType() {

		return ((ServerHeader) this.datagram.getHeader()).getContentType();
	}

	@Override
	public ServletResponse setContentType(String type) {

		((ServerHeader) this.datagram.getHeader()).setContentType(type);

		return this;
	}

	@Override
	public ServletResponse setContentLength(int len) {

		((ServerHeader) this.datagram.getHeader()).setContentLength(len);

		return this;
	}

	@Override
	public ServletResponse setStatus(int status) {

		((ServerHeader) this.datagram.getHeader()).setStatus(status);

		return this;
	}

	@Override
	public boolean isCommitted() {

		return this.commit;
	}
}
