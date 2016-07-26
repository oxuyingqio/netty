package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.protocol.datagram.ServerHeader;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.HeaderConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Header;
import cn.xuyingqi.netty.server.selfservice.temp.Command;
import cn.xuyingqi.netty.server.selfservice.temp.RespondState;
import cn.xuyingqi.netty.server.selfservice.temp.TerminalType;
import cn.xuyingqi.util.util.ByteUtils;
import cn.xuyingqi.util.util.MapFactory;

/**
 * 自助缴费报头
 * 
 * @author XuYQ
 *
 */
public class SelfServiceHeader implements ServerHeader {

	/**
	 * 时间格式化
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 报头Map集合
	 */
	private Map<String, Object> map = MapFactory.newInstance();

	/**
	 * 自助缴费报头
	 */
	public SelfServiceHeader() {

	}

	/**
	 * 自助缴费报头
	 * 
	 * @param header
	 */
	public SelfServiceHeader(Header header) {

		try {
			// 命令字
			int commandId = ByteUtils.byteArray2Int(header.getCommandId());
			switch (commandId) {
			case 0x0001:
				map.put(HeaderConstant.COMMAND_ID, Command.TERMINAL_AUTHENTICATION);
				break;
			case 0x0002:
				map.put(HeaderConstant.COMMAND_ID, Command.TERMINAL_TIMING);
				break;
			case 0x0101:
				map.put(HeaderConstant.COMMAND_ID, Command.QUERY_ICCARD_INFO);
				break;
			case 0x0102:
				map.put(HeaderConstant.COMMAND_ID, Command.ICCARD_RECHARGE);
				break;
			case 0x0103:
				map.put(HeaderConstant.COMMAND_ID, Command.PAYMENT);
				break;
			case 0x0104:
				map.put(HeaderConstant.COMMAND_ID, Command.REPORT_WRITE_ICCARD);
				break;
			case 0x0105:
				map.put(HeaderConstant.COMMAND_ID, Command.ROLLBACK_PAYMENT);
				break;
			}
			// 终端类型
			short terminalType = ByteUtils.byteArray2Short(header.getTerminalType());
			switch (terminalType) {
			case 0x01:
				map.put(HeaderConstant.TERMINAL_TYPE, TerminalType.A302);
				break;
			case 0x02:
				map.put(HeaderConstant.TERMINAL_TYPE, TerminalType.A303);
				break;
			}
			// 终端设备编号
			map.put(HeaderConstant.TERMINAL_DEVICE_NO, ByteUtils.byteArray2Long(header.getTerminalDeviceNo()));
			// 通讯流水号
			map.put(HeaderConstant.MESSAGE_SD, ByteUtils.byteArray2Long(header.getMessageSD()));
			// 请求方或者响应方本地时间戳
			map.put(HeaderConstant.TIME_STAMP,
					SDF.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(header.getTimeStamp()))));
			// 响应字
			int respondState = ByteUtils.byteArray2Int(header.getRespondState());
			switch (respondState) {
			case 0x0000:
				map.put(HeaderConstant.RESPOND_STATE, RespondState.SUCCESS);
				break;
			default:
				map.put(HeaderConstant.RESPOND_STATE, RespondState.ERROR);
				break;
			}
			// 包体长度
			map.put(HeaderConstant.DATA_LENGTH, ByteUtils.byteArray2Int(header.getDataLength()));
			// 是否有下一报
			short isNextPacket = ByteUtils.byteArray2Short(header.getIsNextPacket());
			map.put(HeaderConstant.IS_NEXT_PACKET, isNextPacket == 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> toMap() {

		return map;
	}

	@Override
	public ServerHeader addHeader(String name, Object value) {

		map.put(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return map.get(name) != null;
	}

	@Override
	public ServerHeader setHeader(String name, Object value) {

		map.put(name, value);

		return this;
	}
}
