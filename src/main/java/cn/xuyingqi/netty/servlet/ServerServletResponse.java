package cn.xuyingqi.netty.servlet;

import cn.xuyingqi.net.servlet.ServletResponse;

/**
 * 服务器端Servlet响应
 * 
 * @author XuYQ
 *
 */
public interface ServerServletResponse extends ServletResponse {

	/**
	 * 添加报头项目
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServletResponse addHeader(String name, Object value);

	/**
	 * 报头项目是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsHeader(String name);

	/**
	 * 设置报头某项的值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServletResponse setHeader(String name, Object value);

	/**
	 * 添加报体项目
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServletResponse addPayload(String name, Object value);

	/**
	 * 报体项目是否存在
	 * 
	 * @param name
	 * @return
	 */
	public boolean containsPayload(String name);

	/**
	 * 设置报体某项的值
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public ServletResponse setPayload(String name, Object value);
}
