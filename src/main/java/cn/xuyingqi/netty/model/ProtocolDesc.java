package cn.xuyingqi.netty.model;

import cn.xuyingqi.netty.protocol.Protocol;

/**
 * 协议类描述
 * 
 * @author XuYQ
 *
 */
public class ProtocolDesc {

	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类路径
	 */
	private String className;

	/**
	 * 协议类描述
	 * 
	 * @param name
	 * @param className
	 */
	public ProtocolDesc(String name, String className) {

		this.name = name;
		this.className = className;
	}

	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * 获取实例,每次都返回新的协议
	 * 
	 * @return
	 */
	public Protocol getInstance() {

		try {

			// 实例化协议
			Protocol protocol = (Protocol) this.getClass().getClassLoader().loadClass(this.className).newInstance();
			if (protocol instanceof cn.xuyingqi.netty.protocol.impl.AbstractProtocol) {
				((cn.xuyingqi.netty.protocol.impl.AbstractProtocol) protocol).setName(this.name);
			}
			return protocol;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
