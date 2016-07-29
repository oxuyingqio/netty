package cn.xuyingqi.netty.servlet.impl;

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
	private Map<String, String> initParamter = MapFactory.newInstance();

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
	public DefaultServletConfig(Map<String, String> initParamter) {

		this.initParamter = initParamter;

		try {

			String contextClass = this.initParamter.get("context") == null
					? "cn.xuyingqi.netty.server.servlet.DefaultServletContext" : this.initParamter.get("context");
			this.context = (ServletContext) Class.forName(contextClass).newInstance();

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
}
