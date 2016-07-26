package cn.xuyingqi.netty.server.echo.protocol;

import java.util.List;

import cn.xuyingqi.netty.server.echo.protocol.datagram.EchoDatagram;
import cn.xuyingqi.netty.server.protocol.Encoder;
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

		// ByteBuf sendBuf = Unpooled.buffer();

		// sendBuf.writeInt(msg.getHeader().getLength());
		// sendBuf.writeBytes(msg.getBody().getBody().getBytes("GBK"));
		// sendBuf.setInt(4, sendBuf.readableBytes());
	}
}
