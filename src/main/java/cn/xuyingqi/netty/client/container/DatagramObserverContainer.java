package cn.xuyingqi.netty.client.container;

import java.util.Map;
import java.util.Vector;

import cn.xuyingqi.netty.client.model.DatagramObserver;
import cn.xuyingqi.util.MapFactory;
import cn.xuyingqi.util.VectorFactory;
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
	 * 通道观察者集合
	 */
	private Map<Channel, Vector<DatagramObserver>> channelObservers = MapFactory.newInstance();

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
	public synchronized static final DatagramObserverContainer getInstance() {

		if (container == null) {

			container = new DatagramObserverContainer();
		}

		return container;
	}

	/**
	 * 给指定通道添加观察者
	 * 
	 * @param channel
	 * @param observer
	 * @return
	 */
	public synchronized DatagramObserverContainer addObserver(Channel channel, DatagramObserver observer) {

		// 获取通道的观察者集合
		Vector<DatagramObserver> observers = this.getObservers(channel);
		// 若观察者集合为空,则创建对应集合
		if (observers == null) {

			observers = VectorFactory.newInstance();
			this.channelObservers.put(channel, observers);
		}

		// 判断观察者是否存在,不存在则添加
		if (!observers.contains(observer)) {

			observers.addElement(observer);
		}

		return this;
	}

	/**
	 * 获取指定通道观察者集合
	 * 
	 * @param channel
	 * @return
	 */
	public synchronized Vector<DatagramObserver> getObservers(Channel channel) {

		return this.channelObservers.get(channel);
	}

	/**
	 * 移除指定通道观察者
	 * 
	 * @param channel
	 * @param observer
	 * @return
	 */
	public synchronized DatagramObserverContainer removeObserver(Channel channel, DatagramObserver observer) {

		// 获取通道的观察者集合
		Vector<DatagramObserver> observers = this.getObservers(channel);

		// 若观察者集合不为空,则移除观察者
		if (observers != null) {

			observers.removeElement(observer);
		}

		return this;
	}

	/**
	 * 移除通道观察者集合
	 * 
	 * @param channel
	 * @return
	 */
	public synchronized DatagramObserverContainer clearObservers(Channel channel) {

		this.channelObservers.remove(channel);

		return this;
	}
}