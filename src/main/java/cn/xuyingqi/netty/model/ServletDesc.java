package cn.xuyingqi.netty.model;

import java.util.HashMap;
import java.util.Map;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.netty.servlet.impl.DefaultServletConfig;

/**
 * Servlet类描述
 * 
 * @author XuYQ
 *
 */
public final class ServletDesc {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类路径
	 */
	private String className;
	/**
	 * 初始参数
	 */
	private Map<String, String> initParam;

	/**
	 * servlet
	 */
	private Servlet servlet;

	/**
	 * Servlet类描述
	 * 
	 * @param name
	 * @param className
	 * @param initParam
	 */
	public ServletDesc(String name, String className, Map<String, String> initParam) {

		this.name = name;
		this.className = className;
		this.initParam = initParam;
	}

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * 获取实例
	 * 
	 * @return
	 */
	public Servlet getInstance() {

		// 判断Servlet是否已实例化
		if (this.servlet == null) {

			try {

				// 实例化Servlet
				this.servlet = (Servlet) this.getClass().getClassLoader().loadClass(this.className).newInstance();

				// 实例化Servlet配置
				DefaultServletConfig servletConfig = new DefaultServletConfig(
						this.initParam == null ? new HashMap<String, String>() : this.initParam);
				// 设置Servlet名称
				servletConfig.setServletName(this.name);

				// 设置初始化参数
				this.servlet.init(servletConfig);
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}

		return this.servlet;
	}
}