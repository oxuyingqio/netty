package cn.xuyingqi.netty.client.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.client.observer.DatagramObserver;
import cn.xuyingqi.util.util.ListFactory;
import cn.xuyingqi.util.util.MapFactory;
import io.netty.channel.Channel;

/**
 * 数据报文观察者容器
 * 
 * @author XuYQ
 *
 */
public final class DatagramObserverContainer {

	/**
	 * 容器
	 */
	private static DatagramObserverContainer container;

	/**
	 * 通道集合
	 */
	private Map<Channel, List<DatagramObserver>> observers = MapFactory.newInstance();

	/**
	 * 数据报文观察者容器
	 */
	private DatagramObserverContainer() {

	}

	/**
	 * 数据报文观察者容器
	 * 
	 * @return
	 */
	public static final synchronized DatagramObserverContainer getInstance() {

		if (container == null) {
			container = new DatagramObserverContainer();
		}

		return container;
	}

	/**
	 * 添加观察者
	 * 
	 * @param channel
	 * @param observer
	 * @return
	 */
	public DatagramObserverContainer addObserver(Channel channel, DatagramObserver observer) {

		// 获取对应通道的观察者
		List<DatagramObserver> observers = this.observers.get(channel);
		// 若观察者为空,则创建,并设置观察者
		if (observers == null) {

			observers = ListFactory.newInstance();
			this.observers.put(channel, observers);
		}
		// 添加观察者
		observers.add(observer);

		return this;
	}

	/**
	 * 获取观察者集合
	 * 
	 * @param channel
	 * @return
	 */
	public List<DatagramObserver> getObservers(Channel channel) {

		return this.observers.get(channel);
	}

	/**
	 * 移除观察者
	 * 
	 * @param channel
	 * @param observer
	 * @return
	 */
	public DatagramObserverContainer removeObserver(Channel channel, DatagramObserver observer) {

		// 获取对应通道的观察者
		List<DatagramObserver> observers = this.observers.get(channel);
		// 若观察者不为空,则移除观察者
		if (observers != null) {

			observers.remove(observer);
		}

		return this;
	}

	/**
	 * 移除通道所有观察者
	 * 
	 * @param channel
	 * @return
	 */
	public DatagramObserverContainer clearObservers(Channel channel) {

		this.observers.remove(channel);

		return this;
	}
}
