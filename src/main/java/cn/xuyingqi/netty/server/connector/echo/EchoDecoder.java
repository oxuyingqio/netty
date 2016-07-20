package cn.xuyingqi.netty.server.connector.echo;

import cn.xuyingqi.netty.server.connector.Decoder;
import cn.xuyingqi.netty.study.datagram.Body;
import cn.xuyingqi.netty.study.datagram.Header;
import cn.xuyingqi.netty.study.datagram.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 应答解码器
 * 
 * @author XuYQ
 *
 */
public class EchoDecoder extends LengthFieldBasedFrameDecoder implements Decoder {

	/**
	 * 应答解码器
	 */
	public EchoDecoder() {
		super(1024 * 1024, 0, 4);
	}

	/**
	 * 解码
	 */
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
		byte[] str = new byte[frame.readableBytes()];
		frame.readBytes(str);
		body.setBody(new String(str, "GBK"));

		message.setHeader(header);
		message.setBody(body);
		return message;
	}
}
