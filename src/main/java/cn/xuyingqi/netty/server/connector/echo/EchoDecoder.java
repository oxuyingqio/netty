package cn.xuyingqi.netty.server.connector.echo;

import cn.xuyingqi.netty.server.connector.Decoder;
import cn.xuyingqi.netty.server.connector.datagram.echo.EchoDatagram;
import cn.xuyingqi.netty.server.connector.datagram.echo.EchoHeader;
import cn.xuyingqi.netty.server.connector.datagram.echo.EchoPayload;
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
		 * 最大长度1M,报体长度偏移24,长度2字节
		 */
		super(1024 * 1024, 24, 2);
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
		// 报体
		EchoPayload payload = new EchoPayload();

		frame.readBytes(header.getCommandId());
		frame.readBytes(header.getTerminalType());
		frame.readBytes(header.getTerminalDeviceNo());
		frame.readBytes(header.getMessageSD());
		frame.readBytes(header.getTimeStamp());
		frame.readBytes(header.getRespondState());
		frame.readBytes(header.getDataLength());
		frame.readBytes(header.getIsNextPacket());

		frame.readBytes(payload.getTerminalRandom());
		frame.readBytes(payload.getTerminalTimeStamp());

		datagram.setHeader(header);
		datagram.setPayload(payload);

		return datagram;
	}
}
