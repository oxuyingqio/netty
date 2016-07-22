package cn.xuyingqi.netty.server.container;

import org.junit.Test;

import cn.xuyingqi.net.server.container.ServletContainer;

public class ServerServletContainerTest {

	@Test
	public void test() {
		ServletContainer servletContainer = ServerServletContainer.getInstance();
		System.out.println(servletContainer);
	}
}
