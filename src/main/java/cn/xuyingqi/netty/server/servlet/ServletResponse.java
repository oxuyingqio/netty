package cn.xuyingqi.netty.server.servlet;

/**
 * Servlet响应
 * 
 * @author XuYQ
 *
 */
public interface ServletResponse {

	/**
	 * 获取Servlet会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession();

	/**
	 * 获取Servlet请求
	 * 
	 * @return
	 */
	public ServletRequest getServletRequest();

	/**
	 * 设置属性
	 * 
	 * @param name
	 * @param object
	 */
	public void setAttribute(String name, Object object);

	/**
	 * 获取属性
	 * 
	 * @param name
	 * @return
	 */
	public Object getAttribute(String name);
}
