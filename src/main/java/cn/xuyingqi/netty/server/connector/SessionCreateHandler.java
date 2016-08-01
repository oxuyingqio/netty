package cn.xuyingqi.netty.server.connector;

import java.util.UUID;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 会话ID生成
 * 
 * @author XuYQ
 *
 */
public class SessionCreateHandler extends ChannelHandlerAdapter {

	/**
	 * 属性:会话ID
	 */
	private AttributeKey<String> sessionIdAttr = AttributeKey.valueOf("sessionId");

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {

		// 获取会话ID属性
		Attribute<String> attr = ctx.attr(sessionIdAttr);
		// 获取随机UUID
		String id = UUID.randomUUID().toString();
		// 设置会话ID属性值
		attr.set(id);

		// 后续处理
		ctx.fireChannelActive();
	}
}
