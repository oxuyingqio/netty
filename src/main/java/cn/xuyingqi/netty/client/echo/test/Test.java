package cn.xuyingqi.netty.client.echo.test;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.connector.Connector;
import cn.xuyingqi.netty.client.echo.protocol.EchoDatagram;
import cn.xuyingqi.netty.client.echo.protocol.EchoProtocol;

public class Test {

	public static void main(String[] args) {

		Connector c = new Connector("127.0.0.1", 60000, new EchoProtocol());
		c.connect();
//		Datagram datagram = c.request(new EchoDatagram(5, "12345"));
//		c.request(new EchoDatagram(5, "12345"), new DatagramObserver() {
//
//			@Override
//			public boolean receiveDatagram(Datagram datagram) {
//
//				System.out.println("进来了");
//
//				return true;
//			}
//		});
//
//		System.out.println(datagram);
//		c.close();
	}
}
