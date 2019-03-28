package cn.xuyingqi.netty.servlet.impl;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletConfig;

/**
 * 默认的Servlet配置
 * 
 * @author XuYQ
 *
 */
public final class DefaultServletConfig extends AbstractServletConfig {

	/**
	 * 默认的Servlet上下文类路径
	 */
	private static final String DEFAULT_SERVLET_CONTEXT_CLASS = "cn.xuyingqi.netty.servlet.impl.DefaultServletContext";

	/**
	 * 初始化参数
	 */
	private Map<String, String> initParamter;

	/**
	 * Servlet上下文
	 */
	private ServletContext context;

	/**
	 * Servlet名称
	 */
	private String servletName;

	/**
	 * 默认的Servlet配置
	 * 
	 * @param initParamter
	 *            初始化参数
	 */
	public DefaultServletConfig(Map<String, String> initParamter) {

		// 初始化参数
		this.initParamter = initParamter;

		try {

			// 实例化Servlet上下文对象,每个Servlet都有自己独一的上下文
			String contextClassName = this.initParamter.get("context") == null ? DEFAULT_SERVLET_CONTEXT_CLASS
					: this.initParamter.get("context");
			this.context = (ServletContext) Class.forName(contextClassName).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Set<String> getInitParameterNames() {

		return initParamter.keySet();
	}

	@Override
	public String getInitParameter(String name) {

		return initParamter.get(name);
	}

	@Override
	public ServletContext getServletContext() {

		return this.context;
	}

	@Override
	public String getServletName() {

		return this.servletName;
	}

	/**
	 * 设置Servlet名称
	 * 
	 * @param servletName
	 */
	public void setServletName(String servletName) {

		this.servletName = servletName;
	}
}