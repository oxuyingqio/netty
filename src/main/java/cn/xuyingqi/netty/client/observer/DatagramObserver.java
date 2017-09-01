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
	 * 接收数据报文.返回true,则移除本观察者不再通知;返回false,则收到数据报文后继续通知
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