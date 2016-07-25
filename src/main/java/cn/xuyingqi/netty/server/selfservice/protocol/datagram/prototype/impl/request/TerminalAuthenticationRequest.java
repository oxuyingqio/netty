package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 终端认证-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class TerminalAuthenticationRequest extends Payload {

	/**
	 * 终端随机数默认长度
	 */
	private static final int TERMINAL_RANDOM_LENGTH = 8;
	/**
	 * 终端时间默认长度
	 */
	private static final int TERMINAL_TIME_STAMP_LENGTH = 7;

	/**
	 * 终端随机数
	 */
	private byte[] terminalRandom = new byte[TERMINAL_RANDOM_LENGTH];
	/**
	 * 终端时间
	 */
	private byte[] terminalTimeStamp = new byte[TERMINAL_TIME_STAMP_LENGTH];

	/**
	 * 获取终端随机数
	 * 
	 * @return
	 */
	public byte[] getTerminalRandom() {

		return terminalRandom;
	}

	/**
	 * 获取终端时间
	 * 
	 * @return
	 */
	public byte[] getTerminalTimeStamp() {

		return terminalTimeStamp;
	}

	@Override
	public byte[] toByteArray() {

		// 待返回的字节数组
		byte[] terminalAuthentication = new byte[this.terminalRandom.length + this.terminalTimeStamp.length];

		// 索引值
		int index = 0;

		// 终端随机数
		System.arraycopy(this.terminalRandom, 0, terminalAuthentication, index, this.terminalRandom.length);
		index += this.terminalRandom.length;
		// 终端时间
		System.arraycopy(this.terminalTimeStamp, 0, terminalAuthentication, index, this.terminalTimeStamp.length);
		index += this.terminalTimeStamp.length;

		// 返回
		return terminalAuthentication;
	}

	@Override
	public String toString() {

		// 转换的字符串
		StringBuffer sb = new StringBuffer();

		// 终端随机数
		sb.append("终端随机数:");
		for (int i = 0, length = this.terminalRandom.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.terminalRandom[i]));
		}
		sb.append("\n");

		// 终端时间
		sb.append("终端时间:");
		for (int i = 0, length = this.terminalTimeStamp.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.terminalTimeStamp[i]));
		}
		sb.append("\n");

		// 返回
		return sb.toString();
	}
}