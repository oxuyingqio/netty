package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.net.server.startup.Bootstrap;
import cn.xuyingqi.netty.model.ServerXml;
import cn.xuyingqi.netty.server.connector.Connector;

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
		Connector connector = new Connector();
		// 配置连接器配置
		connector.init(ServerXml.getInstance().getServiceConfig().getConnectorConfig());
		// 连接
		connector.connect();
	}

	public static void main(String[] args) {

		new ServerBootstrap().startup();
	}
}
