package cn.xuyingqi.netty.server.container;

import java.util.Map;

import cn.xuyingqi.netty.server.connector.Protocol;

/**
 * 协议容器
 * 
 * @author XuYQ
 *
 */
public final class ProtocolContainer {

	private static Map<String, Protocol> protocols;

	private ProtocolContainer() {

	}

	public static ProtocolContainer getInstance() {

		return new ProtocolContainer();
	}

	public static void addProtocol(String name, Protocol protocol) {
		protocols.put(name, protocol);
	}

	public static void removeProtocol(String name) {
		protocols.remove(name);
	}

	public static Protocol getProtocol(String name) {
		return protocols.get(name);
	}

	public static void main(String[] args) {

		ProtocolContainer p = ProtocolContainer.getInstance();
		p.addProtocol("demo1", null);
	}
}
