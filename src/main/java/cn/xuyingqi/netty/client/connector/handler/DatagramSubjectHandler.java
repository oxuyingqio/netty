package cn.xuyingqi.netty.client.connector.handler;

import java.util.Iterator;
import java.util.List;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.observer.DatagramObserver;
import cn.xuyingqi.util.util.ListFactory;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 被观察数据报文
 * 
 * @author XuYQ
 *
 */
public final class DatagramSubjectHandler extends ChannelHandlerAdapter {

	/**
	 * 观察者
	 */
	private static final List<DatagramObserver> observers = ListFactory.newInstance();

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	public static void addObserver(DatagramObserver observer) {

		observers.add(observer);
	}

	/**
	 * 移除观察者
	 * 
	 * @param observer
	 */
	public static void removeObserver(DatagramObserver observer) {

		observers.remove(observer);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		Iterator<DatagramObserver> iter = observers.iterator();
		while (iter.hasNext()) {
			if (iter.next().receiveDatagram((Datagram) msg)) {
				iter.remove();
			}
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}
}
