package cn.xuyingqi.netty.client.connector.handler;

import java.util.Iterator;
import java.util.List;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.observer.DatagramObserver;
import cn.xuyingqi.util.util.ListFactory;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 数据报文处理
 * 
 * @author XuYQ
 *
 */
public final class DatagramHandler extends ChannelHandlerAdapter {

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

		// 获取观察者集合
		Iterator<DatagramObserver> iter = observers.iterator();
		// 遍历观察者集合
		while (iter.hasNext()) {

			// 调用接收消息,当返回true时,移除本观察者
			if (iter.next().receiveDatagram(ctx, (Datagram) msg)) {
				iter.remove();
			}
		}

		// 后续处理
		ctx.fireChannelRead(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		cause.printStackTrace();
	}
}
