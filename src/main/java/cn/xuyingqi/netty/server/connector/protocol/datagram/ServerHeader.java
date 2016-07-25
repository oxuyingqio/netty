package cn.xuyingqi.netty.server.connector.protocol.datagram;

import cn.xuyingqi.net.server.protocol.datagram.Header;

/**
 * 报头
 * 
 * @author XuYQ
 *
 */
public interface ServerHeader extends Header {

	/**
	 * 添加报头项目
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServerHeader addHeader(String name, Object value);

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
	public ServerHeader setHeader(String name, Object value);

	/**
	 * 获取编码格式
	 * 
	 * @return
	 */
	public String getCharacterEncoding();

	/**
	 * 设置编码格式
	 * 
	 * @param charset
	 *            编码格式
	 * @return
	 */
	public ServerHeader setCharacterEncoding(String charset);

	/**
	 * 获取内容类型
	 * 
	 * @return
	 */
	public String getContentType();

	/**
	 * 设置内容类型
	 * 
	 * @param type
	 * @return
	 */
	public ServerHeader setContentType(String type);

	/**
	 * 获取报体长度
	 * 
	 * @return
	 */
	public int getContentLength();

	/**
	 * 设置报体长度
	 * 
	 * @param len
	 * @return
	 */
	public ServerHeader setContentLength(int len);

	/**
	 * 设置响应状态
	 * 
	 * @param status
	 * @return
	 */
	public ServerHeader setStatus(int status);
}
