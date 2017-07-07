package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.net.connector.ConnectorConfig;
import cn.xuyingqi.net.protocol.Datagram;
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

		// 初始化启动器
		this.bootstrap = new Bootstrap();
		// 配置启动器
		this.bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						// 超时
						ch.pipeline().addLast(new ReadTimeoutHandler(config.getTimeout()));

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
	}

	@Override
	public final void connect() {

		try {

			// 同步连接主机,端口号,获取通信通道
			this.channel = this.bootstrap.connect(this.config.getHost(), this.config.getPort()).sync().channel();
			// 打印日志
			this.logger.info("远程地址(" + this.config.getHost() + ":" + this.config.getPort() + ")已连接.");
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
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
		// 发送数据报文
		this.channel.writeAndFlush(request);
	}

	@Override
	public void stop() {

		// 关闭通信通道
		this.channel.close();
		// 打印日志
		this.logger.info("远程地址(" + this.config.getHost() + ":" + this.config.getPort() + ")已断开.");
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
			public int getPort() {

				return 60000;
			}

			@Override
			public String getHost() {

				return "127.0.0.1";
			}

			@Override
			public cn.xuyingqi.net.protocol.Protocol getProtocol() {

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