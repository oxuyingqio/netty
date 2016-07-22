package cn.xuyingqi.netty.server.servlet;

/**
 * 默认Servlet配置
 * 
 * @author XuYQ
 *
 */
public class DefaultServletConfig implements ServletConfig {

	/**
	 * Servlet上下文
	 */
	private ServletContext servletContext;

	/**
	 * 默认的Servlet配置
	 */
	public DefaultServletConfig() {
		this.servletContext = new DefaultServletContext();
	}

	/**
	 * 默认的Servlet配置
	 * 
	 * @param servletContext
	 */
	public DefaultServletConfig(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public ServletContext getServletContext() {
		return this.servletContext;
	}
}
