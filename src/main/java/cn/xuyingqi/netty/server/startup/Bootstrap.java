package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.netty.server.connector.Connector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public final class Bootstrap {

	/**
	 * 启动
	 */
	public static final void startup() {

		// 连接器
		Connector connector = new Connector();
		// 连接
		connector.connect();
	}
}
