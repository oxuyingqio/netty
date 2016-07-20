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

	/**
	 * 编码器
	 */
	private Encoder encoder;
	/**
	 * 解码器
	 */
	private Decoder decoder;

	/**
	 * 应答协议
	 */
	public EchoProtocol() {

		this.encoder = new EchoEncoder();
		this.decoder = new EchoDecoder();
	}

	@Override
	public Encoder getEncoder() {
		return encoder;
	}

	@Override
	public Decoder getDecoder() {
		return decoder;
	}
}
