package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.net.connector.ConnectorConfig;
import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.connector.handler.ConnectLoggerHandler;
import cn.xuyingqi.netty.client.connector.handler.DatagramHandler;
import cn.xuyingqi.netty.client.connector.handler.ExceptionHandler;
import cn.xuyingqi.netty.client.connector.handler.SessionHandler;
import cn.xuyingqi.netty.client.container.DatagramObserverContainer;
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
	 * 通道
	 */
	private Channel channel;

	@Override
	public final void init(ConnectorConfig config) {

		// 获取连接器配置
		this.config = config;
	}

	@Override
	public final void connect() {

		// 线程组
		EventLoopGroup group = new NioEventLoopGroup();

		// 启动器
		Bootstrap bootstrap = new Bootstrap();
		// 配置
		bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(config.getTimeout()));

						// 连接日志
						ch.pipeline().addLast(new ConnectLoggerHandler());

						// 编码
						ch.pipeline().addLast(((Protocol) config.getProtocol()).getEncoder());
						// 解码
						ch.pipeline().addLast(((Protocol) config.getProtocol()).getDecoder());

						// 会话
						ch.pipeline().addLast(new SessionHandler());

						// 数据报文处理
						ch.pipeline().addLast(new DatagramHandler());

						// 异常处理
						ch.pipeline().addLast(new ExceptionHandler());
					}
				});

		try {

			// 同步连接主机,端口号
			this.channel = bootstrap.connect(this.config.getHost(), this.config.getPort()).sync().channel();
			// 打印日志
			this.logger.info("远程地址(" + this.config.getHost() + ":" + this.config.getPort() + ")已连接.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {

		// 关闭
		this.channel.close();
		// 打印日志
		this.logger.info("远程地址(" + this.config.getHost() + ":" + this.config.getPort() + ")已断开.");
	}

	/**
	 * 请求
	 * 
	 * @param request
	 */
	public final void request(Datagram request, DatagramObserver observer) {

		// 添加观察者
		DatagramObserverContainer.getInstance().addObserver(this.channel, observer);
		// 发送数据报文
		this.channel.write(request);
	}
}
