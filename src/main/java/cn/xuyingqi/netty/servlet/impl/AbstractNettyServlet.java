package cn.xuyingqi.netty.servlet.impl;

import cn.xuyingqi.net.servlet.ServletSession;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;

/**
 * 抽象公共Servlet
 * 
 * @author XuYQ
 *
 */
public abstract class AbstractNettyServlet extends AbstractServlet {

	/**
	 * 捕获异常
	 * 
	 * @param session
	 * @param cause
	 * @throws Exception
	 */
	public abstract void exceptionCaught(ServletSession session, Throwable cause);
}