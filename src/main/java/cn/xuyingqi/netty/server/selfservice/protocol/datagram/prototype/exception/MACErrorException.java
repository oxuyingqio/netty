package cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.exception;

/**
 * MAC消息认证码错误
 * 
 * @author XuYQ
 *
 */
public class MACErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MACErrorException() {
		super("MAC消息认证码错误");
	}

	public MACErrorException(String message) {
		super(message);
	}

	public MACErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public MACErrorException(Throwable cause) {
		super(cause);
	}

	protected MACErrorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
