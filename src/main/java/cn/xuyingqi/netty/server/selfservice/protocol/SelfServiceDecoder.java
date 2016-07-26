package cn.xuyingqi.netty.server.selfservice.protocol;

import cn.xuyingqi.netty.server.protocol.Decoder;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.SelfServiceDatagram;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Datagram;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Header;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.exception.MACErrorException;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ICCardRechargeRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.PaymentRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.QueryICCardInfoRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ReportWriteICCardRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.RollbackPaymentRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.TerminalAuthenticationRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.TerminalTimingRequest;
import cn.xuyingqi.util.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * 自助缴费解码器
 * 
 * @author XuYQ
 *
 */
public class SelfServiceDecoder extends LengthFieldBasedFrameDecoder implements Decoder {

	/**
	 * 属性值:key
	 */
	private AttributeKey<byte[]> key = AttributeKey.valueOf("key");

	/**
	 * 自助缴费解码器
	 */
	public SelfServiceDecoder() {

		/**
		 * @maxFrameLength 最大长度1M
		 * @lengthFieldOffset 报体长度偏移24字节
		 * @lengthFieldLength 报体长度2字节
		 * @lengthAdjustment 报体长度修正值1+8
		 * @initialBytesToStrip 报体长度忽略0字节
		 */
		super(1024 * 1024, 24, 2, 1 + 8, 0);
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

		// 报头解码
		Header header = new Header();
		frame.readBytes(header.getCommandId());
		frame.readBytes(header.getTerminalType());
		frame.readBytes(header.getTerminalDeviceNo());
		frame.readBytes(header.getMessageSD());
		frame.readBytes(header.getTimeStamp());
		frame.readBytes(header.getRespondState());
		frame.readBytes(header.getDataLength());
		frame.readBytes(header.getIsNextPacket());

		// 获取该通道的密钥
		Attribute<byte[]> attr = ctx.attr(key);
		if (attr.get() == null) {
			attr.set(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		}
		byte[] key = attr.get();

		// 报体解码
		Payload payload = null;
		switch (ByteUtils.byteArray2Int(header.getCommandId())) {
		case 0x0001:
			payload = new TerminalAuthenticationRequest();
			frame.readBytes(((TerminalAuthenticationRequest) payload).getTerminalRandom());
			frame.readBytes(((TerminalAuthenticationRequest) payload).getTerminalTimeStamp());
			break;
		case 0x0002:
			payload = new TerminalTimingRequest();
			break;
		case 0x0101:
			payload = new QueryICCardInfoRequest();
			frame.readBytes(((QueryICCardInfoRequest) payload).getIcCardType());
			frame.readBytes(((QueryICCardInfoRequest) payload).getIcCardPartition());
			frame.readBytes(((QueryICCardInfoRequest) payload).getIcCardDataLength());
			byte[] queryCardData = new byte[ByteUtils
					.byteArray2Int(((QueryICCardInfoRequest) payload).getIcCardDataLength())];
			frame.readBytes(queryCardData);
			((QueryICCardInfoRequest) payload).setIcCardData(queryCardData);
			break;
		case 0x0102:
			payload = new ICCardRechargeRequest();
			frame.readBytes(((ICCardRechargeRequest) payload).getIcCardType());
			frame.readBytes(((ICCardRechargeRequest) payload).getIcCardPartition());
			frame.readBytes(((ICCardRechargeRequest) payload).getIcCardDataLength());
			byte[] rechargeCardData = new byte[ByteUtils
					.byteArray2Int(((ICCardRechargeRequest) payload).getIcCardDataLength())];
			frame.readBytes(rechargeCardData);
			((ICCardRechargeRequest) payload).setIcCardData(rechargeCardData);
			frame.readBytes(((ICCardRechargeRequest) payload).getBuyGas());
			frame.readBytes(((ICCardRechargeRequest) payload).getEntrustUnitNo());
			frame.readBytes(((ICCardRechargeRequest) payload).getTerminalBizSN());
			break;
		case 0x0103:
			payload = new PaymentRequest();
			frame.readBytes(((PaymentRequest) payload).getIcCardType());
			frame.readBytes(((PaymentRequest) payload).getIcCardController());
			frame.readBytes(((PaymentRequest) payload).getIcCardPartition());
			frame.readBytes(((PaymentRequest) payload).getIcCardDataLength());
			byte[] paymentCardData = new byte[ByteUtils
					.byteArray2Int(((PaymentRequest) payload).getIcCardDataLength())];
			frame.readBytes(paymentCardData);
			((PaymentRequest) payload).setIcCardData(paymentCardData);
			frame.readBytes(((PaymentRequest) payload).getIcCardNo());
			frame.readBytes(((PaymentRequest) payload).getUserCode());
			frame.readBytes(((PaymentRequest) payload).getUserName());
			frame.readBytes(((PaymentRequest) payload).getBuyTime());
			frame.readBytes(((PaymentRequest) payload).getBuyGas());
			frame.readBytes(((PaymentRequest) payload).getReceivable());
			frame.readBytes(((PaymentRequest) payload).getEntrustUnitNo());
			frame.readBytes(((PaymentRequest) payload).getTerminalBizSN());
			break;
		case 0x0104:
			payload = new ReportWriteICCardRequest();
			frame.readBytes(((ReportWriteICCardRequest) payload).getIcCardType());
			frame.readBytes(((ReportWriteICCardRequest) payload).getIcCardController());
			frame.readBytes(((ReportWriteICCardRequest) payload).getIcCardNo());
			frame.readBytes(((ReportWriteICCardRequest) payload).getUserCode());
			frame.readBytes(((ReportWriteICCardRequest) payload).getUserName());
			frame.readBytes(((ReportWriteICCardRequest) payload).getBuyTime());
			frame.readBytes(((ReportWriteICCardRequest) payload).getBuyGas());
			frame.readBytes(((ReportWriteICCardRequest) payload).getBuyCost());
			frame.readBytes(((ReportWriteICCardRequest) payload).getPrestore());
			frame.readBytes(((ReportWriteICCardRequest) payload).getPayment());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasPrice1());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasVolume1());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasPrice2());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasVolume2());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasPrice3());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasVolume3());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasPrice4());
			frame.readBytes(((ReportWriteICCardRequest) payload).getGasVolume4());
			frame.readBytes(((ReportWriteICCardRequest) payload).getBankSD());
			frame.readBytes(((ReportWriteICCardRequest) payload).getPaymentDate());
			frame.readBytes(((ReportWriteICCardRequest) payload).getWriteICCardState());
			frame.readBytes(((ReportWriteICCardRequest) payload).getExceptionCause());
			frame.readBytes(((ReportWriteICCardRequest) payload).getWriteICCardDataLength());
			byte[] writeCardData = new byte[ByteUtils
					.byteArray2Int(((ReportWriteICCardRequest) payload).getWriteICCardDataLength())];
			frame.readBytes(writeCardData);
			((ReportWriteICCardRequest) payload).setWriteICCardData(writeCardData);
			frame.readBytes(((ReportWriteICCardRequest) payload).getEntrustUnitNo());
			frame.readBytes(((ReportWriteICCardRequest) payload).getTerminalBizSN());
			break;
		case 0x0105:
			payload = new RollbackPaymentRequest();
			frame.readBytes(((RollbackPaymentRequest) payload).getIcCardType());
			frame.readBytes(((RollbackPaymentRequest) payload).getIcCardController());
			frame.readBytes(((RollbackPaymentRequest) payload).getIcCardNo());
			frame.readBytes(((RollbackPaymentRequest) payload).getUserCode());
			frame.readBytes(((RollbackPaymentRequest) payload).getUserName());
			frame.readBytes(((RollbackPaymentRequest) payload).getBuyTime());
			frame.readBytes(((RollbackPaymentRequest) payload).getBuyGas());
			frame.readBytes(((RollbackPaymentRequest) payload).getRollbackMoney());
			frame.readBytes(((RollbackPaymentRequest) payload).getBankSD());
			frame.readBytes(((RollbackPaymentRequest) payload).getRollbackCause());
			frame.readBytes(((RollbackPaymentRequest) payload).getEntrustUnitNo());
			frame.readBytes(((RollbackPaymentRequest) payload).getTerminalBizSN());
			break;
		}

		// 数据报文解码
		Datagram datagram = new Datagram(header, payload);
		frame.readBytes(datagram.getMAC());

		// 校验MAC
		if (Datagram.checkMAC(datagram, key)) {

			System.out.println(datagram.toString());

			return new SelfServiceDatagram(datagram);

		} else {
			throw new MACErrorException();
		}
	}
}
