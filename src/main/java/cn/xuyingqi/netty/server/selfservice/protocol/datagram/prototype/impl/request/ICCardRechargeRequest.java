package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 充值计费请求-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class ICCardRechargeRequest extends Payload {

	/**
	 * 卡类型默认长度
	 */
	private static final int ICCARD_TYPE_LENGTH = 1;
	/**
	 * 分区或文件数量默认长度
	 */
	private static final int ICCARD_PARTITION_LENGTH = 2;
	/**
	 * 数据长度默认长度
	 */
	private static final int ICCARD_DATA_LENGTH_LENGTH = 2;
	/**
	 * 购气量默认长度
	 */
	private static final int BUY_GAS_LENGTH = 4;
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
	 * 分区或文件数量
	 */
	private byte[] icCardPartition = new byte[ICCARD_PARTITION_LENGTH];
	/**
	 * 数据长度
	 */
	private byte[] icCardDataLength = new byte[ICCARD_DATA_LENGTH_LENGTH];
	/**
	 * 卡片数据
	 */
	private byte[] icCardData;
	/**
	 * 购气量
	 */
	private byte[] buyGas = new byte[BUY_GAS_LENGTH];
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
	 * 获取购气量
	 * 
	 * @return
	 */
	public byte[] getBuyGas() {

		return buyGas;
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
		byte[] byteArray = new byte[this.icCardType.length + this.icCardPartition.length + this.icCardDataLength.length
				+ this.icCardData.length + this.buyGas.length + this.entrustUnitNo.length + this.terminalBizSN.length];

		// 索引值
		int index = 0;

		// 卡类型
		System.arraycopy(this.icCardType, 0, byteArray, index, this.icCardType.length);
		index += this.icCardType.length;
		// 分区或文件数量
		System.arraycopy(this.icCardPartition, 0, byteArray, index, this.icCardPartition.length);
		index += this.icCardPartition.length;
		// 数据长度
		System.arraycopy(this.icCardDataLength, 0, byteArray, index, this.icCardDataLength.length);
		index += this.icCardDataLength.length;
		// 卡片数据
		System.arraycopy(this.icCardData, 0, byteArray, index, this.icCardData.length);
		index += this.icCardData.length;
		// 购气量
		System.arraycopy(this.buyGas, 0, byteArray, index, this.buyGas.length);
		index += this.buyGas.length;
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

		// 购气量
		sb.append("购气量:");
		for (int i = 0, length = this.buyGas.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.buyGas[i]));
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