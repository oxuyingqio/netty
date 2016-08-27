package cn.xuyingqi.netty.server.echo.protocol.datagram;

import cn.xuyingqi.net.protocol.Datagram;

/**
 * 应答数据报文
 * 
 * @author XuYQ
 *
 */
public class EchoDatagram implements Datagram {

	private EchoHeader header;
	private EchoPayload payload;

	/**
	 * 应答数据报文
	 */
	public EchoDatagram() {

		this.header = new EchoHeader();
		this.payload = new EchoPayload();
	}

	public EchoHeader getHeader() {

		return this.header;
	}

	public EchoPayload getPayload() {

		return this.payload;
	}
}
