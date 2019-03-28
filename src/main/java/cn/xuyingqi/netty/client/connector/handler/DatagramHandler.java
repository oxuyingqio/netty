package cn.xuyingqi.netty.client.connector.handler;

import java.util.Iterator;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.container.DatagramObserverContainer;
import cn.xuyingqi.netty.client.model.DatagramObserver;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 数据报文处理
 * 
 * @author XuYQ
 *
 */
public final class DatagramHandler extends ChannelHandlerAdapter {

	/**
	 * 日志
	 */
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ChannelHandlerAdapter.class);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 打印日志
		LOGGER.info("\n【Netty】[客户端-数据报文处理]开始.");

		// 获取观察者集合
		Iterator<DatagramObserver> iterator = DatagramObserverContainer.getInstance().getObservers(ctx.channel())
				.iterator();
		// 遍历观察者集合
		while (iterator.hasNext()) {

			// 获取当前观察者
			DatagramObserver dob = iterator.next();

			// 打印日志
			LOGGER.info("\n【Netty】[客户端-数据报文处理]观察者({}),开始处理.", dob.getClass().getName());

			// 调用通知方法.当返回true时,移除本观察者.
			if (dob.notify((Datagram) msg)) {

				iterator.remove();
			}

			// 打印日志
			LOGGER.info("\n【Netty】[客户端-数据报文处理]观察者({}),处理结束.", dob.getClass().getName());
		}

		// 后续处理
		ctx.fireChannelRead(msg);

		// 打印日志
		LOGGER.info("\n【Netty】[客户端-数据报文处理]结束.");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 获取观察者集合
		Iterator<DatagramObserver> iterator = DatagramObserverContainer.getInstance().getObservers(ctx.channel())
				.iterator();
		// 遍历观察者集合
		while (iterator.hasNext()) {

			// 获取当前观察者
			DatagramObserver dob = iterator.next();
			// 调用异常处理方法
			dob.exception(cause);

			// 移除本观察者
			iterator.remove();
		}

		// 移除通道观察者集合
		DatagramObserverContainer.getInstance().clearObservers(ctx.channel());

		// 后续处理
		ctx.fireExceptionCaught(cause);
	}
}