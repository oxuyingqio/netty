package cn.xuyingqi.netty.server.connector.protocol;

/**
 * 协议
 * 
 * @author XuYQ
 *
 */
public interface ServerProtocol {

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
