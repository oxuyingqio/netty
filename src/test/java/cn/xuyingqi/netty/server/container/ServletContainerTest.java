package cn.xuyingqi.netty.server.container;

import org.junit.Test;

public class ServletContainerTest {

	@Test
	public void test() {
		ServletContainer servletContainer = ServletContainer.getInstance();
		System.out.println(servletContainer);
	}
}
