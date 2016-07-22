package cn.xuyingqi.netty.server.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.Protocol;
import cn.xuyingqi.netty.server.core.ServerXml;
import cn.xuyingqi.netty.server.core.ServerXml.ProtocolConfig;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 协议容器.<br>
 * 获取该实例时,协议尚未实例化,在调用getProtocol时,才进行实例化.<br>
 * 因此每次调用getProtocol时,获取的都是不同的协议对象
 * 
 * @author XuYQ
 *
 */
public final class ProtocolContainer {

	/**
	 * 协议容器
	 */
	private static ProtocolContainer protocolContainer;

	/**
	 * 协议类对象集合
	 */
	private static Map<String, Class<Protocol>> protocolClasses = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ProtocolContainer() {

		// 获取协议配置集合
		List<ProtocolConfig> protocolConfigs = ServerXml.getInstance().getProtocolConfigs();

		try {

			// 遍历协议配置集合
			for (int i = 0, length = protocolConfigs.size(); i < length; i++) {

				// 获取协议类对象
				@SuppressWarnings("unchecked")
				Class<Protocol> protocol = (Class<Protocol>) this.getClass().getClassLoader()
						.loadClass(protocolConfigs.get(i).getClassName());
				// 添加协议类对象
				this.addProtocolClass(protocolConfigs.get(i).getName(), protocol);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取协议容器实例
	 * 
	 * @return
	 */
	public static final ProtocolContainer getInstance() {

		if (protocolContainer == null) {
			protocolContainer = new ProtocolContainer();
		}

		return protocolContainer;
	}

	/**
	 * 添加协议类对象
	 * 
	 * @param name
	 *            协议名称
	 * @param protocol
	 *            协议类对象
	 */
	private void addProtocolClass(String name, Class<Protocol> protocol) {
		protocolClasses.put(name, protocol);
	}

	/**
	 * 获取协议对象
	 * 
	 * @param name
	 *            协议名称
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Protocol getProtocol(String name) throws InstantiationException, IllegalAccessException {
		return protocolClasses.get(name).newInstance();
	}
}