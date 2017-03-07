package cn.xuyingqi.netty.client.echo.protocol;

import cn.xuyingqi.netty.protocol.Decoder;
import cn.xuyingqi.netty.protocol.Encoder;
import cn.xuyingqi.netty.protocol.impl.AbstractProtocol;

/**
 * 应答协议
 * 
 * @author XuYQ
 *
 */
public class EchoProtocol extends AbstractProtocol {

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

		return this.encoder;
	}

	@Override
	public Decoder getDecoder() {

		return this.decoder;
	}
}
