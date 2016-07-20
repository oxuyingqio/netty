package cn.xuyingqi.netty.server.connector.echo;

import cn.xuyingqi.netty.server.connector.Decoder;
import cn.xuyingqi.netty.server.connector.Encoder;
import cn.xuyingqi.netty.server.connector.Protocol;

/**
 * 应答协议
 * 
 * @author XuYQ
 *
 */
public class EchoProtocol implements Protocol {

	@Override
	public Encoder getEncoder() {
		return null;
	}

	@Override
	public Decoder getDecoder() {
		return null;
	}
}
