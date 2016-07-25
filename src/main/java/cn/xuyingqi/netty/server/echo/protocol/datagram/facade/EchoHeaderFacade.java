package cn.xuyingqi.netty.server.echo.protocol.datagram.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.xuyingqi.net.server.connector.protocol.datagram.Header;
import cn.xuyingqi.netty.server.echo.protocol.datagram.EchoHeader;
import cn.xuyingqi.util.util.ByteUtils;

/**
 * 应答报头外观类
 * 
 * @author XuYQ
 *
 */
public class EchoHeaderFacade implements Header {

	/**
	 * 应答数据报头
	 */
	private EchoHeader header;

	/**
	 * 应答报头外观类
	 * 
	 * @param header
	 */
	public EchoHeaderFacade(EchoHeader header) {

		this.header = header;
	}

	/**
	 * 获取通讯流水号
	 * 
	 * @return
	 */
	public long getMessageSD() {
		return ByteUtils.byteArray2Long(this.header.getMessageSD());
	}

	/**
	 * 设置通讯流水号
	 * 
	 * @param messageSD
	 */
	public void setMessageSD(long messageSD) {
		this.header.setMessageSD(ByteUtils.int2ByteArray((int) messageSD));
	}

	/**
	 * 获取请求方或者响应方本地时间戳
	 * 
	 * @return
	 */
	public Date getTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			return sdf.parse(ByteUtils.byteArray2String(ByteUtils.bcd2ByteArray(this.header.getTimeStamp())));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 设置请求方或者响应方本地时间戳
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		this.header.setTimeStamp(ByteUtils.byteArray2BCD(ByteUtils.string2ByteArray(sdf.format(timeStamp))));
	}

	/**
	 * 获取响应字
	 * 
	 * @return
	 */
	public int getRespondState() {
		return ByteUtils.byteArray2Int(this.header.getRespondState());
	}

	/**
	 * 设置响应字
	 * 
	 * @param respondState
	 */
	public void setRespondState(int respondState) {
		this.header.setRespondState(ByteUtils.short2ByteArray((short) respondState));
	}

	/**
	 * 获取报体长度
	 * 
	 * @return
	 */
	public int getDataLength() {
		return ByteUtils.byteArray2Int(this.header.getDataLength());
	}

	/**
	 * 设置报体长度
	 * 
	 * @param dataLength
	 */
	public void setDataLength(int dataLength) {
		this.header.setDataLength(ByteUtils.short2ByteArray((short) dataLength));
	}

	@Override
	public int getContentLength() {
		return this.getDataLength();
	}
}
