package cn.xuyingqi.netty.client.container;

import java.util.Map;

import cn.xuyingqi.net.servlet.Servlet;
import cn.xuyingqi.util.util.MapFactory;

/**
 * Servlet容器
 * 
 * @author XuYQ
 *
 */
public final class ClientServletContainer {

	/**
	 * Servlet容器
	 */
	private static ClientServletContainer container;

	/**
	 * Servlet集合
	 */
	private static Map<String, Servlet> servlets = MapFactory.newInstance();
}
