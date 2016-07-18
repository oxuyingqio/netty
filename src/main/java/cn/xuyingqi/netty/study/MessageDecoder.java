package cn.xuyingqi.netty.study;

import cn.xuyingqi.netty.study.datagram.Body;
import cn.xuyingqi.netty.study.datagram.Header;
import cn.xuyingqi.netty.study.datagram.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 解码
 * 
 * @author XuYQ
 *
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

	public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}

		Message message = new Message();
		Header header = new Header();
		Body body = new Body();

		header.setLength(frame.readInt());
		byte[] str = new byte[5];
		in.readBytes(str);
		body.setBody(new String(str, "GBK"));

		message.setHeader(header);
		message.setBody(body);
		return message;
	}
}
