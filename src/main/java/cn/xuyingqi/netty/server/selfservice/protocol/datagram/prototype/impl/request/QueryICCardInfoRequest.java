package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * IC卡信息查询-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class QueryICCardInfoRequest extends Payload {

	/**
	 * 卡类型默认长度
	 */
	private static final int ICCARD_TYPE_LENGTH = 1;
	/**
	 * 分区或文件数量默认长度
	 */
	private static final int ICCARD_PARTITION_LENGTH = 2;
	/**
	 * 数据域长度默认长度
	 */
	private static final int ICCARD_DATA_LENGTH_LENGTH = 2;

	/**
	 * 卡类型
	 */
	private byte[] icCardType = new byte[ICCARD_TYPE_LENGTH];
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

	@Override
	public byte[] toByteArray() {

		// 待返回的字节数组
		byte[] queryICCardInfo = new byte[this.icCardType.length + this.icCardPartition.length
				+ this.icCardDataLength.length + this.icCardData.length];

		// 索引值
		int index = 0;

		// 卡类型
		System.arraycopy(this.icCardType, 0, queryICCardInfo, index, this.icCardType.length);
		index += this.icCardType.length;
		// 分区或文件数量
		System.arraycopy(this.icCardPartition, 0, queryICCardInfo, index, this.icCardPartition.length);
		index += this.icCardPartition.length;
		// 数据域长度
		System.arraycopy(this.icCardDataLength, 0, queryICCardInfo, index, this.icCardDataLength.length);
		index += this.icCardDataLength.length;
		// 数据域
		System.arraycopy(this.icCardData, 0, queryICCardInfo, index, this.icCardData.length);
		index += this.icCardData.length;

		// 返回
		return queryICCardInfo;
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

		// 返回
		return sb.toString();
	}
}
