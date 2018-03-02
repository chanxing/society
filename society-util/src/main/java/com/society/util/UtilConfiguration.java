package com.society.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfiguration {
	@Value("${mail.host}")
	private String emailHost;
	@Value("${mail.port}")
	private String emailPort;
	@Value("${mail.user}")
	private String emailUser;
	@Value("${mail.password}")
	private String emailPassword;
	@Value("${mail.name}")
	private String emailName;
	@Value("${society.domain}")
	private String domain;
	@Value("${society.imagesDomain}")
	private String imagesDomain;
	@Value("${society.videoDomain}")
	private String videoDomain;
	@Value("${ENV}")
	private String env;

	@Bean
	public MailConfig mailConfig() throws Exception {
		return new MailConfig(emailHost, emailPort, emailUser, emailPassword, emailName);
	}

	@Bean
	public Config config() throws Exception {
		return new Config(domain, imagesDomain, videoDomain, env);
	}
}
