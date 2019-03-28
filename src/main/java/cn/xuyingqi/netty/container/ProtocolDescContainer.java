package cn.xuyingqi.netty.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.model.ProtocolDesc;
import cn.xuyingqi.netty.model.ServerXml;
import cn.xuyingqi.netty.model.ServerXml.ProtocolConfig;
import cn.xuyingqi.netty.protocol.Protocol;
import cn.xuyingqi.util.MapFactory;

/**
 * 协议类描述容器
 * 
 * @author XuYQ
 *
 */
public final class ProtocolDescContainer {

	/**
	 * 容器
	 */
	private static ProtocolDescContainer CONTAINER;

	/**
	 * 协议类描述集合
	 */
	private Map<String, ProtocolDesc> descs = MapFactory.newInstance();

	/**
	 * 私有构造方法
	 */
	private ProtocolDescContainer() {

		// 获取协议配置集合
		List<ProtocolConfig> configs = ServerXml.getInstance().getProtocolConfigs();

		// 遍历配置集合
		for (int i = 0, length = configs.size(); i < length; i++) {

			// 配置
			ProtocolConfig config = configs.get(i);
			// 添加协议类描述
			this.addProtocolDesc(config.getName(), new ProtocolDesc(config.getName(), config.getClassName()));
		}
	}

	/**
	 * 获取协议容器实例
	 * 
	 * @return
	 */
	public synchronized static final ProtocolDescContainer getInstance() {

		if (CONTAINER == null) {

			CONTAINER = new ProtocolDescContainer();
		}

		return CONTAINER;
	}

	/**
	 * 添加协议类描述
	 * 
	 * @param key
	 * @param protocolDesc
	 */
	public void addProtocolDesc(String key, ProtocolDesc protocolDesc) {

		this.descs.put(key, protocolDesc);
	}

	/**
	 * 移除协议类描述
	 * 
	 * @param key
	 */
	public void removeProtocolDesc(String key) {

		this.descs.remove(key);
	}

	/**
	 * 获取协议类描述
	 * 
	 * @param key
	 * @return
	 */
	public ProtocolDesc getProtocolDesc(String key) {

		return this.descs.get(key);
	}

	/**
	 * 获取协议
	 * 
	 * @param key
	 * @return
	 */
	public Protocol getProtocol(String key) {

		return this.getProtocolDesc(key).newInstance();
	}
}