package cn.xuyingqi.netty.protocol.datagram;

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
	public ServerPayload addParameter(String name, Object value);

	/**
	 * 参数是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsParameter(String name);

	/**
	 * 设置参数的值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServerPayload setParameter(String name, Object value);

	/**
	 * 获取参数的值
	 * 
	 * @param name
	 * @return
	 */
	public Object getParameter(String name);
}
