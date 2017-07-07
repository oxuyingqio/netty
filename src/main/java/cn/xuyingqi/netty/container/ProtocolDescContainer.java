package cn.xuyingqi.netty.container;

import java.util.List;
import java.util.Map;

import cn.xuyingqi.netty.model.ProtocolDesc;
import cn.xuyingqi.netty.model.ServerXml;
import cn.xuyingqi.netty.model.ServerXml.ProtocolConfig;
import cn.xuyingqi.netty.protocol.Protocol;
import cn.xuyingqi.util.util.MapFactory;

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
	private static ProtocolDescContainer container;

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
			this.descs.put(config.getName(), new ProtocolDesc(config.getName(), config.getClassName()));
		}
	}

	/**
	 * 获取协议容器实例
	 * 
	 * @return
	 */
	public static final synchronized ProtocolDescContainer getInstance() {

		if (container == null) {

			container = new ProtocolDescContainer();
		}

		return container;
	}

	/**
	 * 获取指定名称的协议
	 * 
	 * @param name
	 * @return
	 */
	public Protocol getProtocol(String name) {

		return descs.get(name).getInstance();
	}
}