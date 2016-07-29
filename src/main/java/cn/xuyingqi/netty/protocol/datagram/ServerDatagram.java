package cn.xuyingqi.netty.protocol.datagram;

import cn.xuyingqi.net.server.protocol.datagram.Datagram;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public interface ServerDatagram extends Datagram {

	/**
	 * 创建对应响应的数据报文
	 * 
	 * @return
	 */
	public ServerDatagram newResponse();
}
