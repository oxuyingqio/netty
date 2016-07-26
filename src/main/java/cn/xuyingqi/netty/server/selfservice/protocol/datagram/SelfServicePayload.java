package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.protocol.datagram.ServerPayload;
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
import cn.xuyingqi.netty.server.selfservice.temp.ICCardType;
import cn.xuyingqi.netty.server.selfservice.temp.WriteCardResult;
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

		// 终端随机数
		map.put(TerminalAuthenticationRequestConstant.TERMINAL_RANDOM,
				ByteUtils.byteArray2Long(request.getTerminalRandom()));
		// 终端时间
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

		// 卡类型
		short icCardType = ByteUtils.byteArray2Short(request.getIcCardType());
		switch (icCardType) {
		case 0x01:
			map.put(QueryICCardInfoRequestConstant.ICCARD_TYPE, ICCardType.IC4442);
			break;
		case 0x02:
			map.put(QueryICCardInfoRequestConstant.ICCARD_TYPE, ICCardType.IC153);
			break;
		}
		// 分区或文件数量
		map.put(QueryICCardInfoRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		// 数据域长度
		map.put(QueryICCardInfoRequestConstant.ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		// 数据域
		map.put(QueryICCardInfoRequestConstant.ICCARD_DATA, request.getIcCardData());
	}

	/**
	 * 充值计费请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initICCardRechargeRequest(ICCardRechargeRequest request) throws UnsupportedEncodingException {

		// 卡类型
		short icCardType = ByteUtils.byteArray2Short(request.getIcCardType());
		switch (icCardType) {
		case 0x01:
			map.put(ICCardRechargeRequestConstant.ICCARD_TYPE, ICCardType.IC4442);
			break;
		case 0x02:
			map.put(ICCardRechargeRequestConstant.ICCARD_TYPE, ICCardType.IC153);
			break;
		}
		// 分区或文件数量
		map.put(ICCardRechargeRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		// 数据长度
		map.put(ICCardRechargeRequestConstant.ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		// 卡片数据
		map.put(ICCardRechargeRequestConstant.ICCARD_DATA, request.getIcCardData());
		// 购气量
		map.put(ICCardRechargeRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		// 委托单位编号
		map.put(ICCardRechargeRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		// 终端业务流水号
		map.put(ICCardRechargeRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	/**
	 * 扣款请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initPaymentRequest(PaymentRequest request) throws UnsupportedEncodingException {

		// 卡类型
		short icCardType = ByteUtils.byteArray2Short(request.getIcCardType());
		switch (icCardType) {
		case 0x01:
			map.put(PaymentRequestConstant.ICCARD_TYPE, ICCardType.IC4442);
			break;
		case 0x02:
			map.put(PaymentRequestConstant.ICCARD_TYPE, ICCardType.IC153);
			break;
		}
		// 控制器类型
		map.put(PaymentRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		// 分区或文件数量
		map.put(PaymentRequestConstant.ICCARD_PARTITION, ByteUtils.byteArray2Int(request.getIcCardPartition()));
		// 数据长度
		map.put(PaymentRequestConstant.ICCARD_DATA_LENGTH, ByteUtils.byteArray2Int(request.getIcCardDataLength()));
		// 卡片数据
		map.put(PaymentRequestConstant.ICCARD_DATA, request.getIcCardData());
		// 卡号
		map.put(PaymentRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		// 用户号
		map.put(PaymentRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		// 用户名
		map.put(PaymentRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		// 购气次数
		map.put(PaymentRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		// 购气量
		map.put(PaymentRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		// 应收金额
		map.put(PaymentRequestConstant.RECEIVABLE, ByteUtils.byteArray2Long(request.getReceivable()));
		// 委托单位编号
		map.put(PaymentRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		// 终端业务流水号
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

		// 卡类型
		short icCardType = ByteUtils.byteArray2Short(request.getIcCardType());
		switch (icCardType) {
		case 0x01:
			map.put(ReportWriteICCardRequestConstant.ICCARD_TYPE, ICCardType.IC4442);
			break;
		case 0x02:
			map.put(ReportWriteICCardRequestConstant.ICCARD_TYPE, ICCardType.IC153);
			break;
		}
		// 控制器类型
		map.put(ReportWriteICCardRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		// 卡号
		map.put(ReportWriteICCardRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		// 用户号
		map.put(ReportWriteICCardRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		// 用户名
		map.put(ReportWriteICCardRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		// 购气次数
		map.put(ReportWriteICCardRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		// 购气量
		map.put(ReportWriteICCardRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		// 购气金额
		map.put(ReportWriteICCardRequestConstant.BUY_COST, ByteUtils.byteArray2Long(request.getBuyCost()));
		// 预存支付
		map.put(ReportWriteICCardRequestConstant.PRESTORE, ByteUtils.byteArray2Long(request.getPrestore()));
		// 扣款金额
		map.put(ReportWriteICCardRequestConstant.PAYMENT, ByteUtils.byteArray2Long(request.getPayment()));
		// 气价1
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_1, ByteUtils.byteArray2Int(request.getGasPrice1()));
		// 气量1
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_1, ByteUtils.byteArray2Int(request.getGasVolume1()));
		// 气价2
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_2, ByteUtils.byteArray2Int(request.getGasPrice2()));
		// 气量2
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_2, ByteUtils.byteArray2Int(request.getGasVolume2()));
		// 气价3
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_3, ByteUtils.byteArray2Int(request.getGasPrice3()));
		// 气量3
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_3, ByteUtils.byteArray2Int(request.getGasVolume3()));
		// 气价4
		map.put(ReportWriteICCardRequestConstant.GAS_PRICE_4, ByteUtils.byteArray2Int(request.getGasPrice4()));
		// 气量4
		map.put(ReportWriteICCardRequestConstant.GAS_VOLUME_4, ByteUtils.byteArray2Int(request.getGasVolume4()));
		// 银行业务流水号
		map.put(ReportWriteICCardRequestConstant.BANK_SD, new String(request.getBankSD(), CHARSET));
		// 扣款时间
		map.put(ReportWriteICCardRequestConstant.PAYMENT_DATE,
				SDF.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(request.getPaymentDate()))));
		// 写卡结果
		short writeIcCardState = ByteUtils.byteArray2Short(request.getWriteICCardState());
		switch (writeIcCardState) {
		case 0:
			map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_STATE, WriteCardResult.FAIL);
			break;
		case 1:
			map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_STATE, WriteCardResult.SUCCESS);
			break;
		}
		// 失败原因
		map.put(ReportWriteICCardRequestConstant.EXCEPTION_CAUSE, new String(request.getExceptionCause(), CHARSET));
		// 数据长度
		map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_DATA_LENGTH,
				ByteUtils.byteArray2Int(request.getWriteICCardDataLength()));
		// 写卡后数据
		map.put(ReportWriteICCardRequestConstant.WRITE_ICCARD_DATA, request.getWriteICCardData());
		// 委托单位编号
		map.put(ReportWriteICCardRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		// 终端业务流水号
		map.put(ReportWriteICCardRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	/**
	 * 冲正请求
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void initRollbackPaymentRequest(RollbackPaymentRequest request) throws UnsupportedEncodingException {

		// 卡类型
		short icCardType = ByteUtils.byteArray2Short(request.getIcCardType());
		switch (icCardType) {
		case 0x01:
			map.put(RollbackPaymentRequestConstant.ICCARD_TYPE, ICCardType.IC4442);
			break;
		case 0x02:
			map.put(RollbackPaymentRequestConstant.ICCARD_TYPE, ICCardType.IC153);
			break;
		}
		// 控制器类型
		map.put(RollbackPaymentRequestConstant.ICCARD_CONTROLLER, new String(request.getIcCardController(), CHARSET));
		// 卡号
		map.put(RollbackPaymentRequestConstant.ICCARD_NO, new String(request.getIcCardNo(), CHARSET));
		// 用户号
		map.put(RollbackPaymentRequestConstant.USER_CODE, new String(request.getUserCode(), CHARSET));
		// 用户名
		map.put(RollbackPaymentRequestConstant.USER_NAME, new String(request.getUserName(), CHARSET));
		// 购气次数
		map.put(RollbackPaymentRequestConstant.BUY_TIME, ByteUtils.byteArray2Int(request.getBuyTime()));
		// 购气量
		map.put(RollbackPaymentRequestConstant.BUY_GAS, ByteUtils.byteArray2Long(request.getBuyGas()));
		// 冲正金额
		map.put(RollbackPaymentRequestConstant.ROLLBACK_MONEY, ByteUtils.byteArray2Long(request.getRollbackMoney()));
		// 银行业务流水号
		map.put(RollbackPaymentRequestConstant.BANK_SD, new String(request.getBankSD(), CHARSET));
		// 冲正原因
		map.put(RollbackPaymentRequestConstant.ROLLBACL_CAUSE, new String(request.getRollbackCause(), CHARSET));
		// 委托单位编号
		map.put(RollbackPaymentRequestConstant.ENTRUST_UNIT_NO, new String(request.getEntrustUnitNo(), CHARSET));
		// 终端业务流水号
		map.put(RollbackPaymentRequestConstant.TERMINAL_BIZ_SN, ByteUtils.byteArray2Long(request.getTerminalBizSN()));
	}

	@Override
	public Map<String, Object> toMap() {

		return map;
	}

	@Override
	public ServerPayload addParamter(String name, Object value) {

		map.put(name, value);

		return this;
	}

	@Override
	public boolean containsParamter(String name) {

		return map.get(name) != null;
	}

	@Override
	public ServerPayload setParamter(String name, Object value) {

		map.put(name, value);

		return this;
	}
}
