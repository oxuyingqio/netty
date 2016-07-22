package cn.xuyingqi.netty.server.servlet.echo;

import cn.xuyingqi.netty.server.servlet.AbstractServlet;
import cn.xuyingqi.netty.server.servlet.ServletRequest;
import cn.xuyingqi.netty.server.servlet.ServletResponse;

/**
 * 应答Servlet
 * 
 * @author XuYQ
 *
 */
public class EchoServlet extends AbstractServlet {

	private int i = 0;

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		i++;

		System.out.println("EchoServlet执行" + i + "次");
	}
}
