package cn.xuyingqi.netty.server.startup;

import org.junit.Test;

public class NettyServerBootstrapTest {

	@Test
	public void test() {
		new NettyServerBootstrap().startup();
	}
}
