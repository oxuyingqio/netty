package cn.xuyingqi.netty.server.startup;

import cn.xuyingqi.netty.server.connector.Connector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public class Bootstrap {

	public static void main(String[] args) {

		Connector connector = new Connector();
		connector.startup();
	}
}
