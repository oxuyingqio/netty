package cn.xuyingqi.netty.protocol.datagram;

import cn.xuyingqi.net.protocol.datagram.Datagram;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public interface NettyDatagram extends Datagram {

	/**
	 * 创建对应响应的数据报文
	 * 
	 * @return
	 */
	public NettyDatagram newResponse();
}
