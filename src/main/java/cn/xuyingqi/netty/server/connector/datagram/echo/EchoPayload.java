package cn.xuyingqi.netty.server.connector.datagram.echo;

import cn.xuyingqi.netty.server.connector.datagram.Payload;

/**
 * 应答报体
 * 
 * @author XuYQ
 *
 */
public class EchoPayload implements Payload {

	/**
	 * 终端认证-终端请求报体总长度
	 */
	public static final int LENGTH = 15;

	/**
	 * 终端随机数默认长度
	 */
	private static final int TERMINAL_RANDOM_LENGTH = 8;
	/**
	 * 终端时间默认长度
	 */
	private static final int TERMINAL_TIME_STAMP = 7;

	/**
	 * 终端随机数
	 */
	private byte[] terminalRandom = new byte[TERMINAL_RANDOM_LENGTH];
	/**
	 * 终端时间
	 */
	private byte[] terminalTimeStamp = new byte[TERMINAL_TIME_STAMP];

	public byte[] getTerminalRandom() {
		return terminalRandom;
	}

	public void setTerminalRandom(byte[] terminalRandom) {
		this.terminalRandom = terminalRandom;
	}

	public byte[] getTerminalTimeStamp() {
		return terminalTimeStamp;
	}

	public void setTerminalTimeStamp(byte[] terminalTimeStamp) {
		this.terminalTimeStamp = terminalTimeStamp;
	}
}
