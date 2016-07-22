package cn.xuyingqi.netty.server.servlet;

/**
 * 默认Servlet配置
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
	 */
	public DefaultServletConfig(ServletContext servletContext) {
		super(servletContext);
	}
}
