package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype;

import java.util.Arrays;

import cn.xuyingqi.security.MAC;

/**
 * 数据报文
 * 
 * @author XuYQ
 *
 */
public class Datagram {

	// MAC消息认证码初始向量
	private static final byte[] MAC_VECTOR = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };

	// MAC消息认证码默认长度
	private static final int MAC_LENGTH = 8;

	// 报头
	private Header header;
	// 报体
	private Payload payload;
	// MAC消息认证码
	private byte[] mac = new byte[MAC_LENGTH];

	/**
	 * 数据报文
	 * 
	 * @param header
	 *            报头
	 * @param payload
	 *            报体
	 */
	public Datagram(Header header, Payload payload) {

		super();

		this.header = header;
		this.payload = payload;
	}

	/**
	 * 获取报头
	 * 
	 * @return
	 */
	public Header getHeader() {

		return header;
	}

	/**
	 * 设置报头
	 * 
	 * @param header
	 */
	public void setHeader(Header header) {

		this.header = header;
	}

	/**
	 * 获取报体
	 * 
	 * @return
	 */
	public Payload getPayload() {

		return payload;
	}

	/**
	 * 设置报体
	 * 
	 * @param payload
	 */
	public void setPayload(Payload payload) {

		this.payload = payload;
	}

	/**
	 * 获取MAC消息认证码
	 * 
	 * @return
	 */
	public byte[] getMAC() {

		return mac;
	}

	/**
	 * 设置MAC消息认证码
	 * 
	 * @param mac
	 */
	public void setMAC(byte[] mac) {

		this.mac = mac;
	}

	/**
	 * 计算MAC
	 * 
	 * @param datagram
	 * @param key
	 * @return
	 */
	public static final byte[] computeMAC(Datagram datagram, byte[] key) {

		// 获取报头字节数组
		byte[] header = datagram.getHeader().toByteArray();
		// 获取报体字节数组
		byte[] payload = datagram.getPayload().toByteArray();

		// 合并报头报体字节数组
		byte[] data = new byte[header.length + payload.length];
		int index = 0;
		System.arraycopy(header, 0, data, index, header.length);
		index += header.length;
		System.arraycopy(payload, 0, data, index, payload.length);
		index += payload.length;

		// 服务器计算MAC消息认证码
		return MAC.mac(data, key, MAC_VECTOR);
	}

	/**
	 * 校验MAC
	 * 
	 * @param datagram
	 * @param key
	 * @return
	 */
	public static final boolean checkMAC(Datagram datagram, byte[] key) {

		// 获取终端MAC消息认证码
		byte[] clientMAC = datagram.getMAC();
		// 计算服务器MAC消息认证码
		byte[] serverMAC = Datagram.computeMAC(datagram, key);

		// 判断服务器产生的消息认证码与终端消息认证码是否一致
		return Arrays.equals(serverMAC, clientMAC);
	}

	@Override
	public String toString() {

		// 转换的字符串
		StringBuffer sb = new StringBuffer();

		// 报头
		sb.append("报头:");
		sb.append("\n");
		sb.append(this.header.toString());
		sb.append("\n");

		// 报体
		sb.append("报体:");
		sb.append("\n");
		sb.append(this.payload.toString());
		sb.append("\n");

		// MAC消息校验码
		sb.append("MAC:");
		sb.append("\n");
		for (int i = 0, length = this.mac.length; i < length; i++) {

			sb.append(" ");
			sb.append(Integer.toHexString(this.mac[i]));
		}
		sb.append("\n");

		// 返回
		return sb.toString();
	}
}
