package cn.xuyingqi.netty.server.echo.servlet;

import java.util.Random;

import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;

/**
 * 应答Servlet
 * 
 * @author XuYQ
 *
 */
public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		String password = (String) request.getServletSession().getAttribute("password");
		if (password == null) {
			password = new Random().nextInt() + "";
			request.getServletSession().setAttribute("password", password);
			System.out.println("客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "设置密码：" + password);
		} else {
			System.out.println("客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "当前密码：" + password);
		}

		// 信息
		String msg = (String) request.getServletSession().getServletContext().getAttribute("msg");
		if (msg == null) {
			request.getServletSession().getServletContext().setAttribute("msg",
					"客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "到此一游");

			System.out.println("客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "说到此一游");
		} else {
			System.out.println("有人说：" + msg);

			request.getServletSession().getServletContext().setAttribute("msg",
					"客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "到此一游");

			System.out.println("客户端" + request.getRemoteAddr() + ":" + request.getRemotePort() + "说到此一游");
		}

	}

	@Override
	public void destroy() {

	}

	@Override
	public String getServletInfo() {

		return "应答(Echo) Servlet";
	}
}
