package cn.xuyingqi.netty.study;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class LoginAuthReqHandler2 extends ChannelHandlerAdapter {

	/**
	 * 属性key
	 */
	private AttributeKey<Integer> attrKey = AttributeKey.valueOf("key");

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("int值是否一致呢？：" + ctx.attr(attrKey).get());
		
		ctx.fireChannelRead(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
