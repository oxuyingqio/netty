package cn.xuyingqi.netty.server.connector.datagram;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public interface Datagram {

	/**
	 * 获取报头
	 * 
	 * @return
	 */
	public Header getHeader();

	/**
	 * 获取报体
	 * 
	 * @return
	 */
	public byte[] getPayload();
}
