package cn.xuyingqi.netty.server.selfservice.temp;

/**
 * 命令
 * 
 * @author XuYQ
 *
 */
public enum Command {

	/**
	 * 终端认证
	 */
	TERMINAL_AUTHENTICATION,
	/**
	 * 终端校时
	 */
	TERMINAL_TIMING,
	/**
	 * IC卡信息查询
	 */
	QUERY_ICCARD_INFO,
	/**
	 * 充值计费请求
	 */
	ICCARD_RECHARGE,
	/**
	 * 扣款请求
	 */
	PAYMENT,
	/**
	 * 上报写卡信息
	 */
	REPORT_WRITE_ICCARD,
	/**
	 * 冲正请求
	 */
	ROLLBACK_PAYMENT
}
