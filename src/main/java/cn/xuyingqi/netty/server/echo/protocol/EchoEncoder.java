package cn.xuyingqi.netty.server.echo.protocol;

import java.util.List;

import cn.xuyingqi.netty.protocol.Encoder;
import cn.xuyingqi.netty.server.echo.protocol.datagram.EchoDatagram;
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

	@Override
	protected void encode(ChannelHandlerContext ctx, EchoDatagram msg, List<Object> out) throws Exception {

		byte[] data = "这只是一个测试而已".getBytes("GBK");
		ctx.writeAndFlush(Unpooled.buffer(data.length).writeBytes(data));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 后续处理
		ctx.fireExceptionCaught(cause);

		ctx.close();
	}
}
