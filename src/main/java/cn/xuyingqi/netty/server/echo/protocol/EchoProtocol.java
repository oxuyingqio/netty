package cn.xuyingqi.netty.server.echo.protocol;

import cn.xuyingqi.netty.server.protocol.Decoder;
import cn.xuyingqi.netty.server.protocol.Encoder;
import cn.xuyingqi.netty.server.protocol.ServerProtocol;

/**
 * 应答协议
 * 
 * @author XuYQ
 *
 */
public class EchoProtocol implements ServerProtocol {

	/**
	 * 应答编码器
	 */
	private EchoEncoder encoder;
	/**
	 * 应答解码器
	 */
	private EchoDecoder decoder;

	/**
	 * 应答协议
	 */
	public EchoProtocol() {

		// 实例化应答编码器
		this.encoder = new EchoEncoder();
		// 实例化应答解码器
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