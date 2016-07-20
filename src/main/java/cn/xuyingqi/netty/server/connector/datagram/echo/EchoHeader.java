package cn.xuyingqi.netty.server.connector.datagram.echo;

import cn.xuyingqi.netty.server.connector.datagram.Header;

/**
 * 应答报头
 * 
 * @author XuYQ
 *
 */
public class EchoHeader implements Header {

	/**
	 * 报头总长度
	 */
	public static final int LENGTH = 27;

	/**
	 * 命令字默认长度
	 */
	private static final int COMMAND_ID_LENGTH = 2;
	/**
	 * 终端类型默认长度
	 */
	private static final int TERMINAL_TYPE_LENGTH = 1;
	/**
	 * 终端设备编号默认长度
	 */
	private static final int TERMINAL_DEVICE_NO_LENGTH = 8;
	/**
	 * 通讯流水号默认长度
	 */
	private static final int MESSAGE_SD_LENGTH = 4;
	/**
	 * 请求方或者响应方本地时间戳默认长度
	 */
	private static final int TIME_STAMP_LENGTH = 7;
	/**
	 * 响应字默认长度
	 */
	private static final int RESPOND_STATE_LENGTH = 2;
	/**
	 * 报体长度默认长度
	 */
	private static final int DATA_LENGTH_LENGTH = 2;
	/**
	 * 是否有下一报默认长度
	 */
	private static final int IS_NEXT_PACKET_LENGTH = 1;

	/**
	 * 主命令字
	 */
	private byte[] commandId = new byte[COMMAND_ID_LENGTH];
	/**
	 * 终端类型
	 */
	private byte[] terminalType = new byte[TERMINAL_TYPE_LENGTH];
	/**
	 * 终端设备编号
	 */
	private byte[] terminalDeviceNo = new byte[TERMINAL_DEVICE_NO_LENGTH];
	/**
	 * 通讯流水号
	 */
	private byte[] messageSD = new byte[MESSAGE_SD_LENGTH];
	/**
	 * 请求方或者响应方本地时间戳
	 */
	private byte[] timeStamp = new byte[TIME_STAMP_LENGTH];
	/**
	 * 响应字
	 */
	private byte[] respondState = new byte[RESPOND_STATE_LENGTH];
	/**
	 * 报体长度
	 */
	private byte[] dataLength = new byte[DATA_LENGTH_LENGTH];
	/**
	 * 是否有下一报
	 */
	private byte[] isNextPacket = new byte[IS_NEXT_PACKET_LENGTH];

	public byte[] getCommandId() {
		return commandId;
	}

	public void setCommandId(byte[] commandId) {
		this.commandId = commandId;
	}

	public byte[] getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(byte[] terminalType) {
		this.terminalType = terminalType;
	}

	public byte[] getTerminalDeviceNo() {
		return terminalDeviceNo;
	}

	public void setTerminalDeviceNo(byte[] terminalDeviceNo) {
		this.terminalDeviceNo = terminalDeviceNo;
	}

	public byte[] getMessageSD() {
		return messageSD;
	}

	public void setMessageSD(byte[] messageSD) {
		this.messageSD = messageSD;
	}

	public byte[] getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(byte[] timeStamp) {
		this.timeStamp = timeStamp;
	}

	public byte[] getRespondState() {
		return respondState;
	}

	public void setRespondState(byte[] respondState) {
		this.respondState = respondState;
	}

	public byte[] getDataLength() {
		return dataLength;
	}

	public void setDataLength(byte[] dataLength) {
		this.dataLength = dataLength;
	}

	public byte[] getIsNextPacket() {
		return isNextPacket;
	}

	public void setIsNextPacket(byte[] isNextPacket) {
		this.isNextPacket = isNextPacket;
	}
}
