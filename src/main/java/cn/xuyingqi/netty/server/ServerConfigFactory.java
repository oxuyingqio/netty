package cn.xuyingqi.netty.server;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 服务器配置工厂
 * 
 * @author XuYQ
 *
 */
public final class ServerConfigFactory {

	/**
	 * 服务器配置文件默认位置
	 */
	private static final String DEFAULT_SERVER_CONFIG_FILE = "server.xml";

	/**
	 * 服务器配置
	 */
	private static ServerConfig config = null;

	/**
	 * 获取服务器配置
	 * 
	 * @return
	 */
	public static final ServerConfig getInstance() {

		// 若配置文件为空,则创建配置文件
		if (config == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ServerConfig.class);
				Unmarshaller u = jc.createUnmarshaller();
				config = (ServerConfig) u.unmarshal(new File(
						ServerConfig.class.getClassLoader().getResource(DEFAULT_SERVER_CONFIG_FILE).getPath()));

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return config;
	}

	/**
	 * 服务器配置
	 * 
	 * @author XuYQ
	 *
	 */
	@XmlRootElement(name = "server")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static final class ServerConfig {

		/**
		 * 连接配置
		 */
		@XmlElement(name = "connector")
		private Connector connector;

		/**
		 * 获取连接配置
		 * 
		 * @return
		 */
		public Connector getConnector() {
			return connector;
		}

		/**
		 * 连接配置
		 * 
		 * @author XuYQ
		 *
		 */
		public static final class Connector {

			/**
			 * 主机地址
			 */
			@XmlAttribute(name = "host")
			private String host;
			/**
			 * 端口号
			 */
			@XmlAttribute(name = "port")
			private int port;
			/**
			 * 超时时间
			 */
			@XmlAttribute(name = "timeout")
			private int timeout;

			/**
			 * 获取主机地址
			 * 
			 * @return
			 */
			public String getHost() {
				return host;
			}

			/**
			 * 获取端口号
			 * 
			 * @return
			 */
			public int getPort() {
				return port;
			}

			/**
			 * 获取超时时间
			 * 
			 * @return
			 */
			public int getTimeout() {
				return timeout;
			}
		}
	}
}