package cn.xuyingqi.netty.server.selfservice.protocol;

import java.util.List;

import cn.xuyingqi.netty.server.protocol.Encoder;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.SelfServiceDatagram;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 自助缴费编码器
 * 
 * @author XuYQ
 *
 */
public class SelfServiceEncoder extends MessageToMessageEncoder<SelfServiceDatagram> implements Encoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, SelfServiceDatagram msg, List<Object> out) throws Exception {

		System.out.println("11111111111111111111111111111111111");

		byte[] demo = new byte[] { 1, 2, 3, 4 };
		ctx.writeAndFlush(Unpooled.buffer(demo.length).writeBytes(demo));
	}
}
