package cn.xuyingqi.netty.server.echo.protocol.datagram;

import cn.xuyingqi.net.server.connector.protocol.datagram.Datagram;
import cn.xuyingqi.net.server.connector.protocol.datagram.Header;

/**
 * 应答数据报文
 * 
 * @author XuYQ
 *
 */
public class EchoDatagram implements Datagram {

	/**
	 * 报头
	 */
	private EchoHeader header;

	/**
	 * 报体
	 */
	private byte[] payload;

	@Override
	public Header getHeader() {
		return header;
	}

	/**
	 * 设置报头
	 * 
	 * @param header
	 */
	public void setHeader(EchoHeader header) {
		this.header = header;
	}

	public byte[] getPayload() {
		return payload;
	}

	/**
	 * 设置报体
	 * 
	 * @param payload
	 */
	public void setPayload(byte[] payload) {
		this.payload = payload;
	}
}
