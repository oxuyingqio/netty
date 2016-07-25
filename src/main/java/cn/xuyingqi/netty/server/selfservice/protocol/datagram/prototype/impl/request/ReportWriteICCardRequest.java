package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 上报写卡信息-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class ReportWriteICCardRequest extends Payload {

	/**
	 * 卡类型默认长度
	 */
	private static final int ICCARD_TYPE_LENGTH = 1;
	/**
	 * 控制器类型默认长度
	 */
	private static final int ICCARD_CONTROLLER_LENGTH = 10;
	/**
	 * 卡号默认长度
	 */
	private static final int ICCARD_NO_LENGTH = 12;
	/**
	 * 用户号默认长度
	 */
	private static final int USER_CODE_LENGTH = 12;
	/**
	 * 用户名默认长度
	 */
	private static final int USER_NAME_LENGTH = 10;
	/**
	 * 购气次数默认长度
	 */
	private static final int BUY_TIME_LENGTH = 2;
	/**
	 * 购气量默认长度
	 */
	private static final int BUY_GAS_LENGTH = 4;
	/**
	 * 购气金额默认长度
	 */
	private static final int BUY_COST_LENGTH = 4;
	/**
	 * 预存支付默认长度
	 */
	private static final int PRESTORE_LENGTH = 4;
	/**
	 * 扣款金额默认长度
	 */
	private static final int PAYMENT_LENGTH = 4;
	/**
	 * 气价1默认长度
	 */
	private static final int GAS_PRICE_1_LENGTH = 2;
	/**
	 * 气量1默认长度
	 */
	private static final int GAS_VOLUME_1_LENGTH = 2;
	/**
	 * 气价2默认长度
	 */
	private static final int GAS_PRICE_2_LENGTH = 2;
	/**
	 * 气量2默认长度
	 */
	private static final int GAS_VOLUME_2_LENGTH = 2;
	/**
	 * 气价3默认长度
	 */
	private static final int GAS_PRICE_3_LENGTH = 2;
	/**
	 * 气量3默认长度
	 */
	private static final int GAS_VOLUME_3_LENGTH = 2;
	/**
	 * 气价4默认长度
	 */
	private static final int GAS_PRICE_4_LENGTH = 2;
	/**
	 * 气量4默认长度
	 */
	private static final int GAS_VOLUME_4_LENGTH = 2;
	/**
	 * 银行业务流水号默认长度
	 */
	private static final int BANK_SD_LENGTH = 10;
	/**
	 * 扣款时间默认长度
	 */
	private static final int PAYMENT_DATE_LENGTH = 7;
	/**
	 * 写卡结果默认长度
	 */
	private static final int WRITE_ICCARD_STATE_LENGTH = 1;
	/**
	 * 失败原因默认长度
	 */
	private static final int EXCEPTION_CAUSE_LENGTH = 32;
	/**
	 * 写卡后数据长度默认长度
	 */
	private static final int WRITE_ICCARD_DATA_LENGTH_LENGTH = 2;
	/**
	 * 委托单位编号默认长度
	 */
	private static final int ENTRUST_UNIT_NO_LENGTH = 10;
	/**
	 * 终端业务流水号默认长度
	 */
	private static final int TERMINAL_BIZ_SN_LENGTH = 4;

	/**
	 * 卡类型
	 */
	private byte[] icCardType = new byte[ICCARD_TYPE_LENGTH];
	/**
	 * 控制器类型
	 */
	private byte[] icCardController = new byte[ICCARD_CONTROLLER_LENGTH];
	/**
	 * 卡号
	 */
	private byte[] icCardNo = new byte[ICCARD_NO_LENGTH];
	/**
	 * 用户号
	 */
	private byte[] userCode = new byte[USER_CODE_LENGTH];
	/**
	 * 用户名
	 */
	private byte[] userName = new byte[USER_NAME_LENGTH];
	/**
	 * 购气次数
	 */
	private byte[] buyTime = new byte[BUY_TIME_LENGTH];
	/**
	 * 购气量
	 */
	private byte[] buyGas = new byte[BUY_GAS_LENGTH];
	/**
	 * 购气金额
	 */
	private byte[] buyCost = new byte[BUY_COST_LENGTH];
	/**
	 * 预存支付
	 */
	private byte[] prestore = new byte[PRESTORE_LENGTH];
	/**
	 * 扣款金额
	 */
	private byte[] payment = new byte[PAYMENT_LENGTH];
	/**
	 * 气价1
	 */
	private byte[] gasPrice1 = new byte[GAS_PRICE_1_LENGTH];
	/**
	 * 气量1
	 */
	private byte[] gasVolume1 = new byte[GAS_VOLUME_1_LENGTH];
	/**
	 * 气价2
	 */
	private byte[] gasPrice2 = new byte[GAS_PRICE_2_LENGTH];
	/**
	 * 气量2
	 */
	private byte[] gasVolume2 = new byte[GAS_VOLUME_2_LENGTH];
	/**
	 * 气价3
	 */
	private byte[] gasPrice3 = new byte[GAS_PRICE_3_LENGTH];
	/**
	 * 气量3
	 */
	private byte[] gasVolume3 = new byte[GAS_VOLUME_3_LENGTH];
	/**
	 * 气价4
	 */
	private byte[] gasPrice4 = new byte[GAS_PRICE_4_LENGTH];
	/**
	 * 气量4
	 */
	private byte[] gasVolume4 = new byte[GAS_VOLUME_4_LENGTH];
	/**
	 * 银行业务流水号
	 */
	private byte[] bankSD = new byte[BANK_SD_LENGTH];
	/**
	 * 扣款时间
	 */
	private byte[] paymentDate = new byte[PAYMENT_DATE_LENGTH];
	/**
	 * 写卡结果
	 */
	private byte[] writeICCardState = new byte[WRITE_ICCARD_STATE_LENGTH];
	/**
	 * 失败原因
	 */
	private byte[] exceptionCause = new byte[EXCEPTION_CAUSE_LENGTH];
	/**
	 * 写卡数据长度
	 */
	private byte[] writeICCardDataLength = new byte[WRITE_ICCARD_DATA_LENGTH_LENGTH];
	/**
	 * 写卡数据
	 */
	private byte[] writeICCardData;
	/**
	 * 委托单位编号
	 */
	private byte[] entrustUnitNo = new byte[ENTRUST_UNIT_NO_LENGTH];
	/**
	 * 终端业务流水号
	 */
	private byte[] terminalBizSN = new byte[TERMINAL_BIZ_SN_LENGTH];

	/**
	 * 获取卡类型
	 * 
	 * @return
	 */
	public byte[] getIcCardType() {

		return icCardType;
	}

	/**
	 * 获取控制器类型
	 * 
	 * @return
	 */
	public byte[] getIcCardController() {

		return icCardController;
	}

	/**
	 * 获取卡号
	 * 
	 * @return
	 */
	public byte[] getIcCardNo() {

		return icCardNo;
	}

	/**
	 * 获取用户号
	 * 
	 * @return
	 */
	public byte[] getUserCode() {

		return userCode;
	}

	/**
	 * 获取用户名
	 * 
	 * @return
	 */
	public byte[] getUserName() {

		return userName;
	}

	/**
	 * 获取购气次数
	 * 
	 * @return
	 */
	public byte[] getBuyTime() {

		return buyTime;
	}

	/**
	 * 获取购气量
	 * 
	 * @return
	 */
	public byte[] getBuyGas() {

		return buyGas;
	}

	/**
	 * 获取购气金额
	 * 
	 * @return
	 */
	public byte[] getBuyCost() {

		return buyCost;
	}

	/**
	 * 获取预存支付
	 * 
	 * @return
	 */
	public byte[] getPrestore() {

		return prestore;
	}

	/**
	 * 获取扣款金额
	 * 
	 * @return
	 */
	public byte[] getPayment() {

		return payment;
	}

	/**
	 * 获取气价1
	 * 
	 * @return
	 */
	public byte[] getGasPrice1() {

		return gasPrice1;
	}

	/**
	 * 获取气量1
	 * 
	 * @return
	 */
	public byte[] getGasVolume1() {

		return gasVolume1;
	}

	/**
	 * 获取气价2
	 * 
	 * @return
	 */
	public byte[] getGasPrice2() {

		return gasPrice2;
	}

	/**
	 * 获取气量2
	 * 
	 * @return
	 */
	public byte[] getGasVolume2() {

		return gasVolume2;
	}

	/**
	 * 获取气价3
	 * 
	 * @return
	 */
	public byte[] getGasPrice3() {

		return gasPrice3;
	}

	/**
	 * 获取气量3
	 * 
	 * @return
	 */
	public byte[] getGasVolume3() {

		return gasVolume3;
	}

	/**
	 * 获取气价4
	 * 
	 * @return
	 */
	public byte[] getGasPrice4() {

		return gasPrice4;
	}

	/**
	 * 获取气量4
	 * 
	 * @return
	 */
	public byte[] getGasVolume4() {

		return gasVolume4;
	}

	/**
	 * 获取银行业务流水号
	 * 
	 * @return
	 */
	public byte[] getBankSD() {

		return bankSD;
	}

	/**
	 * 获取扣款时间
	 * 
	 * @return
	 */
	public byte[] getPaymentDate() {

		return paymentDate;
	}

	/**
	 * 获取写卡结果
	 * 
	 * @return
	 */
	public byte[] getWriteICCardState() {

		return writeICCardState;
	}

	/**
	 * 获取失败原因
	 * 
	 * @return
	 */
	public byte[] getExceptionCause() {

		return exceptionCause;
	}

	/**
	 * 获取写卡数据长度
	 * 
	 * @return
	 */
	public byte[] getWriteICCardDataLength() {

		return writeICCardDataLength;
	}

	/**
	 * 获取写卡数据
	 * 
	 * @return
	 */
	public byte[] getWriteICCardData() {

		return writeICCardData;
	}

	/**
	 * 设置写卡数据
	 * 
	 * @param writeICCardData
	 */
	public void setWriteICCardData(byte[] writeICCardData) {

		this.writeICCardData = writeICCardData;
	}

	/**
	 * 获取委托单位编号
	 * 
	 * @return
	 */
	public byte[] getEntrustUnitNo() {

		return entrustUnitNo;
	}

	/**
	 * 获取终端业务流水号
	 * 
	 * @return
	 */
	public byte[] getTerminalBizSN() {

		return terminalBizSN;
	}

	@Override
	public byte[] toByteArray() {

		// 待返回的字节数组
		byte[] byteArray = new byte[this.icCardType.length + this.icCardController.length + this.icCardNo.length
				+ this.userCode.length + this.userName.length + this.buyTime.length + this.buyGas.length
				+ this.buyCost.length + this.prestore.length + this.payment.length + this.gasPrice1.length
				+ this.gasVolume1.length + this.gasPrice2.length + this.gasVolume2.length + this.gasPrice3.length
				+ this.gasVolume3.length + this.gasPrice4.length + this.gasVolume4.length + this.bankSD.length
				+ this.paymentDate.length + this.writeICCardState.length + this.exceptionCause.length
				+ this.writeICCardDataLength.length + this.writeICCardData.length + this.entrustUnitNo.length
				+ this.terminalBizSN.length];

		// 索引值
		int index = 0;

		// 卡类型
		System.arraycopy(this.icCardType, 0, byteArray, index, this.icCardType.length);
		index += this.icCardType.length;
		// 控制器类型
		System.arraycopy(this.icCardController, 0, byteArray, index, this.icCardController.length);
		index += this.icCardController.length;
		// 卡号
		System.arraycopy(this.icCardNo, 0, byteArray, index, this.icCardNo.length);
		index += this.icCardNo.length;
		// 用户号
		System.arraycopy(this.userCode, 0, byteArray, index, this.userCode.length);
		index += this.userCode.length;
		// 用户名
		System.arraycopy(this.userName, 0, byteArray, index, this.userName.length);
		index += this.userName.length;
		// 购气次数
		System.arraycopy(this.buyTime, 0, byteArray, index, this.buyTime.length);
		index += this.buyTime.length;
		// 购气量
		System.arraycopy(this.buyGas, 0, byteArray, index, this.buyGas.length);
		index += this.buyGas.length;
		// 购气金额
		System.arraycopy(this.buyCost, 0, byteArray, index, this.buyCost.length);
		index += this.buyCost.length;
		// 预存支付
		System.arraycopy(this.prestore, 0, byteArray, index, this.prestore.length);
		index += this.prestore.length;
		// 扣款金额
		System.arraycopy(this.payment, 0, byteArray, index, this.payment.length);
		index += this.payment.length;
		// 气价1
		System.arraycopy(this.gasPrice1, 0, byteArray, index, this.gasPrice1.length);
		index += this.gasPrice1.length;
		// 气量1
		System.arraycopy(this.gasVolume1, 0, byteArray, index, this.gasVolume1.length);
		index += this.gasVolume1.length;
		// 气价2
		System.arraycopy(this.gasPrice2, 0, byteArray, index, this.gasPrice2.length);
		index += this.gasPrice2.length;
		// 气量2
		System.arraycopy(this.gasVolume2, 0, byteArray, index, this.gasVolume2.length);
		index += this.gasVolume2.length;
		// 气价3
		System.arraycopy(this.gasPrice3, 0, byteArray, index, this.gasPrice3.length);
		index += this.gasPrice3.length;
		// 气量3
		System.arraycopy(this.gasVolume3, 0, byteArray, index, this.gasVolume3.length);
		index += this.gasVolume3.length;
		// 气价4
		System.arraycopy(this.gasPrice4, 0, byteArray, index, this.gasPrice4.length);
		index += this.gasPrice4.length;
		// 气量4
		System.arraycopy(this.gasVolume4, 0, byteArray, index, this.gasVolume4.length);
		index += this.gasVolume4.length;
		// 银行业务流水号
		System.arraycopy(this.bankSD, 0, byteArray, index, this.bankSD.length);
		index += this.bankSD.length;
		// 扣款时间
		System.arraycopy(this.paymentDate, 0, byteArray, index, this.paymentDate.length);
		index += this.paymentDate.length;
		// 写卡结果
		System.arraycopy(this.writeICCardState, 0, byteArray, index, this.writeICCardState.length);
		index += this.writeICCardState.length;
		// 失败原因
		System.arraycopy(this.exceptionCause, 0, byteArray, index, this.exceptionCause.length);
		index += this.exceptionCause.length;
		// 写卡数据长度
		System.arraycopy(this.writeICCardDataLength, 0, byteArray, index, this.writeICCardDataLength.length);
		index += this.writeICCardDataLength.length;
		// 写卡数据
		System.arraycopy(this.writeICCardData, 0, byteArray, index, this.writeICCardData.length);
		index += this.writeICCardData.length;
		// 委托单位编号
		System.arraycopy(this.entrustUnitNo, 0, byteArray, index, this.entrustUnitNo.length);
		index += this.entrustUnitNo.length;
		// 终端业务流水号
		System.arraycopy(this.terminalBizSN, 0, byteArray, index, this.terminalBizSN.length);
		index += this.terminalBizSN.length;

		// 返回
		return byteArray;
	}

	@Override
	public String toString() {

		// 转换的字符串
		StringBuffer sb = new StringBuffer();

		// 卡类型
		sb.append("卡类型:");
		for (int i = 0, length = this.icCardType.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardType[i]));
		}
		sb.append("\n");

		// 控制器类型
		sb.append("控制器类型:");
		for (int i = 0, length = this.icCardController.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardController[i]));
		}
		sb.append("\n");

		// 卡号
		sb.append("卡号:");
		for (int i = 0, length = this.icCardNo.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardNo[i]));
		}
		sb.append("\n");

		// 用户号
		sb.append("用户号:");
		for (int i = 0, length = this.userCode.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.userCode[i]));
		}
		sb.append("\n");

		// 用户名
		sb.append("用户名:");
		for (int i = 0, length = this.userName.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.userName[i]));
		}
		sb.append("\n");

		// 购气次数
		sb.append("购气次数:");
		for (int i = 0, length = this.buyTime.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.buyTime[i]));
		}
		sb.append("\n");

		// 购气量
		sb.append("购气量:");
		for (int i = 0, length = this.buyGas.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.buyGas[i]));
		}
		sb.append("\n");

		// 购气金额
		sb.append("购气金额:");
		for (int i = 0, length = this.buyCost.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.buyCost[i]));
		}
		sb.append("\n");

		// 预存支付
		sb.append("预存支付:");
		for (int i = 0, length = this.prestore.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.prestore[i]));
		}
		sb.append("\n");

		// 扣款金额
		sb.append("扣款金额:");
		for (int i = 0, length = this.payment.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.payment[i]));
		}
		sb.append("\n");

		// 气价1
		sb.append("气价1:");
		for (int i = 0, length = this.gasPrice1.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasPrice1[i]));
		}
		sb.append("\n");

		// 气量1
		sb.append("气量1:");
		for (int i = 0, length = this.gasVolume1.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasVolume1[i]));
		}
		sb.append("\n");

		// 气价2
		sb.append("气价2:");
		for (int i = 0, length = this.gasPrice2.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasPrice2[i]));
		}
		sb.append("\n");

		// 气量2
		sb.append("气量2:");
		for (int i = 0, length = this.gasVolume2.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasVolume2[i]));
		}
		sb.append("\n");

		// 气价3
		sb.append("气价3:");
		for (int i = 0, length = this.gasPrice3.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasPrice3[i]));
		}
		sb.append("\n");

		// 气量3
		sb.append("气量3:");
		for (int i = 0, length = this.gasVolume3.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasVolume3[i]));
		}
		sb.append("\n");

		// 气价4
		sb.append("气价4:");
		for (int i = 0, length = this.gasPrice4.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasPrice4[i]));
		}
		sb.append("\n");

		// 气量4
		sb.append("气量4:");
		for (int i = 0, length = this.gasVolume4.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.gasVolume4[i]));
		}
		sb.append("\n");

		// 银行业务流水号
		sb.append("银行业务流水号:");
		for (int i = 0, length = this.bankSD.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.bankSD[i]));
		}
		sb.append("\n");

		// 扣款时间
		sb.append("扣款时间:");
		for (int i = 0, length = this.paymentDate.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.paymentDate[i]));
		}
		sb.append("\n");

		// 写卡结果
		sb.append("写卡结果:");
		for (int i = 0, length = this.writeICCardState.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.writeICCardState[i]));
		}
		sb.append("\n");

		// 失败原因
		sb.append("失败原因:");
		for (int i = 0, length = this.exceptionCause.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.exceptionCause[i]));
		}
		sb.append("\n");

		// 写卡数据长度
		sb.append("写卡数据长度:");
		for (int i = 0, length = this.writeICCardDataLength.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.writeICCardDataLength[i]));
		}
		sb.append("\n");

		// 写卡数据
		sb.append("写卡数据:");
		for (int i = 0, length = this.writeICCardData.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.writeICCardData[i]));
		}
		sb.append("\n");

		// 委托单位编号
		sb.append("委托单位编号:");
		for (int i = 0, length = this.entrustUnitNo.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.entrustUnitNo[i]));
		}
		sb.append("\n");

		// 终端业务流水号
		sb.append("终端业务流水号:");
		for (int i = 0, length = this.terminalBizSN.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.terminalBizSN[i]));
		}
		sb.append("\n");

		// 返回
		return sb.toString();
	}
}