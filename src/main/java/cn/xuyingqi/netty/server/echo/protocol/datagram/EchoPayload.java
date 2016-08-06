package cn.xuyingqi.netty.server.echo.protocol.datagram;

import java.util.Map;

import cn.xuyingqi.netty.protocol.datagram.Payload;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 应答报体
 * 
 * @author XuYQ
 *
 */
public class EchoPayload implements Payload {

	/**
	 * 报体
	 */
	private Map<String, Object> payload = MapFactory.newInstance();

	@Override
	public Map<String, Object> toMap() {

		return this.payload;
	}

	@Override
	public Payload addPayload(String name, Object value) {

		this.payload.put(name, value);

		return this;
	}

	@Override
	public boolean containsPayload(String name) {

		return this.payload.containsKey(name);
	}

	@Override
	public Payload setPayload(String name, Object value) {

		this.payload.put(name, value);

		return this;
	}

	@Override
	public Object getPayload(String name) {

		return this.payload.get(name);
	}
}
