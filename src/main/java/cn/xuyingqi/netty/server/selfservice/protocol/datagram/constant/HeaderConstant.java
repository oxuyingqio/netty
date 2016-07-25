package cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant;

/**
 * 报头常量
 * 
 * @author XuYQ
 *
 */
public final class HeaderConstant {

	/**
	 * 主命令字
	 */
	public static final String COMMAND_ID = "COMMAND_ID";
	/**
	 * 终端类型
	 */
	public static final String TERMINAL_TYPE = "TERMINAL_TYPE";
	/**
	 * 终端设备编号
	 */
	public static final String TERMINAL_DEVICE_NO = "TERMINAL_DEVICE_NO";
	/**
	 * 通讯流水号
	 */
	public static final String MESSAGE_SD = "MESSAGE_SD";
	/**
	 * 请求方或者响应方本地时间戳
	 */
	public static final String TIME_STAMP = "TIME_STAMP";
	/**
	 * 响应字
	 */
	public static final String RESPOND_STATE = "RESPOND_STATE";
	/**
	 * 报体长度
	 */
	public static final String DATA_LENGTH = "DATA_LENGTH";
	/**
	 * 是否有下一报
	 */
	public static final String IS_NEXT_PACKET = "IS_NEXT_PACKET";
}