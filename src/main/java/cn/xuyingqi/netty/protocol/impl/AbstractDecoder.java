package cn.xuyingqi.netty.protocol.impl;

import java.util.Iterator;

import io.netty.channel.ChannelHandlerContext;
import cn.xuyingqi.netty.client.container.DatagramObserverContainer;
import cn.xuyingqi.netty.client.model.DatagramObserver;
import cn.xuyingqi.netty.protocol.Decoder;

public abstract class AbstractDecoder implements Decoder{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 获取观察者集合
		Iterator<DatagramObserver> iter = DatagramObserverContainer.getInstance().getObservers(ctx.channel())
				.iterator();
		// 遍历观察者集合
		while (iter.hasNext()) {

			// 调用异常处理方法
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