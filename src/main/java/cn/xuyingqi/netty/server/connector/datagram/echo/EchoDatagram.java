package cn.xuyingqi.netty.server.connector.datagram.echo;

import cn.xuyingqi.netty.server.connector.datagram.Datagram;
import cn.xuyingqi.netty.server.connector.datagram.Header;
import cn.xuyingqi.netty.server.connector.datagram.Payload;

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
	private EchoPayload payload;

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

	@Override
	public Payload getPayload() {
		return payload;
	}

	/**
	 * 设置报体
	 * 
	 * @param payload
	 */
	public void setPayload(EchoPayload payload) {
		this.payload = payload;
	}
}
