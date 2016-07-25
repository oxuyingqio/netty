package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.impl.request;

import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Payload;

/**
 * 终端校时-终端请求报体
 * 
 * @author XuYQ
 *
 */
public class TerminalTimingRequest extends Payload {

	@Override
	public byte[] toByteArray() {

		// 待返回的字节数组
		byte[] terminalTiming = new byte[0];

		// 返回
		return terminalTiming;
	}

	@Override
	public String toString() {

		// 转换的字符串
		StringBuffer sb = new StringBuffer();

		sb.append("\n");

		// 返回
		return sb.toString();
	}
}