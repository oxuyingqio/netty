package cn.xuyingqi.netty.client.observer;

import cn.xuyingqi.net.protocol.Datagram;

/**
 * 数据报文观察者
 * 
 * @author XuYQ
 *
 */
public interface DatagramObserver {

	/**
	 * 接收数据报文
	 * 
	 * @param datagram
	 */
	public boolean receiveDatagram(Datagram datagram);
}
