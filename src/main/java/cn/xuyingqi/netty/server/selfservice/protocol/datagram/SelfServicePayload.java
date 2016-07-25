package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerPayload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.ICCardRechargeRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.QueryICCardInfoRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.TerminalAuthenticationRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ICCardRechargeRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.QueryICCardInfoRequest;
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
	 * 时间格式化
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 报体Map集合
	 */
	private Map<String, Object> payloadMap = MapFactory.newInstance();

	/**
	 * 自助缴费报体
	 * 
	 * @param payload
	 */
	public SelfServicePayload(Payload payload) {

		if (payload instanceof TerminalAuthenticationRequest) {
			payloadMap.put(TerminalAuthenticationRequestConstant.TERMINAL_RANDOM,
					ByteUtils.byteArray2Long(((TerminalAuthenticationRequest) payload).getTerminalRandom()));
			try {
				payloadMap.put(TerminalAuthenticationRequestConstant.TERMINAL_TIME_STAMP,
						SDF.parse(ByteUtils.byteArray2String(ByteUtils
								.bcd2ByteArray(((TerminalAuthenticationRequest) payload).getTerminalTimeStamp()))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (payload instanceof TerminalTimingRequest) {

		} else if (payload instanceof QueryICCardInfoRequest) {
			payloadMap.put(QueryICCardInfoRequestConstant.ICCARD_TYPE,
					ByteUtils.byteArray2Short(((QueryICCardInfoRequest) payload).getIcCardType()));
			payloadMap.put(QueryICCardInfoRequestConstant.ICCARD_PARTITION,
					ByteUtils.byteArray2Int(((QueryICCardInfoRequest) payload).getIcCardPartition()));
			payloadMap.put(QueryICCardInfoRequestConstant.ICCARD_DATA_LENGTH,
					ByteUtils.byteArray2Int(((QueryICCardInfoRequest) payload).getIcCardDataLength()));
			payloadMap.put(QueryICCardInfoRequestConstant.ICCARD_DATA,
					((QueryICCardInfoRequest) payload).getIcCardData());
		} else if (payload instanceof ICCardRechargeRequest) {
			payloadMap.put(ICCardRechargeRequestConstant.ICCARD_TYPE,
					ByteUtils.byteArray2Short(((ICCardRechargeRequest) payload).getIcCardType()));
			payloadMap.put(ICCardRechargeRequestConstant.ICCARD_PARTITION,
					ByteUtils.byteArray2Int(((ICCardRechargeRequest) payload).getIcCardPartition()));
			payloadMap.put(ICCardRechargeRequestConstant.ICCARD_DATA_LENGTH,
					ByteUtils.byteArray2Int(((ICCardRechargeRequest) payload).getIcCardDataLength()));
			payloadMap.put(ICCardRechargeRequestConstant.ICCARD_DATA,
					((ICCardRechargeRequest) payload).getIcCardData());
			payloadMap.put(ICCardRechargeRequestConstant.BUY_GAS,
					ByteUtils.byteArray2Long(((ICCardRechargeRequest) payload).getBuyGas()));
		}
	}

	@Override
	public Map<String, Object> convertMap() {

		return payloadMap;
	}
}
