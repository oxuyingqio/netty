package cn.xuyingqi.netty.servlet;

import java.util.Set;

import cn.xuyingqi.net.servlet.ServletRequest;

/**
 * 服务器端Servlet请求
 * 
 * @author XuYQ
 *
 */
public interface ServerServletRequest extends ServletRequest {

	/**
	 * 获取报头项目名称集合
	 * 
	 * @return
	 */
	public Set<String> getHeaderNames();

	/**
	 * 获取报头某项的值
	 * 
	 * @param name
	 * @return
	 */
	public Object getHeader(String name);

	/**
	 * 获取报体项目名称集合
	 * 
	 * @return
	 */
	public Set<String> getPayloadNames();

	/**
	 * 获取报体某项的值
	 * 
	 * @param name
	 * @return
	 */
	public Object getPayload(String name);
}
