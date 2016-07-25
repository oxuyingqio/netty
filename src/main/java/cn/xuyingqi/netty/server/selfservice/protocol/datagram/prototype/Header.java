package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype;

import cn.xuyingqi.util.exception.ByteArrayLengthErrorException;

/**
 * 报头
 * 
 * @author XuYQ
 *
 */
public class Header {

	// 命令字默认长度
	private static final int COMMAND_ID_LENGTH = 2;
	// 终端类型默认长度
	private static final int TERMINAL_TYPE_LENGTH = 1;
	// 终端设备编号默认长度
	private static final int TERMINAL_DEVICE_NO_LENGTH = 8;
	// 通讯流水号默认长度.暂时不启用,长度设为0
	private static final int MESSAGE_SD_LENGTH = 4;
	// 请求方或者响应方本地时间戳默认长度
	private static final int TIME_STAMP_LENGTH = 7;
	// 响应字默认长度
	private static final int RESPOND_STATE_LENGTH = 2;
	// 报体长度默认长度
	private static final int DATA_LENGTH_LENGTH = 2;
	// 是否有下一报默认长度
	private static final int IS_NEXT_PACKET_LENGTH = 1;

	// 主命令字
	private byte[] commandId = new byte[COMMAND_ID_LENGTH];
	// 终端类型
	private byte[] terminalType = new byte[TERMINAL_TYPE_LENGTH];
	// 终端设备编号
	private byte[] terminalDeviceNo = new byte[TERMINAL_DEVICE_NO_LENGTH];
	// 通讯流水号
	private byte[] messageSD = new byte[MESSAGE_SD_LENGTH];
	// 请求方或者响应方本地时间戳
	private byte[] timeStamp = new byte[TIME_STAMP_LENGTH];
	// 响应字
	private byte[] respondState = new byte[RESPOND_STATE_LENGTH];
	// 报体长度
	private byte[] dataLength = new byte[DATA_LENGTH_LENGTH];
	// 是否有下一报
	private byte[] isNextPacket = new byte[IS_NEXT_PACKET_LENGTH];

	/**
	 * 获取命令字
	 * 
	 * @return
	 */
	public byte[] getCommandId() {

		return commandId;
	}

