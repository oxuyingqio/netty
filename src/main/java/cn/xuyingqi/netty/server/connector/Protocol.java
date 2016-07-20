package cn.xuyingqi.netty.server.connector;

/**
 * 协议
 * 
 * @author XuYQ
 *
 */
public interface Protocol {

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
