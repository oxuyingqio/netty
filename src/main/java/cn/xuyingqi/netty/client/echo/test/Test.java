package cn.xuyingqi.netty.client.echo.test;

import cn.xuyingqi.netty.client.connector.Connector;
import cn.xuyingqi.netty.client.echo.protocol.EchoDatagram;
import cn.xuyingqi.netty.client.echo.protocol.EchoProtocol;

public class Test {

	public static void main(String[] args) {

		Connector c = new Connector("127.0.0.1", 60000, new EchoProtocol());
		c.connect();
		c.demo(new EchoDatagram(5, "12345"));
		c.demo(new EchoDatagram(5, "12345"));
		c.demo(new EchoDatagram(5, "12345"));
		c.close();
	}
}
