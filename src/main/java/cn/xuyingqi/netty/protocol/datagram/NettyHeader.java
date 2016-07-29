package cn.xuyingqi.netty.protocol.datagram;

import cn.xuyingqi.net.server.protocol.datagram.Header;

/**
 * 报头
 * 
 * @author XuYQ
 *
 */
public interface NettyHeader extends Header {

	/**
	 * 添加报头项目
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public NettyHeader addHeader(String name, Object value);

	/**
	 * 报头项目是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsHeader(String name);

	/**
	 * 设置报头某项值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public NettyHeader setHeader(String name, Object value);

	/**
	 * 获取报头某项值
	 * 
	 * @param name
	 * @return
	 */
	public Object getHeader(String name);
}