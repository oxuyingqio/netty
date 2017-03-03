package cn.xuyingqi.netty.model;

import org.junit.Test;

import cn.xuyingqi.netty.model.ServerXml;

public class ServerXmlTest {

	@Test
	public void test() {

		ServerXml xml = ServerXml.getInstance();
		System.out.println(xml);
	}
}
