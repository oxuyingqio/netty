package cn.xuyingqi.netty.server.connector;

import java.util.UUID;

/**
 * 会话
 * 
 * @author XuYQ
 *
 */
public final class Session {

	/**
	 * ID
	 */
	private String id;

	public Session() {

		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
}
