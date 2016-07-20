package cn.xuyingqi.netty.server.core;

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
 * server.xml配置
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "server")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerXml {

	/**
	 * server.xml配置
	 */
	private static ServerXml serverXml;

	/**
	 * 协议配置集合
	 */
	@XmlElements(value = { @XmlElement(name = "protocol", type = Protocol.class) })
	private List<Protocol> protocols;

	/**
	 * 服务配置
	 */
	@XmlElement(name = "service")
	private Service service;

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
	public static ServerXml getInstance() {

		if (serverXml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ServerXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				serverXml = (ServerXml) u.unmarshal(
						new File(ServerXml.class.getClassLoader().getResource(Constant.SERVER_CONFIG_FILE).getPath()));

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
	public List<Protocol> getProtocols() {
		return protocols;
	}

	/**
	 * 获取服务配置
	 * 
	 * @return
	 */
	public Service getService() {
		return service;
	}

	/**
	 * 协议配置
	 * 
	 * @author XuYQ
	 *
	 */
	public static class Protocol {

		/**
		 * 协议名称
		 */
		@XmlAttribute(name = "name")
		private String name;
		/**
		 * 协议类路径
		 */
		@XmlAttribute(name = "className")
		private String className;

		/**
		 * 获取协议名称
		 * 
		 * @return
		 */
		public String getName() {
			return name;
		}

		/**
		 * 获取协议类路径
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
	public static class Service {

		/**
		 * 连接器配置
		 */
		@XmlElement(name = "connector")
		private Connector connector;

		/**
		 * 连接器配置
		 * 
		 * @author XuYQ
		 *
		 */
		public static class Connector {

		}
	}

	public static void main(String[] args) {

		ServerXml xml = ServerXml.getInstance();
		System.out.println(xml);
	}
}
