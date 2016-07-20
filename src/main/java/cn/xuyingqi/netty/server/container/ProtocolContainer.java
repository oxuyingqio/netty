package cn.xuyingqi.netty.server.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.Protocol;
import cn.xuyingqi.netty.server.core.ServerXml;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 协议容器
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
	 * 协议集合
	 */
	private static Map<String, Protocol> protocols = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ProtocolContainer() {

		// 获取协议配置集合
		List<cn.xuyingqi.netty.server.core.ServerXml.Protocol> protocols = ServerXml.getInstance().getProtocols();

		try {

			// 遍历协议配置集合
			for (int i = 0, length = protocols.size(); i < length; i++) {

				// 实例化协议
				Protocol protocol = (Protocol) Class.forName(protocols.get(i).getClassName()).newInstance();
				// 添加协议
				this.addProtocol(protocols.get(i).getName(), protocol);
			}

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
	 * 添加协议
	 * 
	 * @param name
	 *            协议名称
	 * @param protocol
	 *            协议
	 */
	private void addProtocol(String name, Protocol protocol) {
		protocols.put(name, protocol);
	}

	/**
	 * 获取协议
	 * 
	 * @param name
	 *            协议名称
	 * @return
	 */
	public Protocol getProtocol(String name) {
		return protocols.get(name);
	}
}
