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

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("会话: ");
		sb.append(this.getId());

		return sb.toString();
	}
}
