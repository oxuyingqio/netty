package cn.xuyingqi.netty.server.container;

import org.junit.Test;

public class ServerProtocolContainerTest {

	@Test
	public void test() {

		ServerProtocolContainer serverProtocolContainer = ServerProtocolContainer.getInstance();
		System.out.println(serverProtocolContainer);
	}
}
