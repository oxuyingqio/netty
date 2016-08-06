package cn.xuyingqi.netty.server.echo.protocol;

import cn.xuyingqi.netty.protocol.Decoder;
import cn.xuyingqi.netty.protocol.Encoder;
import cn.xuyingqi.netty.protocol.Protocol;

/**
 * 应答客户端协议
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
