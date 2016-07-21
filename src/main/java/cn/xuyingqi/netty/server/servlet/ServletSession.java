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
}
