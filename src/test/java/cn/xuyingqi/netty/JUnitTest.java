package cn.xuyingqi.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.Test;

public class JUnitTest {

	@Test
	public void test() {

		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("www.baidu.com", 80));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
