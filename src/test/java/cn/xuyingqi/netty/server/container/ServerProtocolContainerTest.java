package cn.xuyingqi.netty.server.container;

import org.junit.Test;

import cn.xuyingqi.net.server.container.ProtocolContainer;

public class ServerProtocolContainerTest {

	@Test
	public void test() {

		ProtocolContainer protocolContainer = ServerProtocolContainer.getInstance();
		System.out.println(protocolContainer);
	}
}
