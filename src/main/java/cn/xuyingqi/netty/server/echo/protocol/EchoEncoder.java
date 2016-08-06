package cn.xuyingqi.netty.server.echo.protocol;

import java.util.List;

import cn.xuyingqi.netty.protocol.Encoder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 应答编码器
 * 
 * @author XuYQ
 *
 */
public class EchoEncoder extends MessageToMessageEncoder<String> implements Encoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {

		byte[] data = msg.getBytes();
		ctx.writeAndFlush(Unpooled.buffer(data.length).writeBytes(data));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 后续处理
		ctx.fireExceptionCaught(cause);

		ctx.close();
	}
}
