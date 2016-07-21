package cn.xuyingqi.netty.server.connector.protocol.echo;

import cn.xuyingqi.netty.server.connector.protocol.Decoder;
import cn.xuyingqi.netty.server.connector.protocol.Encoder;
import cn.xuyingqi.netty.server.connector.protocol.Protocol;

/**
 * 应答协议
 * 
 * @author XuYQ
 *
 */
public class EchoProtocol implements Protocol {

	@Override
	public Encoder getEncoder() {
		return new EchoEncoder();
	}

	@Override
	public Decoder getDecoder() {
		return new EchoDecoder();
	}
}
