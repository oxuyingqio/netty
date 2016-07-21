package cn.xuyingqi.netty.server.servlet.echo;

import cn.xuyingqi.netty.server.servlet.ServletContext;

/**
 * 应答Servlet上下文
 * 
 * @author XuYQ
 *
 */
public class EchoServletContext implements ServletContext {

	@Override
	public void setAttribute(String name, Object object) {

	}

	@Override
	public Object getAttribute(String name) {
		return null;
	}
}
