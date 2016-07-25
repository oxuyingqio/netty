package cn.xuyingqi.netty.server.servlet;

import java.util.Set;

import cn.xuyingqi.net.servlet.impl.AbstractServletContext;

/**
 * 默认的Servlet上下文
 * 
 * @author XuYQ
 *
 */
public class DefaultServletContext extends AbstractServletContext {

	/**
	 * 未实现,不影响使用
	 */
	@Override
	public Set<String> getInitParamterNames() {
		return null;
	}

	/**
	 * 未实现,不影响使用
	 */
	@Override
	public Object getInitParameter(String name) {
		return null;
	}
}
