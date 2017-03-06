package cn.xuyingqi.netty.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.netty.model.ServerAppXml;
import cn.xuyingqi.netty.model.ServerAppXml.ServletConfig;
import cn.xuyingqi.netty.model.ServletDesc;
import cn.xuyingqi.util.util.MapFactory;

/**
 * Servlet类描述容器
 * 
 * @author XuYQ
 *
 */
public final class ServletDescContainer {

	/**
	 * 描述容器
	 */
	private static ServletDescContainer container;

	/**
	 * 描述集合
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
			this.descs.put(config.getName(),
					new ServletDesc(config.getName(), config.getClassName(), config.getInitParam()));
		}
	}

	/**
	 * 获取Servlet类描述容器实例
	 * 
	 * @return
	 */
	public static final synchronized ServletDescContainer getInstance() {

		if (container == null) {
			container = new ServletDescContainer();
		}

		return container;
	}

	/**
	 * 获取指定名称Servlet
	 * 
	 * @param name
	 * @return
	 */
	public Servlet getServlet(String name) {

		return descs.get(name).getInstance();
	}
}
