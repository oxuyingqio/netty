package cn.xuyingqi.netty.server.echo.protocol.datagram;

import cn.xuyingqi.netty.protocol.datagram.Datagram;
import cn.xuyingqi.netty.protocol.datagram.Header;
import cn.xuyingqi.netty.protocol.datagram.Payload;

/**
 * 应答数据报文
 * 
 * @author XuYQ
 *
 */
public class EchoDatagram implements Datagram {

	private Header header;
	private Payload payload;

	/**
	 * 应答数据报文
	 */
	public EchoDatagram() {

		this.header = new EchoHeader();
		this.payload = new EchoPayload();
	}

	@Override
	public Header getHeader() {

		return this.header;
	}

	@Override
	public Payload getPayload() {

		return this.payload;
	}

	@Override
	public Datagram newResponse() {

		return new EchoDatagram();
	}
}
