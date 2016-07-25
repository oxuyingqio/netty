package cn.xuyingqi.netty.server.echo.protocol.datagram;

import cn.xuyingqi.net.server.connector.protocol.datagram.Header;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 应答报头
 * 
 * @author XuYQ
 *
 */
public class EchoHeader implements Header {

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
	 * @param messageSD
	 */
	public void setMessageSD(byte[] messageSD) {
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
		this.dataLength = dataLength;
	}

	@Override
	public int getContentLength() {
		return ByteUtils.byteArray2Int(this.dataLength);
	}
}
