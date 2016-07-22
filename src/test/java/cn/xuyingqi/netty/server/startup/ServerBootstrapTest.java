package cn.xuyingqi.netty.server.startup;

import org.junit.Test;

import cn.xuyingqi.net.server.startup.Bootstrap;

public class ServerBootstrapTest {

	@Test
	public void test() {

		Bootstrap bootstrap = new ServerBootstrap();
		bootstrap.startup();
	}
}
