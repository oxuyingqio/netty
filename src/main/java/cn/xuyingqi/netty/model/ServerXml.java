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
	 * 配置文件
	 */
	private static final String CONFIG_FILE = "server.xml";

	/**
	 * XML
	 */
	private static ServerXml xml;

	/**
	 * 协议配置集合
	 */
	@XmlElements(value = { @XmlElement(name = "protocol", type = ProtocolConfig.class) })
	private List<ProtocolConfig> protocols;
	/**
	 * 服务配置
	 */
	@XmlElement(name = "service")
	private ServiceConfig service;

	/**
	 * 私有构造方法
	 */
	private ServerXml() {

	}

	/**
	 * 获取XML配置实例
	 * 
	 * @return
	 */
	public synchronized static final ServerXml getInstance() {

		if (xml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ServerXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				xml = (ServerXml) u.unmarshal(
						new File(ServerXml.class.getClassLoader().getResource(ServerXml.CONFIG_FILE).getPath()));
			} catch (JAXBException e) {

				e.printStackTrace();
			}
		}

		return xml;
	}

	/**
	 * 获取协议配置集合
	 * 
	 * @return
	 */
	public List<ProtocolConfig> getProtocolConfigs() {

		return this.protocols;
	}

	/**
	 * 获取服务配置
	 * 
	 * @return
	 */
	public ServiceConfig getServiceConfig() {

		return this.service;
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

			return this.name;
		}

		/**
		 * 获取类路径
		 * 
		 * @return
		 */
		public String getClassName() {

			return this.className;
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
		private List<ConnectorConfig> connectors;

		/**
		 * 获取连接器配置
		 * 
		 * @return
		 */
		public List<ConnectorConfig> getConnectorConfigs() {

			return this.connectors;
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
			/**
			 * SSL配置
			 */
			@XmlElement(name = "ssl")
			private SSLConfig ssl;

			/**
			 * 获取协议
			 * 
			 * @return
			 */
			public String getProtocol() {

				return this.protocol;
			}

			/**
			 * 获取主机名
			 * 
			 * @return
			 */
			public String getHost() {

				return this.host;
			}

			/**
			 * 获取端口号
			 * 
			 * @return
			 */
			public int getPort() {

				return this.port;
			}

			/**
			 * 获取超时时间
			 * 
			 * @return
			 */
			public int getTimeout() {

				return this.timeout;
			}

			/**
			 * 获取SSL配置
			 * 
			 * @return
			 */
			public SSLConfig getSsl() {

				return this.ssl;
			}

			/**
			 * SSL配置
			 * 
			 * @author XuYQ
			 *
			 */
			public static final class SSLConfig implements cn.xuyingqi.net.connector.SSLConfig {

				/**
				 * 协议
				 */
				@XmlAttribute(name = "protocol")
				private String protocol;
				/**
				 * 私钥
				 */
				@XmlElement(name = "private-key")
				private KeyConfig privateKey;
				/**
				 * 信任证书
				 */
				@XmlElement(name = "trust-certificate")
				private KeyConfig trustCertificate;

				/**
				 * 获取协议
				 * 
				 * @return
				 */
				public String getProtocol() {

					return this.protocol;
				}

				/**
				 * 获取私钥
				 * 
				 * @return
				 */
				public KeyConfig getPrivateKey() {

					return this.privateKey;
				}

				/**
				 * 获取信任证书
				 * 
				 * @return
				 */
				public KeyConfig getTrustCertificate() {

					return this.trustCertificate;
				}

				/**
				 * 密钥配置
				 * 
				 * @author XuYQ
				 *
				 */
				public static final class KeyConfig {

					/**
					 * 密钥路径
					 */
					@XmlAttribute(name = "path")
					private String path;
					/**
					 * 密钥密码
					 */
					@XmlAttribute(name = "password")
					private String password;
					/**
					 * 类型
					 */
					@XmlAttribute(name = "type")
					private String type;
					/**
					 * 算法
					 */
					@XmlAttribute(name = "algorithm")
					private String algorithm;

					/**
					 * 获取密钥路径
					 * 
					 * @return
					 */
					public String getPath() {

						return this.path;
					}

					/**
					 * 获取密钥密码
					 * 
					 * @return
					 */
					public String getPassword() {

						return this.password;
					}

					/**
					 * 获取类型
					 * 
					 * @return
					 */
					public String getType() {

						return this.type;
					}

					/**
					 * 获取算法
					 */
					public String getAlgorithm() {

						return this.algorithm;
					}
				}
			}
		}
	}

	/**
	 * Main函数测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl());

		if (ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl() != null) {

			System.out.println(
					ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl().getProtocol());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getPrivateKey().getPath());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getPrivateKey().getPassword());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getPrivateKey().getType());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getTrustCertificate().getPath());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getTrustCertificate().getPassword());
			System.out.println(ServerXml.getInstance().getServiceConfig().getConnectorConfigs().get(0).getSsl()
					.getTrustCertificate().getType());
		}
	}
}