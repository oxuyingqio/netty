package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.netty.client.connector.handler.ConnectLoggerHandler;
import cn.xuyingqi.netty.client.connector.handler.ExceptionHandler;
import cn.xuyingqi.netty.client.connector.handler.ServletHandler;
import cn.xuyingqi.netty.client.connector.handler.SessionHandler;
import cn.xuyingqi.netty.protocol.Protocol;
import io.netty.bootstrap.Bootstrap;
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
public final class Connector {

	/**
	 * 主机名
	 */
	private String host;
	/**
	 * 端口号
	 */
	private int port;

	/**
	 * 协议
	 */
	private Protocol protocol;

	/**
	 * 连接器
	 * 
	 * @param host
	 *            主机名
	 * @param port
	 *            端口号
	 * @param protocol
	 *            协议
	 */
	public Connector(String host, int port, Protocol protocol) {

		this.host = host;
		this.port = port;
		this.protocol = protocol;
	}

	/**
	 * 连接服务器
	 */
	public final void connect() {

		// 客户端线程组
		EventLoopGroup group = new NioEventLoopGroup();

		// 客户端启动器
		Bootstrap bootstrap = new Bootstrap();
		// 客户端配置
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(300));

						// 连接日志
						ch.pipeline().addLast(new ConnectLoggerHandler());

						// 编码
						ch.pipeline().addLast(protocol.getEncoder());
						// 解码
						ch.pipeline().addLast(protocol.getDecoder());

						// Servlet处理
						ch.pipeline().addLast(new ServletHandler());

						// 会话
						ch.pipeline().addLast(new SessionHandler());

						// 异常处理
						ch.pipeline().addLast(new ExceptionHandler());
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
