package cn.xuyingqi.netty.server.connector.echo;

import cn.xuyingqi.netty.server.connector.Decoder;
import cn.xuyingqi.netty.server.connector.Encoder;
import cn.xuyingqi.netty.server.connector.Protocol;

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
