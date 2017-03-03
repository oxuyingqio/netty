package cn.xuyingqi.netty.container;

import org.junit.Test;

public class ServletDescContainerTest {

	@Test
	public void test() {

		ServletDescContainer sdc = ServletDescContainer.getInstance();
		System.out.println(sdc);
	}
}
