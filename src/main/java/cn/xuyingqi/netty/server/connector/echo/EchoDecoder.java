package cn.xuyingqi.netty.server.connector.echo;

import cn.xuyingqi.netty.server.connector.Decoder;
import cn.xuyingqi.netty.server.connector.datagram.echo.EchoDatagram;
import cn.xuyingqi.netty.server.connector.datagram.echo.EchoHeader;
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
		 * @lengthFieldOffset 报体长度偏移24字节
		 * @lengthFieldLength 报体长度2字节
		 * @lengthAdjustment 报体长度修正值1
		 * @initialBytesToStrip 报体长度忽略0字节
		 */
		super(1024 * 1024, 24, 2, 1, 0);
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

		// 数据报文
		EchoDatagram datagram = new EchoDatagram();
		// 报头
		EchoHeader header = new EchoHeader();

		// 读取报头信息
		frame.readBytes(header.getCommandId());
		frame.readBytes(header.getTerminalType());
		frame.readBytes(header.getTerminalDeviceNo());
		frame.readBytes(header.getMessageSD());
		frame.readBytes(header.getTimeStamp());
		frame.readBytes(header.getRespondState());
		frame.readBytes(header.getDataLength());
		frame.readBytes(header.getIsNextPacket());

		// 读取报体信息
		int size = frame.readableBytes();
		byte[] payload = new byte[size];
		frame.readBytes(payload);

		// 设置报头,报体
		datagram.setHeader(header);
		datagram.setPayload(payload);

		return datagram;
	}
}
