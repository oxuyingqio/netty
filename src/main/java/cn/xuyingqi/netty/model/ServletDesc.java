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
public class ServletDesc {

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

		if (this.servlet == null) {

			try {

				this.servlet = (Servlet) this.getClass().getClassLoader().loadClass(this.className).newInstance();
				this.servlet.init(new DefaultServletConfig(
						this.initParam == null ? new HashMap<String, String>() : this.initParam));
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
