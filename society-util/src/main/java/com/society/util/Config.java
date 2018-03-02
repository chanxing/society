package com.society.util;

import org.springframework.beans.factory.InitializingBean;

/**
 * 系统配置
 * 
 * @author linjintian
 * 
 */
public class Config implements InitializingBean {

	private static Config CONFIG;

	public static Config getCONFIG() {
		return CONFIG;
	}

	private final String domain;
	// private final String cpDomain;
	// private final String rtbDomain;
	// private final String cbDomain;
	private final String imagesDomain;
	private final String videoDomain;
	// private final String taskOn;
	// private final String webMachine;
	// private final String qpsTaskOn;
	private final String env;
	// private final String feedbackMail;
	// private final String dataPackageDomain;// 上传数据包域名

	public Config(String domain, String imagesDomain, String videoDomain, String env) {
		super();
		this.domain = domain;
		this.imagesDomain = imagesDomain;
		this.videoDomain = videoDomain;
		this.env = env;
	}

	public static String getDomain() {
		return CONFIG.domain;
	}

	public static String getImagesDomain() {
		return CONFIG.imagesDomain;
	}

	public static String getVideoDomain() {
		return CONFIG.videoDomain;
	}

	public static String getEnv() {
		return CONFIG.env;
	}

	@Override
	public String toString() {
		return "Config [domain=" + domain + ", imagesDomain=" + imagesDomain + ", videoDomain=" + videoDomain + ", env=" + env + "]";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CONFIG = this;
		System.out.println(this);
	}

}
