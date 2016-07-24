package cn.xuyingqi.netty.server.servlet;

import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.ServletContext;
import cn.xuyingqi.net.servlet.impl.AbstractServletConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 默认的Servlet配置
 * 
 * @author XuYQ
 *
 */
public class DefaultServletConfig extends AbstractServletConfig {

	/**
	 * 初始化参数
	 */
	private Map<String, Object> initParamter = MapFactory.newInstance();

	/**
	 * Servlet名称
	 */
	private String servletName;

	/**
	 * Servlet上下文
	 */
	private ServletContext context;

	/**
	 * 默认的Servlet配置
	 * 
	 * @param initParamter
	 *            初始化参数
	 */
	public DefaultServletConfig(String servletName, Map<String, Object> initParamter) {

		this.servletName = servletName;
		this.initParamter = initParamter;
	}

	@Override
	public Set<String> getInitParameterNames() {

		return initParamter.keySet();
	}

	@Override
	public Object getInitParameter(String name) {

		return initParamter.get(name);
	}

	@Override
	public ServletContext getServletContext() {

		return null;
	}

	@Override
	public String getServletName() {

		return this.servletName;
	}
}
