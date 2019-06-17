package cn.xuyingqi.netty.container;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.netty.model.ServerAppXml;
import cn.xuyingqi.netty.model.ServerAppXml.ServletConfig;
import cn.xuyingqi.util.MapFactory;
import cn.xuyingqi.netty.model.ServletDesc;

/**
 * Servlet类描述容器
 * 
 * @author XuYQ
 *
 */
public final class ServletDescContainer {

	/**
	 * 容器
	 */
	private static ServletDescContainer container;

	/**
	 * Servlet类描述集合
	 */
	private Map<String, ServletDesc> descs = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ServletDescContainer() {

		// 获取Servlet配置集合
		List<ServletConfig> configs = ServerAppXml.getInstance().getServletConfigs();

		// 遍历配置集合
		for (int i = 0, length = configs.size(); i < length; i++) {

			// 配置
			ServletConfig config = configs.get(i);
			// 添加Servlet类描述
			this.addServletDesc(config.getName(),
					new ServletDesc(config.getName(), config.getClassName(), config.getInitParam()));
		}
	}

	/**
	 * 获取Servlet类描述容器实例
	 * 
	 * @return
	 */
	public synchronized static final ServletDescContainer getInstance() {

		if (container == null) {

			container = new ServletDescContainer();
		}

		return container;
	}

	/**
	 * 添加Servlet类描述
	 * 
	 * @param key
	 * @param servletDesc
	 */
	public void addServletDesc(String key, ServletDesc servletDesc) {

		this.descs.put(key, servletDesc);
	}

	/**
	 * 移除Servlet类描述
	 * 
	 * @param key
	 */
	public void removeServletDesc(String key) {

		this.descs.remove(key);
	}

	/**
	 * 获取Servlet类描述
	 * 
	 * @param key
	 * @return
	 */
	public ServletDesc getServletDesc(String key) {

		return this.descs.get(key);
	}

	/**
	 * 获取Servlet类描述Key集合
	 * 
	 * @return
	 */
	public Set<String> getServletDescKeys() {

		return this.descs.keySet();
	}

	/**
	 * 获取Servlet
	 * 
	 * @param key
	 * @return
	 */
	public Servlet getServlet(String key) {

		return this.getServletDesc(key).getInstance();
	}
}