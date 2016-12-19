package cn.xuyingqi.netty.client.observer;

import cn.xuyingqi.net.protocol.Datagram;
import io.netty.channel.ChannelHandlerContext;

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
	 * @param ctx
	 * @param datagram
	 * @return
	 */
	public boolean receiveDatagram(ChannelHandlerContext ctx, Datagram datagram);
}
