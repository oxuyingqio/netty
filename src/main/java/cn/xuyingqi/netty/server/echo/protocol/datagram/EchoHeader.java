package cn.xuyingqi.netty.server.echo.protocol.datagram;

import java.util.Map;

import cn.xuyingqi.util.util.MapFactory;

/**
 * 应答报头
 * 
 * @author XuYQ
 *
 */
public class EchoHeader {

	/**
	 * 数据
	 */
	private Map<String, Object> header = MapFactory.newInstance();

	public Map<String, Object> toMap() {

		return this.header;
	}

	public EchoHeader addHeader(String name, Object value) {

		this.header.put(name, value);

		return this;
	}

	public boolean containsHeader(String name) {

		return this.header.containsKey(name);
	}

	public EchoHeader setHeader(String name, Object value) {

		this.header.put(name, value);

		return this;
	}

	public Object getHeader(String name) {

		return this.header.get(name);
	}
}
