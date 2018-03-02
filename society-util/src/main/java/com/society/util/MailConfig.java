package com.society.util;

import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 邮件配置
 * 
 * @author linjintian
 * 
 */
@Component
public class MailConfig implements InitializingBean {

	private static MailConfig CONFIG;

	public static MailConfig getCONFIG() {
		return CONFIG;
	}

	private Properties otherProps;

	public MailConfig(String otherHost, String otherPort, String otherUser, String otherPassword, String otherName) {
		super();

		otherProps = new Properties();
		// otherProps.setProperty("mail.smtps.ssl.enable", "true");
		// otherProps.setProperty("mail.transport.protocol", "smtps");
		otherProps.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		otherProps.setProperty("mail.smtp.socketFactory.port", otherPort);
		otherProps.setProperty("mail.transport.protocol", "smtp");
		otherProps.setProperty("mail.smtp.host", otherHost);
		otherProps.setProperty("mail.smtp.port", otherPort);
		otherProps.setProperty("mail.smtp.auth", "true");
		otherProps.setProperty("mail.smtp.user", otherUser);
		otherProps.setProperty("mail.smtp.pass", otherPassword);
		otherProps.setProperty("mail.smtp.from", otherUser);
		otherProps.setProperty("mail.smtp.personal", otherName);
		otherProps.setProperty("mail.smtp.localhost", "localhost");
	}

	public Properties getOtherConfig() {
		return otherProps;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		CONFIG = this;
	}

}
