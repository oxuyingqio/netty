package cn.xuyingqi.netty.server.container;

import org.junit.Test;

public class ProtocolClassContainerTest {

	@Test
	public void test() {

		ProtocolClassContainer protocolClassContainer = ProtocolClassContainer.getInstance();
		System.out.println(protocolClassContainer);
	}
}
