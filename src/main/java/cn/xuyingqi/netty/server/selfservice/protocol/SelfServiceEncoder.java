package cn.xuyingqi.netty.server.selfservice.protocol;

import java.util.List;

import cn.xuyingqi.netty.server.protocol.Encoder;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.SelfServiceDatagram;
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

	}
}
