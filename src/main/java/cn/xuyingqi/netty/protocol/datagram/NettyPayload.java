package cn.xuyingqi.netty.protocol.datagram;

import cn.xuyingqi.net.protocol.datagram.Payload;

/**
 * 报体
 * 
 * @author XuYQ
 *
 */
public interface NettyPayload extends Payload {

	/**
	 * 添加参数
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public NettyPayload addPayload(String name, Object value);

	/**
	 * 参数是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsPayload(String name);

	/**
	 * 设置参数的值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public NettyPayload setPayload(String name, Object value);

	/**
	 * 获取参数的值
	 * 
	 * @param name
	 * @return
	 */
	public Object getPayload(String name);
}
