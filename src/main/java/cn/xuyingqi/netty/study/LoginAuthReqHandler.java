package cn.xuyingqi.netty.study;

import java.util.Random;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {

	/**
	 * 属性key
	 */
	private AttributeKey<Integer> attrKey = AttributeKey.valueOf("key");

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 异常则关闭连接
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端连接");

		Attribute<Integer> attr = ctx.attr(attrKey);
		int i = new Random().nextInt();
		System.out.println("分配的int值为：" + i);
		attr.set(i);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

		System.out.println("客户端断开连接");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("登录获取分配的int值为：" + ctx.attr(attrKey).get());

		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
