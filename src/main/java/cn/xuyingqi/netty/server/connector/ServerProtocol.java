package cn.xuyingqi.netty.server.connector;

import cn.xuyingqi.net.server.connector.Protocol;
import cn.xuyingqi.netty.server.connector.protocol.Decoder;
import cn.xuyingqi.netty.server.connector.protocol.Encoder;

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
