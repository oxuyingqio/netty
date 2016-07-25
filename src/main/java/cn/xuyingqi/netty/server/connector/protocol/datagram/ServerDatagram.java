package cn.xuyingqi.netty.server.connector.protocol.datagram;

import cn.xuyingqi.net.server.connector.protocol.datagram.Datagram;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public interface ServerDatagram extends Datagram {

	/**
	 * 获取报体
	 * 
	 * @return
	 */
	public ServerPayload getPayload();

	/**
	 * 获取一个新的数据报文实例
	 * 
	 * @return
	 */
	public ServerDatagram newInstance();
}
