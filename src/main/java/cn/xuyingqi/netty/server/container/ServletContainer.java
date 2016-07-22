package cn.xuyingqi.netty.server.container;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.netty.server.core.DefaultServletConfig;
import cn.xuyingqi.netty.server.core.ServerAppXml;
import cn.xuyingqi.netty.server.core.ServerAppXml.ServletConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * Servlet容器.<br>
 * 获取该实例时,Servlet已提前实例化,因此在程序中每个Servlet都是单例.
 * 
 * @author XuYQ
 *
 */
public final class ServletContainer {

	/**
	 * Servlet容器
	 */
	private static ServletContainer servletContainer;

	/**
	 * Servlet集合
	 */
	private static Map<String, Servlet> servlets = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ServletContainer() {

		// 获取Servlet配置集合
		List<ServletConfig> servletConfigs = ServerAppXml.getInstance().getServletConfigs();

		try {

			// 遍历Servlet配置集合
			for (int i = 0, length = servletConfigs.size(); i < length; i++) {

				// Servlet配置
				ServletConfig servletConfig = servletConfigs.get(i);

				// 实例化Servlet
				Servlet servlet = (Servlet) this.getClass().getClassLoader().loadClass(servletConfig.getClassName())
						.newInstance();
				// 初始化Servlet
				if (servletConfig.getContextConfig() == null) {
					// 未配置上下文,使用默认上下文
					servlet.init(new DefaultServletConfig());
				} else {
					// 自定义上下文,使用自定义上下文
					servlet.init(new DefaultServletConfig(
							(cn.xuyingqi.net.servlet.ServletContext) this.getClass().getClassLoader()
									.loadClass(servletConfig.getContextConfig().getClassName()).newInstance()));
				}

				// 添加Servlet
				this.addServlet(servletConfig.getName(), servlet);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Servlet容器实例
	 * 
	 * @return
	 */
	public static final ServletContainer getInstance() {

		if (servletContainer == null) {
			servletContainer = new ServletContainer();
		}

		return servletContainer;
	}

	/**
	 * 添加Servlet
	 * 
	 * @param name
	 *            Servlet名称
	 * @param servlet
	 *            Servlet
	 */
	private void addServlet(String name, Servlet servlet) {
		servlets.put(name, servlet);
	}

	/**
	 * 获取Servlet
	 * 
	 * @param name
	 *            Servlet名称
	 * @return
	 */
	public Servlet getServlet(String name) {
		return servlets.get(name);
	}

	/**
	 * 获取所有Servlet的名称集合
	 * 
	 * @return
	 */
	public Set<String> getServletNames() {

		return servlets.keySet();
	}
}
