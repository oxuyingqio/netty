package cn.xuyingqi.netty.client.connector.handler;

import java.util.Iterator;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.container.DatagramObserverContainer;
import cn.xuyingqi.netty.client.observer.DatagramObserver;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 数据报文处理
 * 
 * @author XuYQ
 *
 */
public final class DatagramHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 获取观察者集合
		Iterator<DatagramObserver> iter = DatagramObserverContainer.getInstance().getObservers(ctx.channel())
				.iterator();
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

		// 获取观察者集合
		Iterator<DatagramObserver> iter = DatagramObserverContainer.getInstance().getObservers(ctx.channel())
				.iterator();
		// 遍历观察者集合
		while (iter.hasNext()) {

			// 调用异常处理
			iter.next().exception(cause);
			// 移除本观察者
			iter.remove();
		}

		// 移除通道观察者集合
		DatagramObserverContainer.getInstance().clearObservers(ctx.channel());

		// 后续处理
		ctx.fireExceptionCaught(cause);
	}
}
