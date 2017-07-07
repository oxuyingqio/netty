package cn.xuyingqi.netty.server.connector;

import cn.xuyingqi.net.connector.ConnectorConfig;
import cn.xuyingqi.netty.protocol.Protocol;
import cn.xuyingqi.netty.server.connector.handler.ChannelContainerHandler;
import cn.xuyingqi.netty.server.connector.handler.ConnectLoggerHandler;
import cn.xuyingqi.netty.server.connector.handler.ExceptionHandler;
import cn.xuyingqi.netty.server.connector.handler.ServletHandler;
import cn.xuyingqi.netty.server.connector.handler.SessionHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 连接器
 * 
 * @author XuYQ
 *
 */
public final class Connector implements cn.xuyingqi.net.connector.Connector {

	/**
	 * 日志
	 */
	private final InternalLogger logger = InternalLoggerFactory.getInstance(Connector.class);

	/**
	 * 连接器配置
	 */
	private ConnectorConfig config;

	/**
	 * 服务通道
	 */
	private Channel channel;

	/**
	 * 是否已启动
	 */
	private boolean started = false;

	@Override
	public final void init(ConnectorConfig config) {

		// 获取连接器配置
		this.config = config;
	}

	@Override
	public final void connect() {

		// 判断是否已经启动
		if (!this.started) {

			// 服务器线程组
			// 用于接收客户端连接
			EventLoopGroup bossGroup = new NioEventLoopGroup();
			// 用于客户端连接读写
			EventLoopGroup workerGroup = new NioEventLoopGroup();

			// 服务器启动器
			ServerBootstrap bootstrap = new ServerBootstrap();
			// 服务器配置
			bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {

							// 超时
							ch.pipeline().addLast(new ReadTimeoutHandler(config.getTimeout()));

							// 连接日志
							ch.pipeline().addLast(new ConnectLoggerHandler());

							// 编码器
							ch.pipeline().addLast(((Protocol) config.getProtocol()).getEncoder());
							// 解码器
							ch.pipeline().addLast(((Protocol) config.getProtocol()).getDecoder());

							// 会话
							ch.pipeline().addLast(new SessionHandler());

							// Servlet处理
							ch.pipeline().addLast((ChannelHandler) new ServletHandler());

							// 远程链接通道
							ch.pipeline().addLast(new ChannelContainerHandler());

							// 异常处理
							ch.pipeline().addLast(new ExceptionHandler());
						}
					});

			try {

				// 同步绑定端口号
				this.channel = bootstrap.bind(this.config.getHost(), this.config.getPort()).sync().channel();
				// 打印日志
				this.logger.info("服务(" + this.config.getHost() + ":" + this.config.getPort() + ")已启动.");

				// 同步等待端口关闭
				this.channel.closeFuture().sync();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} finally {

				// 释放线程组资源
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
		}
	}

	@Override
	public void stop() {

		// 关闭
		this.channel.close();
		// 打印日志
		this.logger.info("服务(" + this.config.getHost() + ":" + this.config.getPort() + ")已停止.");
	}
}