	/**
	 * 设置命令字
	 * 
	 * @param commandId
	 */
	public void setCommandId(byte[] commandId) {

		// 若长度错误,则抛出异常
		if (commandId.length != this.commandId.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.commandId = commandId;
	}

	/**
	 * 获取终端类型
	 * 
	 * @return
	 */
	public byte[] getTerminalType() {

		return terminalType;
	}

	/**
	 * 设置终端类型
	 * 
	 * @param terminalType
	 */
	public void setTerminalType(byte[] terminalType) {

		// 若长度错误,则抛出异常
		if (terminalType.length != this.terminalType.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.terminalType = terminalType;
	}

	/**
	 * 获取终端设备编号
	 * 
	 * @return
	 */
	public byte[] getTerminalDeviceNo() {

		return terminalDeviceNo;
	}

	/**
	 * 设置终端设备编号
	 * 
	 * @param terminalDeviceNo
	 */
	public void setTerminalDeviceNo(byte[] terminalDeviceNo) {

		// 若长度错误,则抛出异常
		if (terminalDeviceNo.length != this.terminalDeviceNo.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.terminalDeviceNo = terminalDeviceNo;
	}

	/**
	 * 获取通讯流水号
	 * 
	 * @return
	 */
	public byte[] getMessageSD() {

		return messageSD;
	}

	/**
	 * 设置通讯流水号
	 * 
	 * @param messageSN
	 */
	public void setMessageSD(byte[] messageSD) {

		// 若长度错误,则抛出异常
		if (messageSD.length != this.messageSD.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.messageSD = messageSD;
	}

	/**
	 * 获取请求方或者响应方本地时间戳
	 * 
	 * @return
	 */
	public byte[] getTimeStamp() {

		return timeStamp;
	}

	/**
	 * 设置请求方或者响应方本地时间戳
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(byte[] timeStamp) {

		// 若长度错误,则抛出异常
		if (timeStamp.length != this.timeStamp.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.timeStamp = timeStamp;
	}

	/**
	 * 获取响应字
	 * 
	 * @return
	 */
	public byte[] getRespondState() {

		return respondState;
	}

	/**
	 * 设置响应字
	 * 
	 * @param respondState
	 */
	public void setRespondState(byte[] respondState) {

		// 若长度错误,则抛出异常
		if (respondState.length != this.respondState.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.respondState = respondState;
	}

	/**
	 * 获取报体长度
	 * 
	 * @return
	 */
	public byte[] getDataLength() {

		return dataLength;
	}

	/**
	 * 设置报体长度
	 * 
	 * @param dataLength
	 */
	public void setDataLength(byte[] dataLength) {

		// 若长度错误,则抛出异常
		if (dataLength.length != this.dataLength.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.dataLength = dataLength;
	}

	/**
	 * 获取是否有下一报
	 * 
	 * @return
	 */
	public byte[] getIsNextPacket() {

		return isNextPacket;
	}

	/**
	 * 设置是否有下一报
	 * 
	 * @param isNextPacket
	 */
	public void setIsNextPacket(byte[] isNextPacket) {

		// 若长度错误,则抛出异常
		if (isNextPacket.length != this.isNextPacket.length) {
			throw new ByteArrayLengthErrorException();
		}

		this.isNextPacket = isNextPacket;
	}

	/**
	 * 转为字节数组
	 * 
	 * @return
	 */
	public byte[] toByteArray() {

		// 待返回的字节数组
		byte[] byteArray = new byte[this.commandId.length + this.terminalType.length + this.terminalDeviceNo.length
				+ this.messageSD.length + this.timeStamp.length + this.respondState.length + this.dataLength.length
				+ this.isNextPacket.length];

		// 索引值
		int index = 0;

		// 命令字
		System.arraycopy(this.commandId, 0, byteArray, index, this.commandId.length);
		index += this.commandId.length;
		// 终端类型
		System.arraycopy(this.terminalType, 0, byteArray, index, this.terminalType.length);
		index += this.terminalType.length;
		// 终端设备编号
		System.arraycopy(this.terminalDeviceNo, 0, byteArray, index, this.terminalDeviceNo.length);
		index += this.terminalDeviceNo.length;
		// 通讯流水号
		System.arraycopy(this.messageSD, 0, byteArray, index, this.messageSD.length);
		index += this.messageSD.length;
		// 请求方或者响应方本地时间戳
		System.arraycopy(this.timeStamp, 0, byteArray, index, this.timeStamp.length);
		index += this.timeStamp.length;
		// 响应字
		System.arraycopy(this.respondState, 0, byteArray, index, this.respondState.length);
		index += this.respondState.length;
		// 报体长度
		System.arraycopy(this.dataLength, 0, byteArray, index, this.dataLength.length);
		index += this.dataLength.length;
		// 是否有下一报
		System.arraycopy(this.isNextPacket, 0, byteArray, index, this.isNextPacket.length);
		index += this.isNextPacket.length;

		// 返回
		return byteArray;
	}

	@Override
	public String toString() {

		// 转换的字符串
		StringBuffer sb = new StringBuffer();

		// 命令字
		sb.append("命令字:");
		for (int i = 0, length = this.commandId.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.commandId[i]));
		}
		sb.append("\n");

		// 终端类型
		sb.append("终端类型:");
		for (int i = 0, length = this.terminalType.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.terminalType[i]));
		}
		sb.append("\n");

		// 终端设备编号
		sb.append("终端设备编号:");
		for (int i = 0, length = this.terminalDeviceNo.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.terminalDeviceNo[i]));
		}
		sb.append("\n");

		// 通讯流水号
		sb.append("通讯流水号:");
		for (int i = 0, length = this.messageSD.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.messageSD[i]));
		}
		sb.append("\n");

		// 请求方或者响应方本地时间戳
		sb.append("请求方或者响应方本地时间戳:");
		for (int i = 0, length = this.timeStamp.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.timeStamp[i]));
		}
		sb.append("\n");

		// 响应字
		sb.append("响应字:");
		for (int i = 0, length = this.respondState.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.respondState[i]));
		}
		sb.append("\n");

		// 报体长度
		sb.append("报体长度:");
		for (int i = 0, length = this.dataLength.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.dataLength[i]));
		}
		sb.append("\n");

		// 是否有下一报
		sb.append("是否有下一报:");
		for (int i = 0, length = this.isNextPacket.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.isNextPacket[i]));
		}
		sb.append("\n");

		// 返回
		return sb.toString();
	}
}
