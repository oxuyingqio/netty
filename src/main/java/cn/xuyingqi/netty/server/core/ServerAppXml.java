package cn.xuyingqi.netty.server.core;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * server.app.xml配置
 * 
 * @author XuYQ
 *
 */
@XmlRootElement(name = "server.app")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ServerAppXml {

	/**
	 * server.app.xml配置
	 */
	private static ServerAppXml serverAppXml;

	/**
	 * 私有构造方法
	 */
	private ServerAppXml() {

	}

	/**
	 * 获取server.app.xml配置实例
	 * 
	 * @return
	 */
	public static final ServerAppXml getInstance() {

		if (serverAppXml == null) {

			try {

				JAXBContext jc = JAXBContext.newInstance(ServerAppXml.class);
				Unmarshaller u = jc.createUnmarshaller();
				serverAppXml = (ServerAppXml) u.unmarshal(new File(
						ServerAppXml.class.getClassLoader().getResource(Constant.SERVER_APP_CONFIG_FILE).getPath()));

			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}

		return serverAppXml;
	}

}
