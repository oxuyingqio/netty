package cn.xuyingqi.netty.server.echo.protocol.datagram;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;

/**
 * 应答报体
 * 
 * @author XuYQ
 *
 */
public class EchoPayload  {

	/**
	 * 报体
	 */
	private Map<String, Object> payload = MapFactory.newInstance();

	public Map<String, Object> toMap() {

		return this.payload;
	}

	public EchoPayload addPayload(String name, Object value) {

		this.payload.put(name, value);

		return this;
	}

	public boolean containsPayload(String name) {

		return this.payload.containsKey(name);
	}

	public EchoPayload setPayload(String name, Object value) {

		this.payload.put(name, value);

		return this;
	}

	public Object getPayload(String name) {

		return this.payload.get(name);
	}
}
