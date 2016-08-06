package cn.xuyingqi.netty.server.echo.protocol;

import cn.xuyingqi.netty.protocol.Decoder;
import cn.xuyingqi.netty.server.echo.protocol.datagram.EchoDatagram;
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

		/**
		 * @maxFrameLength 最大长度1M
		 * @lengthFieldOffset 报体长度偏移0字节
		 * @lengthFieldLength 报体长度2字节
		 * @lengthAdjustment 报体长度修正值0
		 * @initialBytesToStrip 报体长度忽略0字节
		 */
		super(1024 * 1024, 0, 2, 0, 0);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}

		short length = frame.readShort();
		int size = frame.readableBytes();
		byte[] b = new byte[size];
		frame.readBytes(b);

		EchoDatagram datagram = new EchoDatagram();
		datagram.getHeader().addHeader("length", length);
		datagram.getPayload().addPayload("data", new String(b));

		return datagram;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 后续处理
		ctx.fireExceptionCaught(cause);

		ctx.close();
	}
}
