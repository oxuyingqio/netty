package cn.xuyingqi.netty.server.container;

import org.junit.Test;

public class ProtocolContainerTest {

	@Test
	public void test() {

		ProtocolContainer protocolContainer = ProtocolContainer.getInstance();
		System.out.println(protocolContainer);
	}
}
