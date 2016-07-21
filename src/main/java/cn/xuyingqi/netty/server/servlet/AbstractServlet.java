package cn.xuyingqi.netty.server.servlet;

/**
 * 抽象公共Servlet
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractServlet implements Servlet, ServletConfig {

	/**
	 * Servlet配置
	 */
	private ServletConfig config;

	@Override
	public ServletContext getServletContext() {

		if (this.config != null) {
			return this.config.getServletContext();
		}

		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) {

		this.config = servletConfig;
	}

	@Override
	public abstract void service(ServletRequest request, ServletResponse response);

	@Override
	public void destroy() {

	}

	@Override
	public ServletConfig getServletConfig() {
		return this.config;
	}

	@Override
	public String getServletInfo() {
		return "";
	}
}
