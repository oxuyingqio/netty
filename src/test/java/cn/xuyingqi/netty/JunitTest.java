package cn.xuyingqi.netty;

import org.junit.Test;

public class JunitTest {

	private int i = 0;

	public synchronized void demo() {

		while (i < 5) {
			Thread t = new Thread(new Demo());
			t.start();
			System.out.println("当前i的值为：" + i);
		}

		System.out.println("当前i的值为(已经出来了)：" + i);
	}

	public class Demo implements Runnable {

		@Override
		public synchronized void run() {
			i++;
		}
	}

	@Test
	public void test() {
		new JunitTest().demo();
	}
}
