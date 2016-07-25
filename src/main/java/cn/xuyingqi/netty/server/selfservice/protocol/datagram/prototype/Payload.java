package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype;

/**
 * 报体
 * 
 * @author XuYQ
 *
 */
public abstract class Payload {

	/**
	 * 转为字节数组
	 * 
	 * @return
	 */
	public abstract byte[] toByteArray();

	/**
	 * 转为字符串
	 * 
	 * @return
	 */
	public abstract String toString();
}
