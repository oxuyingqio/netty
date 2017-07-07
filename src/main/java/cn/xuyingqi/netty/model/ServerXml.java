package cn.xuyingqi.netty.model;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * server.xml
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "server")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ServerXml {

	/**
	 * 服务器配置文件
	 */
	private static final String SERVER_CONFIG_FILE = "server.xml";

	/**
	 * server.xml
	 */
	private static ServerXml serverXml;

	/**
	 * 协议配置集合
	 */
	@XmlElements(value = { @XmlElement(name = "protocol", type = ProtocolConfig.class) })
	private List<ProtocolConfig> protocolConfigs;

	/**
	 * 服务配置
	 */
	@XmlElement(name = "service")
	private ServiceConfig serviceConfig;

	/**
	 * 私有构造方法
	 */
	private ServerXml() {

	}

	/**
	 * 获取server.xml配置实例
	 * 
	 * @return
	 */
	public static final ServerXml getInstance() {

		if (serverXml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ServerXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				serverXml = (ServerXml) u.unmarshal(
						new File(ServerXml.class.getClassLoader().getResource(ServerXml.SERVER_CONFIG_FILE).getPath()));
			} catch (JAXBException e) {

				e.printStackTrace();
			}
		}

		return serverXml;
	}

	/**
	 * 获取协议配置集合
	 * 
	 * @return
	 */
	public List<ProtocolConfig> getProtocolConfigs() {

		return protocolConfigs;
	}

	/**
	 * 获取服务配置
	 * 
	 * @return
	 */
	public ServiceConfig getServiceConfig() {

		return serviceConfig;
	}

	/**
	 * 协议配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class ProtocolConfig {

		/**
		 * 名称
		 */
		@XmlAttribute(name = "name")
		private String name;

		/**
		 * 类路径
		 */
		@XmlAttribute(name = "className")
		private String className;

		/**
		 * 获取名称
		 * 
		 * @return
		 */
		public String getName() {

			return name;
		}

		/**
		 * 获取类路径
		 * 
		 * @return
		 */
		public String getClassName() {

			return className;
		}
	}

	/**
	 * 服务配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static final class ServiceConfig {

		/**
		 * 连接器配置
		 */
		@XmlElements(value = { @XmlElement(name = "connector", type = ConnectorConfig.class) })
		private List<ConnectorConfig> connectorConfigs;

		/**
		 * 获取连接器配置
		 * 
		 * @return
		 */
		public List<ConnectorConfig> getConnectorConfigs() {

			return connectorConfigs;
		}

		/**
		 * 连接器配置
		 * 
		 * @author XuYQ
		 *
		 */
		public static final class ConnectorConfig {

			/**
			 * 协议名称
			 */
			@XmlAttribute(name = "protocol")
			private String protocol;

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

			public String getProtocol() {

				return protocol;
			}

			public String getHost() {

				return host;
			}

			public int getPort() {

				return port;
			}

			public int getTimeout() {

				return timeout;
			}
		}
	}
}