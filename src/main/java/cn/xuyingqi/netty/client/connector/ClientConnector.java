package cn.xuyingqi.netty.client.connector;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 连接器
 * 
 * @author XuYQ
 *
 */
public final class ClientConnector {

	// /**
	// * 服务器通道集合
	// */
	// private static Map<String, Channel> servers = MapFactory.newInstance();
	//
	// /**
	// * 添加服务器通道
	// *
	// * @param id
	// * @param ch
	// */
	// public static void addServer(String id, Channel ch) {
	//
	// servers.put(id, ch);
	// }
	//
	// /**
	// * 获取服务器通道
	// *
	// * @param id
	// * @return
	// */
	// public static Channel getServer(String id) {
	//
	// return servers.get(id);
	// }
	//
	// /**
	// * 移除服务器通道
	// *
	// * @param id
	// */
	// public static void removeServer(String id) {
	//
	// servers.remove(id);
	// }

	/**
	 * 连接服务器
	 * 
	 * @param host
	 *            主机名
	 * @param port
	 *            端口号
	 */
	public final void connect(String host, int port) {

		// 客户端线程组
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(300));
						ch.pipeline().addLast(new ClientServletHandler());
					}
				});

		try {

			// 同步连接主机,端口号
			ChannelFuture future = bootstrap.connect(host, port).sync();
			// 同步等待客户端链路关闭
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			// 释放线程组资源
			group.shutdownGracefully();
		}
	}
}
