package cn.xuyingqi.netty.client.connector;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import cn.xuyingqi.net.connector.ConnectorConfig;
import cn.xuyingqi.net.connector.SSLConfig;
import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.netty.client.connector.handler.DatagramHandler;
import cn.xuyingqi.netty.client.connector.handler.ExceptionHandler;
import cn.xuyingqi.netty.client.container.DatagramObserverContainer;
import cn.xuyingqi.netty.client.model.DatagramObserver;
import cn.xuyingqi.netty.protocol.Protocol;
import cn.xuyingqi.netty.util.SSLContextFactory;

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
	private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(Connector.class);

	/**
	 * 连接器配置
	 */
	private ConnectorConfig config;
	/**
	 * SSL上下文
	 */
	private SSLContext sslContext;
	/**
	 * 启动器
	 */
	private Bootstrap bootstrap;
	/**
	 * 通信通道
	 */
	private Channel channel;

	@Override
	public final void init(ConnectorConfig config) {

		// 获取连接器配置
		this.config = config;
		// 是否启用SSL
		if (this.config.getSSLConfig() != null) {

			// 获取SSL上下文
			this.sslContext = SSLContextFactory
					.getInstance((cn.xuyingqi.netty.model.ServerXml.ServiceConfig.ConnectorConfig.SSLConfig) this.config
							.getSSLConfig());
		}

		// 初始化启动器
		this.bootstrap = new Bootstrap();
		// 配置启动器
		this.bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						// 是否启用SSL
						if (sslContext != null) {

							// 获取SSL引擎
							SSLEngine engine = sslContext.createSSLEngine();
							engine.setUseClientMode(false);
							engine.setNeedClientAuth(true);
							// 添加SSL
							ch.pipeline().addLast(new SslHandler(engine));
						}

						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(config.getTimeout()));

						// 编码
						ch.pipeline().addLast(((Protocol) config.getProtocol()).getEncoder());
						// 解码
						ch.pipeline().addLast(((Protocol) config.getProtocol()).getDecoder());

						// 数据报文处理
						ch.pipeline().addLast(new DatagramHandler());

						// 异常处理
						ch.pipeline().addLast(new ExceptionHandler());
					}
				});
	}

	@Override
	public final void connect() {

		try {

			// 同步连接主机,端口号,获取通信通道
			this.channel = this.bootstrap.connect(this.config.getHost(), this.config.getPort()).sync().channel();
			// 打印日志
			LOGGER.info("\n【Netty】[客户端-连接器]远程地址({})已连接.", this.channel.remoteAddress());
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * 获取通道
	 * 
	 * @return
	 */
	public Channel getChannel() {

		return this.channel;
	}

	/**
	 * 请求
	 * 
	 * @param request
	 */
	public final void request(Datagram request) {

		// 发送数据报文
		this.channel.writeAndFlush(request);
	}

	/**
	 * 请求
	 * 
	 * @param request
	 * @param observer
	 */
	public final void request(Datagram request, DatagramObserver observer) {

		// 添加观察者
		DatagramObserverContainer.getInstance().addObserver(this.channel, observer);
		// 发送请求
		this.request(request);
	}

	@Override
	public void stop() {

		// 关闭通信通道
		this.channel.close();
		// 打印日志
		LOGGER.info("\n【Netty】[客户端-连接器]远程地址({})已断开.", this.channel.remoteAddress());
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 客户端
		Connector c = new Connector();
		c.init(new ConnectorConfig() {

			@Override
			public int getTimeout() {

				return 30;
			}

			@Override
			public cn.xuyingqi.net.protocol.Protocol getProtocol() {

				return null;
			}

			@Override
			public int getPort() {

				return 60000;
			}

			@Override
			public String getHost() {

				return "127.0.0.1";
			}

			@Override
			public SSLConfig getSSLConfig() {

				return null;
			}
		});

		// 打印日志
		System.out.println("开始循环");

		// 开始循环
		for (int i = 0; i < 2; i++) {

			// 打印日志
			System.out.println("第" + (i + 1) + "次开始");
			// 记录当前时间
			long current = System.currentTimeMillis();

			// 链接
			c.connect();
			// 打印日志
			System.out.println("启动消耗时常:" + ((System.currentTimeMillis() - current) / 1000) + "秒");

			// 等待
			try {

				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			// 记录当前时间
			current = System.currentTimeMillis();
			// 结束
			c.stop();
			// 打印日志
			System.out.println("终止消耗时常:" + ((System.currentTimeMillis() - current) / 1000) + "秒");
			System.out.println("第" + (i + 1) + "次结束");
		}

		try {

			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}