package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.connector.handler.ConnectLoggerHandler;
import cn.xuyingqi.netty.client.connector.handler.DatagramSubjectHandler;
import cn.xuyingqi.netty.client.connector.handler.ExceptionHandler;
import cn.xuyingqi.netty.client.connector.handler.SessionHandler;
import cn.xuyingqi.netty.client.observer.DatagramObserver;
import cn.xuyingqi.netty.protocol.Protocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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
	 * 线程组
	 */
	private EventLoopGroup group = new NioEventLoopGroup();
	/**
	 * 通道
	 */
	private Channel channel;

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

		// 启动器
		Bootstrap bootstrap = new Bootstrap();
		// 配置
		bootstrap.group(this.group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
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

						// 会话
						ch.pipeline().addLast(new SessionHandler());

						// 数据报文主题处理
						ch.pipeline().addLast(new DatagramSubjectHandler());

						// 异常处理
						ch.pipeline().addLast(new ExceptionHandler());
					}
				});

		try {

			// 同步连接主机,端口号
			this.channel = bootstrap.connect(this.host, this.port).sync().channel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public final void close() {

		// 同步等待链路关闭
		try {

			this.channel.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放线程组资源
			this.group.shutdownGracefully();
		}
	}

	private int i = 0;

	/**
	 * 请求
	 * 
	 * @param datagram
	 */
	public final void request(Datagram datagram) {

		DatagramSubjectHandler.addObserver(new DatagramObserver() {

			@Override
			public boolean receiveDatagram(Datagram datagram) {

				System.out.println("第" + i++ + "接到了");

				return true;
			}
		});

		this.channel.write(datagram);
	}
}
