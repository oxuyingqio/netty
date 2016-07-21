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
}
