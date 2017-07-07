package cn.xuyingqi.netty.client.echo.test;

import cn.xuyingqi.net.connector.ConnectorConfig;
import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.protocol.Protocol;
import cn.xuyingqi.netty.client.connector.Connector;
import cn.xuyingqi.netty.client.echo.protocol.EchoDatagram;
import cn.xuyingqi.netty.client.echo.protocol.EchoProtocol;
import cn.xuyingqi.netty.client.observer.DatagramObserver;

public class Test {

	public static void main(String[] args) {

		Connector c = new Connector();
		c.init(new ConnectorConfig() {

			@Override
			public String getHost() {
				return "127.0.0.1";
			}

			@Override
			public int getPort() {
				return 60000;
			}

			@Override
			public Protocol getProtocol() {
				return new EchoProtocol();
			}

			@Override
			public int getTimeout() {
				return 60;
			}
		});
		c.connect();
		c.request(new EchoDatagram(4, "1234"), new DatagramObserver() {

			@Override
			public boolean receiveDatagram(Datagram datagram) {

				System.out.println("来了" + ((EchoDatagram) datagram).getMsg());

				return true;
			}

			@Override
			public void exception(Throwable exception) {

			}
		});

		System.out.println("直接来了么？");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
