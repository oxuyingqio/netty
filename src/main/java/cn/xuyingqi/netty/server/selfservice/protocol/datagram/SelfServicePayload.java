package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerPayload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.ICCardRechargeRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.PaymentRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.QueryICCardInfoRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.ReportWriteICCardRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.RollbackPaymentRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.TerminalAuthenticationRequestConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ICCardRechargeRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.PaymentRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.QueryICCardInfoRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.ReportWriteICCardRequest;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request.RollbackPaymentRequest;
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
				initTerminalAuthenticationRequest((TerminalAuthenticationRequest) payload);
			} else if (payload instanceof TerminalTimingRequest) {
				initTerminalTimingRequest((TerminalTimingRequest) payload);
			} else if (payload instanceof QueryICCardInfoRequest) {
				initQueryICCardInfoRequest((QueryICCardInfoRequest) payload);
			} else if (payload instanceof ICCardRechargeRequest) {
				initICCardRechargeRequest((ICCardRechargeRequest) payload);
			} else if (payload instanceof PaymentRequest) {
				initPaymentRequest((PaymentRequest) payload);
			} else if (payload instanceof ReportWriteICCardRequest) {
				initReportWriteICCardRequest((ReportWriteICCardRequest) payload);
			} else if (payload instanceof RollbackPaymentRequest) {
				initRollbackPaymentRequest((RollbackPaymentRequest) payload);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 终端认证请求
	 * 
	 * @param request
	 * @throws ParseException
	 */
	private void initTerminalAuthenticationRequest(TerminalAuthenticationRequest request) throws ParseException {
		map.put(TerminalAuthenticationRequestConstant.TERMINAL_RANDOM,
				ByteUtils.byteArray2Long(request.getTerminalRandom()));
		map.put(TerminalAuthenticationRequestConstant.TERMINAL_TIME_STAMP,
				SDF.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(request.getTerminalTimeStamp()))));
	}

	/**
	 * 终端校时请求
	 * 
	 * @param request
	 */
	private void initTerminalTimingRequest(TerminalTimingRequest request) {

	}

	/**
	 * IC卡信息查询请求
	 * 
	 * @param request
	 */
	private void initQueryICCardInfoRequest(QueryICCardInfoRequest request) {
		map.put(QueryICCardInfoRequestConstant.ICCARD_TYPE, ByteUtils.byteArray2Short(request.getIcCardType()));
		map.put(QueryICCardInfoRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		map.put(QueryICCardInfoRequestConstant.ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		map.put(QueryICCardInfoRequestConstant.ICCARD_DATA, request.getIcCardData());
	}

	/**
	 * 充值计费请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initICCardRechargeRequest(ICCardRechargeRequest request) throws UnsupportedEncodingException {
		map.put(ICCardRechargeRequestConstant.ICCARD_TYPE, ByteUtils.byteArray2Short(request.getIcCardType()));
		map.put(ICCardRechargeRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		map.put(ICCardRechargeRequestConstant.ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		map.put(ICCardRechargeRequestConstant.ICCARD_DATA, request.getIcCardData());
		map.put(ICCardRechargeRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		map.put(ICCardRechargeRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		map.put(ICCardRechargeRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	/**
	 * 扣款请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initPaymentRequest(PaymentRequest request) throws UnsupportedEncodingException {
		map.put(PaymentRequestConstant.ICCARD_TYPE, ByteUtils.byteArray2Short(request.getIcCardType()));
		map.put(PaymentRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		map.put(PaymentRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		map.put(PaymentRequestConstant.ICCARD_DATA_LENGTH, ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		map.put(PaymentRequestConstant.ICCARD_DATA, request.getIcCardData());
		map.put(PaymentRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		map.put(PaymentRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		map.put(PaymentRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		map.put(PaymentRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		map.put(PaymentRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		map.put(PaymentRequestConstant.RECEIVABLE, ByteUtils.byteArray2Long(request.getReceivable()));
		map.put(PaymentRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		map.put(PaymentRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	/**
	 * 上报写卡数据请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	private void initReportWriteICCardRequest(ReportWriteICCardRequest request)
			throws UnsupportedEncodingException, ParseException {
		map.put(ReportWriteICCardRequestConstant.ICCARD_TYPE, ByteUtils.byteArray2Short(request.getIcCardType()));
		map.put(ReportWriteICCardRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		map.put(ReportWriteICCardRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		map.put(ReportWriteICCardRequestConstant.BUY_COST, ByteUtils.byteArray2Long(request.getBuyCost()));
		map.put(ReportWriteICCardRequestConstant.PRESTORE, ByteUtils.byteArray2Long(request.getPrestore()));
		map.put(ReportWriteICCardRequestConstant.PAYMENT, ByteUtils.byteArray2Long(request.getPayment()));
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_1, ByteUtils.byteArray2Int(request.getGasPrice1()));
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_1, ByteUtils.byteArray2Int(request.getGasVolume1()));
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_2, ByteUtils.byteArray2Int(request.getGasPrice2()));
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_2, ByteUtils.byteArray2Int(request.getGasVolume2()));
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_3, ByteUtils.byteArray2Int(request.getGasPrice3()));
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_3, ByteUtils.byteArray2Int(request.getGasVolume3()));
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_4, ByteUtils.byteArray2Int(request.getGasPrice4()));
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_4, ByteUtils.byteArray2Int(request.getGasVolume4()));
		map.put(ReportWriteICCardRequestConstant.BANK_SD, new String(request.getBankSD(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.PAYMENT_DATE,
				SDF.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(request.getPaymentDate()))));
		map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_STATE,
				ByteUtils.byteArray2Short(request.getWriteICCardState()));
		map.put(ReportWriteICCardRequestConstant.EXCEPTION_CAUSE, new String(request.getExceptionCause(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getWriteICCardDataLength()));
		map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_DATA, request.getWriteICCardData());
		map.put(ReportWriteICCardRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		map.put(ReportWriteICCardRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	/**
	 * 冲正请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initRollbackPaymentRequest(RollbackPaymentRequest request) throws UnsupportedEncodingException {
		map.put(RollbackPaymentRequestConstant.ICCARD_TYPE, ByteUtils.byteArray2Short(request.getIcCardType()));
		map.put(RollbackPaymentRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		map.put(RollbackPaymentRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		map.put(RollbackPaymentRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		map.put(RollbackPaymentRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		map.put(RollbackPaymentRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		map.put(RollbackPaymentRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		map.put(RollbackPaymentRequestConstant.ROLLBACK_MONEY, ByteUtils.byteArray2Long(request.getRollbackMoney()));
		map.put(RollbackPaymentRequestConstant.BANK_SD, new String(request.getBankSD(), CHARSET));
		map.put(RollbackPaymentRequestConstant.ROLLBACL_CAUSE, new String(request.getRollbackCause(), CHARSET));
		map.put(RollbackPaymentRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		map.put(RollbackPaymentRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	@Override
	public Map<String, Object> convertMap() {

		return map;
	}
}
