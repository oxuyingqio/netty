package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import cn.xuyingqi.net.server.protocol.datagram.Header;
import cn.xuyingqi.netty.server.protocol.datagram.ServerDatagram;
import cn.xuyingqi.netty.server.protocol.datagram.ServerPayload;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Datagram;

/**
 * 自助缴费数据报文
 * 
 * @author XuYQ
 *
 */
public class SelfServiceDatagram implements ServerDatagram {

	/**
	 * 报头
	 */
	private SelfServiceHeader header;

	/**
	 * 报体
	 */
	private SelfServicePayload payload;

	/**
	 * 自助缴费数据报文
	 * 
	 * @param datagram
	 */
	public SelfServiceDatagram(Datagram datagram) {

		this.header = new SelfServiceHeader(datagram.getHeader());
		this.payload = new SelfServicePayload(datagram.getPayload());
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
	public ServerDatagram newResponse() {

		return null;
	}
}
