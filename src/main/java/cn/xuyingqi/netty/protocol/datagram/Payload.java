package cn.xuyingqi.netty.protocol.datagram;

/**
 * 报体
 * 
 * @author XuYQ
 *
 */
public interface Payload extends cn.xuyingqi.net.protocol.datagram.Payload {

	/**
	 * 添加参数
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public Payload addPayload(String name, Object value);

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
	public Payload setPayload(String name, Object value);

	/**
	 * 获取参数的值
	 * 
	 * @param name
	 * @return
	 */
	public Object getPayload(String name);
}
