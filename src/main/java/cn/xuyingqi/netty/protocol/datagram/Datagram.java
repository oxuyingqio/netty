package cn.xuyingqi.netty.protocol.datagram;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public interface Datagram extends cn.xuyingqi.net.protocol.datagram.Datagram {

	/**
	 * 创建对应响应的数据报文
	 * 
	 * @return
	 */
	public Datagram newResponse();
}
