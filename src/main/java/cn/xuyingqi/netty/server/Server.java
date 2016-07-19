package cn.xuyingqi.netty.server;

import cn.xuyingqi.netty.server.ServerConfigFactory.ServerConfig;
import cn.xuyingqi.netty.study.LoginAuthReqHandler;
import cn.xuyingqi.netty.study.LoginAuthReqHandler2;
import cn.xuyingqi.netty.study.MessageDecoder;
import cn.xuyingqi.netty.study.MessageEncoder;
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
 * 服务器
 * 
 * @author XuYQ
 *
 */
public class Server {

	/**
	 * 服务器配置
	 */
	private ServerConfig config;

	/**
	 * 服务器
	 */
	public Server() {

		// 获取服务器配置
		this.config = ServerConfigFactory.getInstance();
	}

	/**
	 * 启动服务
	 */
	public void startup() {

		// 服务器线程组
		// 用于接收客户端连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 用于读写客户端连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		// 服务器启动器
		ServerBootstrap bootstrap = new ServerBootstrap();
		// 服务器配置
		bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 100).childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel sc) throws Exception {
						// 超时时间
						sc.pipeline().addLast(new ReadTimeoutHandler(config.getConnector().getTimeout()));
						sc.pipeline().addLast(new MessageDecoder(1024 * 1024, 0, 4));
						sc.pipeline().addLast(new MessageEncoder());
						sc.pipeline().addLast(new LoginAuthReqHandler());
						sc.pipeline().addLast(new LoginAuthReqHandler2());
					}
				});

		try {
			// 同步绑定主机名,端口号
			ChannelFuture f = bootstrap.bind(this.config.getConnector().getHost(), this.config.getConnector().getPort())
					.sync();
			// 同步等待服务关闭
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
