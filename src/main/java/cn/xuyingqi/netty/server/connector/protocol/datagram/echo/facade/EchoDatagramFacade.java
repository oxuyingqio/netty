package cn.xuyingqi.netty.server.connector.protocol.datagram.echo.facade;

import cn.xuyingqi.net.server.connector.protocol.datagram.Datagram;
import cn.xuyingqi.net.server.connector.protocol.datagram.Header;
import cn.xuyingqi.netty.server.connector.protocol.datagram.echo.EchoDatagram;
import cn.xuyingqi.netty.server.connector.protocol.datagram.echo.EchoHeader;

/**
 * 应答数据报文外观类
 * 
 * @author XuYQ
 *
 */
public class EchoDatagramFacade implements Datagram {

	/**
	 * 应答数据报文
	 */
	private EchoDatagram datagram;

	/**
	 * 应答数据报文外观类
	 * 
	 * @param datagram
	 *            应答数据报文
	 */
	public EchoDatagramFacade(EchoDatagram datagram) {

		this.datagram = datagram;
	}

	@Override
	public Header getHeader() {
		return new EchoHeaderFacade((EchoHeader) this.datagram.getHeader());
	}

	@Override
	public byte[] getPayload() {
		return this.datagram.getPayload();
	}
}
