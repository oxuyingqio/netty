package cn.xuyingqi.netty.server.selfservice.protocol.datagram;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import cn.xuyingqi.netty.server.connector.protocol.datagram.ServerHeader;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.constant.HeaderConstant;
import cn.xuyingqi.netty.server.selfservice.protocol.datagram.prototype.Header;
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
	 * 默认编码集
	 */
	private static final String CHARSET = "GBK";

	/**
	 * 时间格式化
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 报头Map集合
	 */
	private Map<String, Object> headerMap = MapFactory.newInstance();

	/**
	 * 自助缴费报头
	 * 
	 * @param header
	 */
	public SelfServiceHeader(Header header) {

		headerMap.put(HeaderConstant.COMMAND_ID, ByteUtils.byteArray2Int(header.getCommandId()));
		headerMap.put(HeaderConstant.TERMINAL_TYPE, ByteUtils.byteArray2Short(header.getTerminalType()));
		headerMap.put(HeaderConstant.TERMINAL_DEVICE_NO, ByteUtils.byteArray2Long(header.getTerminalDeviceNo()));
		headerMap.put(HeaderConstant.MESSAGE_SD, ByteUtils.byteArray2Long(header.getMessageSD()));
		try {
			headerMap.put(HeaderConstant.TIME_STAMP,
					SDF.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(header.getTimeStamp()))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		headerMap.put(HeaderConstant.RESPOND_STATE, ByteUtils.byteArray2Int(header.getRespondState()));
		headerMap.put(HeaderConstant.DATA_LENGTH, ByteUtils.byteArray2Int(header.getDataLength()));
		headerMap.put(HeaderConstant.IS_NEXT_PACKET, ByteUtils.byteArray2Short(header.getIsNextPacket()));
	}

	@Override
	public Map<String, Object> convertMap() {

		return headerMap;
	}

	@Override
	public ServerHeader addHeader(String name, Object value) {

		headerMap.put(name, value);

		return this;
	}

	@Override
	public boolean containsHeader(String name) {

		return headerMap.get(name) != null;
	}

	@Override
	public ServerHeader setHeader(String name, Object value) {

		headerMap.put(name, value);

		return this;
	}

	@Override
	public String getCharacterEncoding() {

		return CHARSET;
	}

	@Override
	public ServerHeader setCharacterEncoding(String charset) {

		// 什么都不做

		return this;
	}

	@Override
	public String getContentType() {

		return headerMap.get(HeaderConstant.COMMAND_ID).toString();
	}

	@Override
	public ServerHeader setContentType(String type) {

		headerMap.put(HeaderConstant.COMMAND_ID, type);

		return this;
	}

	@Override
	public int getContentLength() {

		return Integer.valueOf(headerMap.get(HeaderConstant.DATA_LENGTH).toString());
	}

	@Override
	public ServerHeader setContentLength(int len) {

		headerMap.put(HeaderConstant.DATA_LENGTH, len);

		return this;
	}

	@Override
	public ServerHeader setStatus(int status) {

		headerMap.put(HeaderConstant.RESPOND_STATE, status);

		return this;
	}
}
