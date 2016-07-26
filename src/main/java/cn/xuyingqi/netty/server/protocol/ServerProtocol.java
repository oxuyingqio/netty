package cn.xuyingqi.netty.server.protocol;

import cn.xuyingqi.net.server.protocol.Protocol;

/**
 * 协议
 * 
 * @author XuYQ
 *
 */
public interface ServerProtocol extends Protocol {

	/**
	 * 获取编码器
	 * 
	 * @return
	 */
	public Encoder getEncoder();

	/**
	 * 获取解码器
	 * 
	 * @return
	 */
	public Decoder getDecoder();
}