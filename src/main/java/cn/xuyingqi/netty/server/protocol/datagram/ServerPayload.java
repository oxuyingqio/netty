package cn.xuyingqi.netty.server.protocol.datagram;

import cn.xuyingqi.net.server.protocol.datagram.Payload;

/**
 * 报体
 * 
 * @author XuYQ
 *
 */
public interface ServerPayload extends Payload {

	/**
	 * 添加参数
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServerPayload addParamter(String name, Object value);

	/**
	 * 检查参数是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsParamter(String name);

	/**
	 * 设置参数的值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServerPayload setParamter(String name, Object value);
}
