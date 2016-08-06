package cn.xuyingqi.netty.server.echo.protocol.datagram;

import java.util.Map;

import cn.xuyingqi.netty.protocol.datagram.Header;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 应答报头
 * 
 * @author XuYQ
 *
 */
public class EchoHeader implements Header {

	/**
	 * 数据
	 */
	private Map<String, Object> header = MapFactory.newInstance();

	@Override
	public Map<String, Object> toMap() {

		return this.header;
	}

	@Override
	public Header addHeader(String name, Object value) {

		this.header.put(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return this.header.containsKey(name);
	}

	@Override
	public Header setHeader(String name, Object value) {

		this.header.put(name, value);

		return this;
	}

	@Override
	public Object getHeader(String name) {

		return this.header.get(name);
	}
}
