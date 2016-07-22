package cn.xuyingqi.netty.server.connector;

import cn.xuyingqi.netty.server.container.ProtocolContainer;
import cn.xuyingqi.netty.server.core.ServerXml;
import cn.xuyingqi.netty.server.core.ServerXml.ServiceConfig.ConnectorConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
	private ConnectorConfig connectorConfig;

	/**
	 * 连接器
	 */
	public Connector() {

		// 获取连接器配置
		this.connectorConfig = ServerXml.getInstance().getServiceConfig().getConnectorConfig();
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
				.option(ChannelOption.SO_BACKLOG, 100).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(connectorConfig.getTimeout()));
						// 编码
						ch.pipeline().addLast(ProtocolContainer.getInstance().getProtocol(connectorConfig.getProtocol())
								.getEncoder());
						// 解码
						ch.pipeline().addLast(ProtocolContainer.getInstance().getProtocol(connectorConfig.getProtocol())
								.getDecoder());
						// Servlet
						ch.pipeline().addLast(new ServletHandler());
					}
				});

		try {
			// 同步绑定端口号
			ChannelFuture future = bootstrap.bind(connectorConfig.getHost(), connectorConfig.getPort()).sync();
			// 同步等待端口关闭
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
