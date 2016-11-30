package cn.xuyingqi.netty.client.connector.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.observer.DatagramObserver;
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
	private static final List<DatagramObserver> observers = Collections
			.synchronizedList(new ArrayList<DatagramObserver>());

	/**
	 * 添加观察者
	 * 
	 * @param observer
	 */
	public synchronized static void addObserver(DatagramObserver observer) {

		observers.add(observer);
	}

	/**
	 * 移除观察者
	 * 
	 * @param observer
	 */
	public synchronized static void removeObserver(DatagramObserver observer) {

		observers.remove(observer);
	}

	@Override
	public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		Iterator<DatagramObserver> iter = observers.iterator();
		while (iter.hasNext()) {
			iter.next().receiveDatagram((Datagram) msg);
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}
}
