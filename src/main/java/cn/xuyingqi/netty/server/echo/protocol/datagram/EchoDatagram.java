package cn.xuyingqi.netty.server.echo.protocol.datagram;

import cn.xuyingqi.net.server.connector.protocol.datagram.Header;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerPayload;

/**
 * 应答数据报文
 * 
 * @author XuYQ
 *
 */
public class EchoDatagram implements ServerDatagram {

	/**
	 * 报头
	 */
	private EchoHeader header;

	/**
	 * 报体
	 */
	private EchoPayload payload;

	/**
	 * 应答数据报文
	 * 
	 * @param header
	 *            报头
	 * @param payload
	 *            报体
	 */
	public EchoDatagram(EchoHeader header, EchoPayload payload) {

		this.header = header;
		this.payload = payload;
	}

	@Override
	public Header getHeader() {

		return header;
	}

	@Override
	public ServerPayload getPayload() {

		return payload;
	}

	@Override
	public ServerDatagram response() {

		return new EchoDatagram(new EchoHeader(), new EchoPayload());
	}
}
