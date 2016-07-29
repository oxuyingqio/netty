package cn.xuyingqi.netty.protocol;

import cn.xuyingqi.net.protocol.Protocol;

/**
 * 协议
 * 
 * @author XuYQ
 *
 */
public interface NettyProtocol extends Protocol {

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
