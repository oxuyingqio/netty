package cn.xuyingqi.netty.server.connector;

import cn.xuyingqi.net.server.connector.Connector;
import cn.xuyingqi.net.server.container.ProtocolContainer;
import cn.xuyingqi.netty.server.connector.protocol.ServerProtocol;
import cn.xuyingqi.netty.server.container.ServerProtocolContainer;
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
public final class ServerConnector implements Connector {

	/**
	 * 连接器配置
	 */
	private ConnectorConfig config;

	/**
	 * 协议容器
	 */
	private ProtocolContainer protocolContainer;

	/**
	 * 连接器
	 */
	public ServerConnector() {

		// 获取连接器配置
		this.config = ServerXml.getInstance().getServiceConfig().getConnectorConfig();

		// 获取协议容器
		this.protocolContainer = ServerProtocolContainer.getInstance();
	}

	@Override
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
						ch.pipeline().addLast(new ReadTimeoutHandler(config.getTimeout()));
						// 编码
						ch.pipeline().addLast(
								((ServerProtocol) protocolContainer.getProtocol(config.getProtocol())).getEncoder());
						// 解码
						ch.pipeline().addLast(
								((ServerProtocol) protocolContainer.getProtocol(config.getProtocol())).getDecoder());
						// Servlet
						ch.pipeline().addLast(new ServletHandler());
					}
				});

		try {
			// 同步绑定端口号
			ChannelFuture future = bootstrap.bind(config.getHost(), config.getPort()).sync();
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
