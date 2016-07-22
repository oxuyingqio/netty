package cn.xuyingqi.netty.server.servlet;

/**
 * Servlet会话
 * 
 * @author XuYQ
 *
 */
public interface ServletSession {

	/**
	 * 获取Servlet上下文
	 * 
	 * @return
	 */
	public ServletContext getServletContext();

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
