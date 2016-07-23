package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.net.server.connector.Connector;
import cn.xuyingqi.net.server.startup.Bootstrap;
import cn.xuyingqi.netty.server.connector.ServerConnector;
import cn.xuyingqi.netty.server.container.ServerProtocolContainer;
import cn.xuyingqi.netty.server.core.ServerXml;

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
		// 配置连接器配置,协议容器
		connector.init(ServerXml.getInstance().getServiceConfig().getConnectorConfig(),
				ServerProtocolContainer.getInstance());
		// 连接
		connector.connect();
	}
}
