package cn.xuyingqi.netty.client.echo.protocol;

import cn.xuyingqi.netty.protocol.Decoder;
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
		 * @lengthFieldLength 报体长度4字节
		 * @lengthAdjustment 报体长度修正值0
		 * @initialBytesToStrip 报体长度忽略0字节
		 */
		super(1024 * 1024, 0, 4, 0, 0);
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null) {
			return null;
		}

		// 读取长度
		int length = frame.readInt();
		// 读取剩余字节数
		int size = frame.readableBytes();
		byte[] byteArray = new byte[size];
		frame.readBytes(byteArray);

		// 创建数据报文
		EchoDatagram datagram = new EchoDatagram(length, new String(byteArray));

		return datagram;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

		// 后续处理
		ctx.fireExceptionCaught(cause);

		ctx.close();
	}
}
