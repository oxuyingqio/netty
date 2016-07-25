package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 扣款请求-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class PaymentRequest extends Payload {

	/**
	 * 卡类型默认长度
	 */
	private static final int ICCARD_TYPE_LENGTH = 1;
	/**
	 * 控制器类型默认长度
	 */
	private static final int ICCARD_CONTROLLER_LENGTH = 10;
	/**
	 * 分区或文件数量默认长度
	 */
	private static final int ICCARD_PARTITION_LENGTH = 2;
	/**
	 * 数据域长度默认长度
	 */
	private static final int ICCARD_DATA_LENGTH_LENGTH = 2;
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
	 * 应收金额默认长度
	 */
	private static final int RECEIVABLE_LENGTH = 4;
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
	 * 分区或文件数量
	 */
	private byte[] icCardPartition = new byte[ICCARD_PARTITION_LENGTH];
	/**
	 * 数据域长度
	 */
	private byte[] icCardDataLength = new byte[ICCARD_DATA_LENGTH_LENGTH];
	/**
	 * 数据域
	 */
	private byte[] icCardData;
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
	 * 应收金额
	 */
	private byte[] receivable = new byte[RECEIVABLE_LENGTH];
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
	 * 获取分区或文件数量
	 * 
	 * @return
	 */
	public byte[] getIcCardPartition() {

		return icCardPartition;
	}

	/**
	 * 获取数据域长度
	 * 
	 * @return
	 */
	public byte[] getIcCardDataLength() {

		return icCardDataLength;
	}

	/**
	 * 获取数据域
	 * 
	 * @return
	 */
	public byte[] getIcCardData() {

		return icCardData;
	}

	/**
	 * 设置数据域
	 * 
	 * @param icCardData
	 */
	public void setIcCardData(byte[] icCardData) {

		this.icCardData = icCardData;
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
	 * 获取应收金额
	 * 
	 * @return
	 */
	public byte[] getReceivable() {

		return receivable;
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
		byte[] byteArray = new byte[this.icCardType.length + this.icCardController.length + this.icCardPartition.length
				+ this.icCardDataLength.length + this.icCardData.length + this.icCardNo.length + this.userCode.length
				+ this.userName.length + this.buyTime.length + this.buyGas.length + this.receivable.length
				+ this.entrustUnitNo.length + this.terminalBizSN.length];

		// 索引值
		int index = 0;

		// 卡类型
		System.arraycopy(this.icCardType, 0, byteArray, index, this.icCardType.length);
		index += this.icCardType.length;
		// 控制器类型
		System.arraycopy(this.icCardController, 0, byteArray, index, this.icCardController.length);
		index += this.icCardController.length;
		// 分区或文件数量
		System.arraycopy(this.icCardPartition, 0, byteArray, index, this.icCardPartition.length);
		index += this.icCardPartition.length;
		// 数据长度
		System.arraycopy(this.icCardDataLength, 0, byteArray, index, this.icCardDataLength.length);
		index += this.icCardDataLength.length;
		// 卡片数据
		System.arraycopy(this.icCardData, 0, byteArray, index, this.icCardData.length);
		index += this.icCardData.length;
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
		// 应收金额
		System.arraycopy(this.receivable, 0, byteArray, index, this.receivable.length);
		index += this.receivable.length;
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

		// 分区或文件数量
		sb.append("分区或文件数量:");
		for (int i = 0, length = this.icCardPartition.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardPartition[i]));
		}
		sb.append("\n");

		// 数据域长度
		sb.append("数据域长度:");
		for (int i = 0, length = this.icCardDataLength.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardDataLength[i]));
		}
		sb.append("\n");

		// 数据域
		sb.append("数据域:");
		for (int i = 0, length = this.icCardData.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.icCardData[i]));
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

		// 应收金额
		sb.append("应收金额:");
		for (int i = 0, length = this.receivable.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.receivable[i]));
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