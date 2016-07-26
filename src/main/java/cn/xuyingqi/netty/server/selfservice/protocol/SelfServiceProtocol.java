package cn.xuyingqi.netty.server.selfservice.protocol;

import cn.xuyingqi.netty.server.protocol.Decoder;
import cn.xuyingqi.netty.server.protocol.Encoder;
import cn.xuyingqi.netty.server.protocol.ServerProtocol;

/**
 * 自助缴费协议
 * 
 * @author XuYQ
 *
 */
public class SelfServiceProtocol implements ServerProtocol {

	/**
	 * 应答编码器
	 */
	private SelfServiceEncoder encoder;

	/**
	 * 应答解码器
	 */
	private SelfServiceDecoder decoder;

	/**
	 * 自助缴费协议
	 */
	public SelfServiceProtocol() {

		this.encoder = new SelfServiceEncoder();
		this.decoder = new SelfServiceDecoder();
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
