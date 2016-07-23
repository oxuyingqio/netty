package cn.xuyingqi.netty.server.servlet;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletConfig;

/**
 * 默认的Servlet配置
 * 
 * @author XuYQ
 *
 */
public class DefaultServletConfig extends AbstractServletConfig {

	/**
	 * 默认的Servlet配置
	 */
	public DefaultServletConfig() {
		super(new DefaultServletContext());
	}

	/**
	 * 默认的Servlet配置
	 * 
	 * @param servletContext
	 *            servlet上下文
	 */
	public DefaultServletConfig(ServletContext servletContext) {
		super(servletContext);
	}
}
