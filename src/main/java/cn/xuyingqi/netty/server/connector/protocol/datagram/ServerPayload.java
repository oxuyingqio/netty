package cn.xuyingqi.netty.server.connector.protocol.datagram;

import java.util.Map;

/**
 * 报体
 * 
 * @author XuYQ
 *
 */
public interface ServerPayload {

	/**
	 * 转换为Map集合
	 * 
	 * @return
	 */
	public Map<String, Object> convertMap();
}
