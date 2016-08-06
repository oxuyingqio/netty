package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * Servlet处理器
 * 
 * @author XuYQ
 *
 */
public class ClientServletHandler extends ChannelHandlerAdapter {

	/**
	 * 属性值:session
	 */
	private AttributeKey<DefaultServletSession> sessionKey = AttributeKey.valueOf("session");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

//		// 创建session对象
//		DefaultServletSession serverSession = new DefaultServletSession(null, ctx.channel().localAddress(),
//				ctx.channel().remoteAddress());
//
//		// 设置该链接的session属性
//		Attribute<DefaultServletSession> attr = ctx.attr(sessionKey);
//		attr.set(serverSession);

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		// 后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		// 后续处理
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 打印错误信息
		cause.printStackTrace();
		// 关闭连接
		ctx.close();
	}
}
