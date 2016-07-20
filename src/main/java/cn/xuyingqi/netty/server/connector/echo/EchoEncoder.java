package cn.xuyingqi.netty.server.connector.echo;

import java.util.List;

import cn.xuyingqi.netty.server.connector.Encoder;
import cn.xuyingqi.netty.server.connector.datagram.echo.facade.EchoDatagramFacade;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 应答编码器
 * 
 * @author XuYQ
 *
 */
public class EchoEncoder extends MessageToMessageEncoder<EchoDatagramFacade> implements Encoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, EchoDatagramFacade msg, List<Object> out) throws Exception {

		// ByteBuf sendBuf = Unpooled.buffer();

		// sendBuf.writeInt(msg.getHeader().getLength());
		// sendBuf.writeBytes(msg.getBody().getBody().getBytes("GBK"));
		// sendBuf.setInt(4, sendBuf.readableBytes());
	}
}
