package cn.xuyingqi.netty.protocol.impl;

import cn.xuyingqi.netty.protocol.Decoder;
import cn.xuyingqi.netty.protocol.Encoder;
import cn.xuyingqi.netty.protocol.Protocol;

/**
 * 协议抽象实现
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractProtocol implements Protocol {

	private String name;

	@Override
	public String getName() {

		return this.name;
	}

	/**
	 * 设置协议名称
	 * 
	 * @param name
	 */
	public void setName(String name) {

		this.name = name;
	}

	@Override
	public abstract Encoder getEncoder();

	@Override
	public abstract Decoder getDecoder();
}
