package cn.xuyingqi.netty.server.echo.servlet;

import java.util.Queue;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.net.servlet.ServerServletRequest;
import cn.xuyingqi.net.servlet.ServerServletResponse;
import cn.xuyingqi.net.servlet.ServletRequest;
import cn.xuyingqi.net.servlet.ServletResponse;
import cn.xuyingqi.net.servlet.impl.AbstractServlet;
import cn.xuyingqi.netty.server.container.ChannelContainer;
import cn.xuyingqi.netty.server.echo.message.Message;
import cn.xuyingqi.netty.server.echo.message.MessageContainer;
import cn.xuyingqi.netty.server.echo.message.MessageType;
import cn.xuyingqi.netty.server.echo.protocol.datagram.EchoDatagram;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class EchoServlet extends AbstractServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) {

		this.service((ServerServletRequest) request, (ServerServletResponse) response);
	}

	@SuppressWarnings("unchecked")
	private void service(ServerServletRequest request, ServerServletResponse response) {

		System.out.println("来了========");

		try {
			Thread.sleep(1000);
			
			
			Datagram datagram = new EchoDatagram();
			ChannelContainer.getInstance().getChannel(request.getServletSessionId()).writeAndFlush(datagram)
					.addListeners(new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture cf) throws Exception {

							if (cf.isDone()) {

								Queue<Message> queue = MessageContainer.getInstance().getMessages(cf.channel());
								Message[] demo = new Message[queue.size()];
								queue.toArray(demo);
								Message lastMessage = demo[queue.size() - 1];
								
								while (lastMessage.getDatagram() == datagram
										|| lastMessage.getMessageType() == MessageType.RESPONSE) {
									
									Thread.sleep(5000);
									
									queue = MessageContainer.getInstance().getMessages(cf.channel());
									demo = new Message[queue.size()];
									queue.toArray(demo);
									lastMessage = demo[queue.size() - 1];
								}
								
								System.out.println(lastMessage.getDatagram());
							}
						}
					});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}
