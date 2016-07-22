package cn.xuyingqi.netty.server.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.Protocol;
import cn.xuyingqi.netty.server.core.ServerXml;
import cn.xuyingqi.netty.server.core.ServerXml.ProtocolConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 协议类容器.<br>
 * 获取的是协议类对象.因此在调用时都需要调用其newInstance进行实例化.
 * 
 * @author XuYQ
 *
 */
public final class ProtocolClassContainer {

	/**
	 * 协议类容器
	 */
	private static ProtocolClassContainer protocolClassContainer;

	/**
	 * 协议类集合
	 */
	private static Map<String, Class<Protocol>> protocolClasses = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ProtocolClassContainer() {

		// 获取协议配置集合
		List<ProtocolConfig> protocolConfigs = ServerXml.getInstance().getProtocolConfigs();

		try {

			// 遍历协议配置集合
			for (int i = 0, length = protocolConfigs.size(); i < length; i++) {

				// 获取协议类
				@SuppressWarnings("unchecked")
				Class<Protocol> protocol = (Class<Protocol>) this.getClass().getClassLoader()
						.loadClass(protocolConfigs.get(i).getClassName());
				// 添加协议类
				this.addProtocolClass(protocolConfigs.get(i).getName(), protocol);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取协议类容器实例
	 * 
	 * @return
	 */
	public static final ProtocolClassContainer getInstance() {

		if (protocolClassContainer == null) {
			protocolClassContainer = new ProtocolClassContainer();
		}

		return protocolClassContainer;
	}

	/**
	 * 添加协议类
	 * 
	 * @param name
	 *            协议名称
	 * @param protocol
	 *            协议类
	 */
	private void addProtocolClass(String name, Class<Protocol> protocol) {
		protocolClasses.put(name, protocol);
	}

	/**
	 * 获取协议类
	 * 
	 * @param name
	 *            协议名称
	 * @return
	 */
	public Class<Protocol> getProtocolClass(String name) {
		return protocolClasses.get(name);
	}
}
