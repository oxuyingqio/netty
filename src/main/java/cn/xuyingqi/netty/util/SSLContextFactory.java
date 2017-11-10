package cn.xuyingqi.netty.util;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import cn.xuyingqi.netty.model.ServerXml.ServiceConfig.ConnectorConfig.SSLConfig;

/**
 * SSL上下文工厂
 * 
 * @author XuYQ
 *
 */
public final class SSLContextFactory {

	/**
	 * SSL上下文
	 */
	private static SSLContext SSL_CONTEXT;

	/**
	 * 私有构造方法
	 */
	private SSLContextFactory() {

	}

	/**
	 * 获取SSL上下文实例
	 * 
	 * @param sslConfig
	 *            SSL配置
	 * @return
	 */
	public static SSLContext getInstance(cn.xuyingqi.net.connector.SSLConfig sslConfig) {

		// 判断是否为空
		if (SSL_CONTEXT == null) {

			// SSL配置
			SSLConfig config = (SSLConfig) sslConfig;

			try {

				// 密钥管理工厂
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(config.getPrivateKey().getAlgorithm());
				// 密钥库
				KeyStore ks = KeyStore.getInstance(config.getPrivateKey().getType());
				// 设置文件路径,及密码
				ks.load(new FileInputStream(config.getPrivateKey().getPath()),
						config.getPrivateKey().getPassword().toCharArray());
				// 初始化密钥管理工厂
				kmf.init(ks, config.getPrivateKey().getPassword().toCharArray());

				// 受信证书管理工厂
				TrustManagerFactory tmf = null;
				// 判断是否设置受信证书
				if (config.getTrustCertificate() != null) {

					// 受信证书管理工厂
					tmf = TrustManagerFactory.getInstance(config.getTrustCertificate().getAlgorithm());
					// 密钥库
					KeyStore tks = KeyStore.getInstance(config.getTrustCertificate().getType());
					// 设置文件路径,及密码
					tks.load(new FileInputStream(config.getTrustCertificate().getPath()),
							config.getTrustCertificate().getPassword().toCharArray());
					// 初始化受信证书管理工厂
					tmf.init(tks);
				}

				// 获取SSL上下文实例
				SSL_CONTEXT = SSLContext.getInstance(config.getProtocol());
				// 初始化SSL上下文实例
				SSL_CONTEXT.init(kmf.getKeyManagers(), tmf == null ? null : tmf.getTrustManagers(), null);
			} catch (Exception e) {

				throw new Error("初始化SSL上下文失败", e);
			}
		}

		return SSL_CONTEXT;
	}
}