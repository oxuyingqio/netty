package cn.xuyingqi.netty.server.startup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

import cn.xuyingqi.net.connector.SSLConfig;
import cn.xuyingqi.net.protocol.Protocol;
import cn.xuyingqi.netty.container.ProtocolDescContainer;
import cn.xuyingqi.netty.model.ServerXml;
import cn.xuyingqi.netty.model.ServerXml.ServiceConfig.ConnectorConfig;
import cn.xuyingqi.netty.server.connector.Connector;

/**
 * 启动程序
 * 
 * @author XuYQ
 *
 */
public final class ServerBootstrap {

	/**
	 * 启动
	 */
	public final void startup() {

		// 连接器配置
		List<ConnectorConfig> configs = ServerXml.getInstance().getServiceConfig().getConnectorConfigs();
		// 遍历连接器配置
		for (int i = 0, length = configs.size(); i < length; i++) {

			// 获取连接器配置
			ConnectorConfig config = configs.get(i);

			// 开启新线程启动程序
			new Thread() {

				@Override
				public void run() {

					// 连接器
					Connector connector = new Connector();
					// 配置连接器配置
					connector.init(new cn.xuyingqi.net.connector.ConnectorConfig() {

						@Override
						public String getHost() {

							return config.getHost();
						}

						@Override
						public int getPort() {

							return config.getPort();
						}

						@Override
						public Protocol getProtocol() {

							return ProtocolDescContainer.getInstance().getProtocol(config.getProtocol());
						}

						@Override
						public int getTimeout() {

							return config.getTimeout();
						}

						@Override
						public SSLConfig getSSLConfig() {

							return config.getSsl();
						}
					});

					// 连接
					connector.connect();
				}
			}.start();
		}
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new ServerBootstrap().startup();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 1000; i++) {
			
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						
						Socket sokcet = new Socket();
						sokcet.connect(new InetSocketAddress("127.0.0.1", 60000));
						sokcet.getOutputStream().write(new byte[] { 0, 0, 0, 1, 0 });
						sokcet.getOutputStream().close();
						sokcet.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}