package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 冲正请求-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class RollbackPaymentRequest extends Payload {

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
	 * 冲正金额默认长度
	 */
	private static final int ROLLBACK_MONEY_LENGTH = 4;
	/**
	 * 银行业务流水号默认长度
	 */
	private static final int BANK_SD_LENGTH = 10;
	/**
	 * 冲正原因默认长度
	 */
	private static final int ROLLBACL_CAUSE_LENGTH = 32;
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
	 * 冲正金额
	 */
	private byte[] rollbackMoney = new byte[ROLLBACK_MONEY_LENGTH];
	/**
	 * 银行业务流水号
	 */
	private byte[] bankSD = new byte[BANK_SD_LENGTH];
	/**
	 * 冲正原因
	 */
	private byte[] rollbackCause = new byte[ROLLBACL_CAUSE_LENGTH];
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
	 * 获取冲正金额
	 * 
	 * @return
	 */
	public byte[] getRollbackMoney() {

		return rollbackMoney;
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
	 * 获取冲正原因
	 * 
	 * @return
	 */
	public byte[] getRollbackCause() {

		return rollbackCause;
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
				+ this.rollbackMoney.length + this.bankSD.length + this.rollbackCause.length + this.entrustUnitNo.length
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
		// 冲正金额
		System.arraycopy(this.rollbackMoney, 0, byteArray, index, this.rollbackMoney.length);
		index += this.rollbackMoney.length;
		// 银行业务流水号
		System.arraycopy(this.bankSD, 0, byteArray, index, this.bankSD.length);
		index += this.bankSD.length;
		// 冲正原因
		System.arraycopy(this.rollbackCause, 0, byteArray, index, this.rollbackCause.length);
		index += this.rollbackCause.length;
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

		// 冲正金额
		sb.append("冲正金额:");
		for (int i = 0, length = this.rollbackMoney.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.rollbackMoney[i]));
		}
		sb.append("\n");

		// 银行业务流水号
		sb.append("银行业务流水号:");
		for (int i = 0, length = this.bankSD.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.bankSD[i]));
		}
		sb.append("\n");

		// 冲正原因
		sb.append("冲正原因:");
		for (int i = 0, length = this.rollbackCause.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.rollbackCause[i]));
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
