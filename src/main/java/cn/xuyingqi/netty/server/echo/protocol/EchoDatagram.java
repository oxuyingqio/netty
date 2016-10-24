package cn.xuyingqi.netty.server.echo.protocol;

import java.io.UnsupportedEncodingException;

import cn.xuyingqi.net.protocol.Datagram;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 应答数据报文
 * 
 * @author XuYQ
 *
 */
public class EchoDatagram implements Datagram {

	/**
	 * 报体长度
	 */
	private int length;
	/**
	 * 报体消息内容
	 */
	private String msg;

	/**
	 * 应答数据报文
	 */
	public EchoDatagram(int length, String msg) {

		this.length = length;
		this.msg = msg;
	}

	public int getLength() {
		return length;
	}

	public String getMsg() {
		return msg;
	}

	public byte[] toByteArray() {

		byte[] byteArray = new byte[4 + this.length];

		// 添加报头
		System.arraycopy(ByteUtils.int2ByteArray(this.length), 0, byteArray, 0, 4);
		// 添加报体
		try {
			System.arraycopy(this.msg.getBytes("GBK"), 0, byteArray, 4, this.length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 返回
		return byteArray;
	}
}
