package cn.xuyingqi.netty.client.echo.protocol;

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
public class EchoEncoder extends MessageToMessageEncoder<EchoDatagram> implements Encoder {

	private int i = 0;

	@Override
	protected void encode(ChannelHandlerContext ctx, EchoDatagram msg, List<Object> out) throws Exception {

		System.out.println("第" + i++ + "发了");

		byte[] data = msg.toByteArray();
		ctx.writeAndFlush(Unpooled.buffer(data.length).writeBytes(data));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 后续处理
		ctx.fireExceptionCaught(cause);

		ctx.close();
	}
}
