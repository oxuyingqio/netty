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
	 * 接收数据报文,处理成功返回true
	 * 
	 * @param datagram
	 * @return
	 */
	public boolean receiveDatagram(Datagram datagram);

	/**
	 * 异常
	 * 
	 * @param exception
	 */
	public void exception(Throwable exception);
}