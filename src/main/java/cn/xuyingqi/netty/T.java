package cn.xuyingqi.netty;

public class T {

	public static void main(String[] args) {

		Basket basket = new Basket();

		new Thread(new Producer("生产1号", basket)).start();
		new Thread(new Producer("生产2号", basket)).start();
		new Thread(new Producer("生产3号", basket)).start();
		new Thread(new Producer("生产4号", basket)).start();
		new Thread(new Producer("生产5号", basket)).start();
		new Thread(new Consumer("消费1号", basket)).start();
		new Thread(new Consumer("消费2号", basket)).start();
		new Thread(new Consumer("消费3号", basket)).start();
	}
}

/**
 * 生产者
 * 
 * @author XuYQ
 *
 */
class Producer implements Runnable {

	/**
	 * 生产者名称
	 */
	private String name;

	/**
	 * 筐
	 */
	private Basket basket;

	/**
	 * 生产者
	 * 
	 * @param name
	 * @param basket
	 */
	public Producer(String name, Basket basket) {

		this.name = name;
		this.basket = basket;
	}

	@Override
	public void run() {

		// 不停的生产产品
		while (true) {

			// 生产添加产品
			this.basket.add(this);

			// 生产等待3秒
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {

		return this.name;
	}
}

/**
 * 消费者
 * 
 * @author XuYQ
 *
 */
class Consumer implements Runnable {

	/**
	 * 消费者名称
	 */
	private String name;

	/**
	 * 筐
	 */
	private Basket basket;

	/**
	 * 消费者
	 * 
	 * @param name
	 * @param basket
	 */
	public Consumer(String name, Basket basket) {

		this.name = name;
		this.basket = basket;
	}

	@Override
	public void run() {

		// 不停的消耗产品
		while (true) {

			// 消耗移除产品
			this.basket.remove(this);

			// 消耗等待1秒
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {

		return this.name;
	}
}

/**
 * 筐
 * 
 * @author XuYQ
 *
 */
class Basket {

	/**
	 * 筐内产品个数
	 */
	private Integer count = 0;

	/**
	 * 增加产品
	 *
	 * @param producer
	 */
	public synchronized void add(Producer producer) {

		// 当个数等于5个的时候释放锁,阻塞线程,等待被唤醒
		while (this.count >= 5) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 增加产品个数
		this.count++;
		// 输出产品个数
		System.out.println(producer + "生产了1个产品,当前产品个数:" + this.count);

		this.notifyAll();
	}

	/**
	 * 消耗产品
	 *
	 * @param consumer
	 */
	public synchronized void remove(Consumer consumer) {

		// 当个数等于0个的时候释放锁,阻塞线程,等待被唤醒
		while (this.count == 0) {

			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.count--;
		System.out.println(consumer + "消耗了1个产品,当前产品个数:" + this.count);

		// 执行完,释放锁
		this.notifyAll();
	}
}
