package cn.xuyingqi.netty.model;

import org.junit.Test;

import cn.xuyingqi.netty.model.ServerAppXml;

public class ServletAppXmlTest {

	@Test
	public void test() {

		ServerAppXml serverXml = ServerAppXml.getInstance();
		System.out.println(serverXml);
	}
}
