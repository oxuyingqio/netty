package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.net.server.connector.Connector;
import cn.xuyingqi.net.server.startup.Bootstrap;
import cn.xuyingqi.netty.server.connector.ServerConnector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public final class ServerBootstrap implements Bootstrap {

	@Override
	public final void startup() {

		// 连接器
		Connector connector = new ServerConnector();
		// 连接
		connector.connect();
	}
}
