package cn.xuyingqi.netty.client.connector;

import cn.xuyingqi.netty.servlet.impl.DefaultServletSession;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * 测试处理器
 * 
 * @author XuYQ
 *
 */
public class TestHandler extends ChannelHandlerAdapter {

	/**
	 * 属性值:session
	 */
	private AttributeKey<DefaultServletSession> serverSessionKey = AttributeKey.valueOf("session");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println(ctx.channel().localAddress());
		System.out.println(ctx.channel().remoteAddress());

		System.out.println("连上了");

		// 后续处理
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("断开了");

		// 后续处理
		ctx.fireChannelInactive();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		byte[] demo = "this is first message".getBytes();
		ctx.writeAndFlush(Unpooled.buffer(demo.length).writeBytes(demo));

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
