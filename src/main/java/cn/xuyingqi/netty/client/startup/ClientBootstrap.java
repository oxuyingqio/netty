package cn.xuyingqi.netty.client.startup;

import cn.xuyingqi.netty.client.connector.ClientConnector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public final class ClientBootstrap {

	public final void startup() {

		ClientConnector cc = new ClientConnector();
		cc.connect("127.0.0.1", 60000);
	}
}
