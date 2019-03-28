package cn.xuyingqi.netty.servlet.impl;

import java.util.Set;

import cn.xuyingqi.net.servlet.impl.AbstractServletContext;
import cn.xuyingqi.util.SetFactory;

/**
 * 默认的Servlet上下文
 * 
 * @author XuYQ
 *
 */
public final class DefaultServletContext extends AbstractServletContext {

	@Override
	public Set<String> getInitParamterNames() {

		// 默认Servlet上下文不实现该功能
		return SetFactory.newInstance();
	}

	@Override
	public Object getInitParameter(String name) {

		// 默认Servlet上下文不实现该功能
		return null;
	}
}