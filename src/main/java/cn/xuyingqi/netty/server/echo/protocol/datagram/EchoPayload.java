package cn.xuyingqi.netty.server.echo.protocol.datagram;

import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerPayload;

/**
 * 应答报体
 * 
 * @author XuYQ
 *
 */
public class EchoPayload implements ServerPayload {

	@Override
	public Map<String, Object> convertMap() {
		return null;
	}
}
