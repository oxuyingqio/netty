package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerPayload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.ICCardRechargeRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.PaymentRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.QueryICCardInfoRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.TerminalAuthenticationRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ICCardRechargeRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.PaymentRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.QueryICCardInfoRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ReportWriteICCardRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.TerminalAuthenticationRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.TerminalTimingRequest;
import cn.xuyingqi.util.util.ByteUtils;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 自助缴费报体
 * 
 * @author XuYQ
 *
 */
public class SelfServicePayload implements ServerPayload {

	/**
	 * 默认编码集
	 */
	private static final String CHARSET = "GBK";

	/**
	 * 时间格式化
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 报体Map集合
	 */
	private Map<String, Object> map = MapFactory.newInstance();

	/**
	 * 自助缴费报体
	 * 
	 * @param payload
	 */
	public SelfServicePayload(Payload payload) {

		try {
			if (payload instanceof TerminalAuthenticationRequest) {
				map.put(TerminalAuthenticationRequestConstant.TERMINAL_RANDOM,
						ByteUtils.byteArray2Long(((TerminalAuthenticationRequest) payload).getTerminalRandom()));
				map.put(TerminalAuthenticationRequestConstant.TERMINAL_TIME_STAMP, SDF.parse(ByteUtils.byteArray2String(
						ByteUtils.bcd2ByteArray(((TerminalAuthenticationRequest) payload).getTerminalTimeStamp()))));
			} else if (payload instanceof TerminalTimingRequest) {

			} else if (payload instanceof QueryICCardInfoRequest) {
				map.put(QueryICCardInfoRequestConstant.ICCARD_TYPE,
						ByteUtils.byteArray2Short(((QueryICCardInfoRequest) payload).getIcCardType()));
				map.put(QueryICCardInfoRequestConstant.ICCARD_PARTITION,
						ByteUtils.byteArray2Int(((QueryICCardInfoRequest) payload).getIcCardPartition()));
				map.put(QueryICCardInfoRequestConstant.ICCARD_DATA_LENGTH,
						ByteUtils.byteArray2Int(((QueryICCardInfoRequest) payload).getIcCardDataLength()));
				map.put(QueryICCardInfoRequestConstant.ICCARD_DATA, ((QueryICCardInfoRequest) payload).getIcCardData());
			} else if (payload instanceof ICCardRechargeRequest) {
				map.put(ICCardRechargeRequestConstant.ICCARD_TYPE,
						ByteUtils.byteArray2Short(((ICCardRechargeRequest) payload).getIcCardType()));
				map.put(ICCardRechargeRequestConstant.ICCARD_PARTITION,
						ByteUtils.byteArray2Int(((ICCardRechargeRequest) payload).getIcCardPartition()));
				map.put(ICCardRechargeRequestConstant.ICCARD_DATA_LENGTH,
						ByteUtils.byteArray2Int(((ICCardRechargeRequest) payload).getIcCardDataLength()));
				map.put(ICCardRechargeRequestConstant.ICCARD_DATA, ((ICCardRechargeRequest) payload).getIcCardData());
				map.put(ICCardRechargeRequestConstant.BUY_GAS,
						ByteUtils.byteArray2Long(((ICCardRechargeRequest) payload).getBuyGas()));
				map.put(ICCardRechargeRequestConstant.ENTRUST_UNIT_NO,
						new String(((ICCardRechargeRequest) payload).getEntrustUnitNo(), CHARSET));
				map.put(ICCardRechargeRequestConstant.TERMINAL_BIZ_SN,
						ByteUtils.byteArray2Long(((ICCardRechargeRequest) payload).getTerminalBizSN()));
			} else if (payload instanceof PaymentRequest) {
				map.put(PaymentRequestConstant.ICCARD_TYPE,
						ByteUtils.byteArray2Short(((PaymentRequest) payload).getIcCardType()));
				map.put(PaymentRequestConstant.ICCARD_CONTROLLER,
						new String(((PaymentRequest) payload).getIcCardController(), CHARSET));
				map.put(PaymentRequestConstant.ICCARD_PARTITION,
						ByteUtils.byteArray2Int(((PaymentRequest) payload).getIcCardPartition()));
				map.put(PaymentRequestConstant.ICCARD_DATA_LENGTH,
						ByteUtils.byteArray2Int(((PaymentRequest) payload).getIcCardDataLength()));
				map.put(PaymentRequestConstant.ICCARD_DATA, ((PaymentRequest) payload).getIcCardData());
				map.put(PaymentRequestConstant.ICCARD_NO,
						new String(((PaymentRequest) payload).getIcCardNo(), CHARSET));
				map.put(PaymentRequestConstant.USER_CODE,
						new String(((PaymentRequest) payload).getUserCode(), CHARSET));
				map.put(PaymentRequestConstant.USER_NAME,
						new String(((PaymentRequest) payload).getUserName(), CHARSET));
				map.put(PaymentRequestConstant.BUY_TIME,
						ByteUtils.byteArray2Int(((PaymentRequest) payload).getBuyTime()));
				map.put(PaymentRequestConstant.BUY_GAS,
						ByteUtils.byteArray2Long(((PaymentRequest) payload).getBuyGas()));
				map.put(PaymentRequestConstant.RECEIVABLE,
						ByteUtils.byteArray2Long(((PaymentRequest) payload).getReceivable()));
				map.put(PaymentRequestConstant.ENTRUST_UNIT_NO,
						new String(((PaymentRequest) payload).getEntrustUnitNo(), CHARSET));
				map.put(PaymentRequestConstant.TERMINAL_BIZ_SN,
						ByteUtils.byteArray2Long(((PaymentRequest) payload).getTerminalBizSN()));
			} else if (payload instanceof ReportWriteICCardRequest) {

			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> convertMap() {

		return map;
	}
}
