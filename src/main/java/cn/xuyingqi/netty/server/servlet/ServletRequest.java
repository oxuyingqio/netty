package cn.xuyingqi.netty.server.servlet;

/**
 * Servlet请求
 * 
 * @author XuYQ
 *
 */
public interface ServletRequest {

	/**
	 * 获取Servlet会话
	 * 
	 * @return
	 */
	public ServletSession getServletSession();

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
