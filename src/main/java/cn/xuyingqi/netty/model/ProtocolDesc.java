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
	 * 协议
	 */
	private Protocol protocl;

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
	 * 获取实例
	 * 
	 * @return
	 */
	public Protocol getInstance() {

		if (this.protocl == null) {

			try {

				this.protocl = (Protocol) this.getClass().getClassLoader().loadClass(this.className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return this.protocl;
	}
}
