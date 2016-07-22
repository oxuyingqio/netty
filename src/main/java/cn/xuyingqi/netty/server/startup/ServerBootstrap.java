package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.net.server.startup.Bootstrap;
import cn.xuyingqi.netty.server.connector.Connector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public final class ServerBootstrap implements Bootstrap {

	@Override
	public void startup() {

		// 连接器
		Connector connector = new Connector();
		// 连接
		connector.connect();
	}
}
