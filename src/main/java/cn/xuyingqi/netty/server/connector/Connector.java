package cn.xuyingqi.netty.server.connector;

import cn.xuyingqi.netty.server.container.ProtocolContainer;
import cn.xuyingqi.netty.server.core.ServerXml;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 连接器
 * 
 * @author XuYQ
 *
 */
public final class Connector {

	/**
	 * 连接器配置
	 */
	private cn.xuyingqi.netty.server.core.ServerXml.Service.Connector connector;

	/**
	 * 协议
	 */
	private Protocol protocol;

	/**
	 * 连接器
	 */
	public Connector() {

		// 获取连接器配置
		this.connector = ServerXml.getInstance().getService().getConnector();
		// 获取协议
		this.protocol = ProtocolContainer.getInstance().getProtocol(this.connector.getProtocol());
	}

	/**
	 * 连接
	 */
	public final void connect() {

		// 服务器线程组
		// 用于接收客户端连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 用于客户端连接读写
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100).handler(new LoggingHandler(LogLevel.INFO))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ReadTimeoutHandler(connector.getTimeout()));
						ch.pipeline().addLast(protocol.getDecoder());
						ch.pipeline().addLast(protocol.getEncoder());
					}
				});

		try {
			// 同步绑定端口号
			ChannelFuture f = bootstrap.bind(connector.getHost(), connector.getPort()).sync();
			// 同步等待端口关闭
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
