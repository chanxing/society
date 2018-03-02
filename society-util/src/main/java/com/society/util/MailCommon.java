package com.society.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

public class MailCommon {

	static final String Charset = "UTF-8";
	static final String Encoding = "B";
	static final String HtmlContentType = "text/html;charset=GB2312";

	/**
	 * 发送简单的Text邮件.
	 * 
	 * @param email
	 *            收件人
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendSimpleEmail(Session session, String email, String subject, String content) throws UnsupportedEncodingException, MessagingException {
		sendSimpleEmail(session, Arrays.asList(email), null, subject, content);
	}

	/**
	 * 发送简单的Text邮件.
	 * 
	 * @param session
	 *            发送邮件的会话
	 * @param to
	 *            收件人列表
	 * @param cc
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendSimpleEmail(Session session, List<String> to, List<String> cc, String subject, String content) throws UnsupportedEncodingException, MessagingException {

		if (to == null || to.size() == 0) {
			return;
		}

		Transport transport = null;
		try {
			transport = session.getTransport();
			Properties props = session.getProperties();
			String protocol = (String) props.get("mail.transport.protocol");
			String host = (String) props.get("mail." + protocol + ".host");
			String username = (String) props.get("mail." + protocol + ".user");
			String password = (String) props.get("mail." + protocol + ".pass");
			String from = session.getProperty("mail." + protocol + ".from");
			String personal = session.getProperty("mail." + protocol + ".personal");
			String p = (String) props.get("mail." + protocol + ".port");
			int port = 25;
			if (StringUtils.isNotBlank(p)) {
				port = Integer.parseInt(p);
			}
			transport.connect(host, port, username, password);

			MimeMessage mimeMsg = new MimeMessage(session);
			mimeMsg.setFrom(new InternetAddress(from, personal));
			mimeMsg.setSubject(MimeUtility.encodeText(subject, Charset, Encoding));
			mimeMsg.setSentDate(new java.util.Date());
			mimeMsg.setText(content, Charset);

			Set<Address> allAddr = new HashSet<Address>();
			Address[] toAddr = new Address[to.size()];
			for (int i = 0; i < toAddr.length; i++) {
				toAddr[i] = new InternetAddress(to.get(i));
			}
			mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
			Collections.addAll(allAddr, toAddr);

			Address[] ccAddr = null;
			if (cc != null && cc.size() > 0) {
				ccAddr = new Address[cc.size()];
				for (int i = 0; i < ccAddr.length; i++) {
					ccAddr[i] = new InternetAddress(cc.get(i));
				}
				mimeMsg.setRecipients(Message.RecipientType.CC, ccAddr);

				Collections.addAll(allAddr, ccAddr);
			}
			transport.sendMessage(mimeMsg, allAddr.toArray(new Address[0]));
		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException e) {
					throw e;
				}
			}
		}
	}

	/**
	 * 发送html邮件
	 * 
	 * @param to
	 *            收件人列表
	 * @param cc
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmail(Session session, List<String> to, List<String> cc, String subject, String html) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();
		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		sendMultiEmail(session, to, cc, subject, multi);

	}

	public static void sendHtmlEmail(Session session, String to, String subject, String html) throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();
		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		sendMultiEmail(session, Arrays.asList(to), null, subject, multi);

	}

	/**
	 * 发送带附件的Html邮件
	 * 
	 * @param recipient
	 *            收件人
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件html内容
	 * @param attachData
	 *            附件内容
	 * @param attachName
	 *            附件名称
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendHtmlEmailWithAttach(Session session, String recipient, String subject, String html, List<EmailAttach> attachs) throws UnsupportedEncodingException, MessagingException {
		sendHtmlEmailWithAttach(session, Arrays.asList(recipient), null, subject, html, attachs);
	}

	/**
	 * 发送带附件的html邮件
	 * 
	 * @param to
	 *            发件人列表
	 * @param cc
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件html内容
	 * @param attachData
	 *            附件数据
	 * @param attachName
	 *            附件名称
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithAttach(Session session, List<String> to, List<String> cc, String subject, String html, List<EmailAttach> attachs)
			throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart();

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);
		for (EmailAttach attach : attachs) {
			MimeBodyPart attachPart = new MimeBodyPart();
			attachPart.setDataHandler(new DataHandler(attach.getAttachData()));
			attachPart.setFileName(MimeUtility.encodeText(attach.getAttachName(), Charset, Encoding));
			multi.addBodyPart(attachPart);
		}

		sendMultiEmail(session, to, cc, subject, multi);

	}

	/**
	 * 发送带图片的html邮件. 注意, 如果收件人列表与抄送人列表有交叉,某些邮件服务器将报告错误.
	 * 
	 * @param to
	 *            收件人列表
	 * @param cc
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件html内容
	 * @param imgData
	 *            图片数据
	 * @param cid
	 *            图片引用id,在html中通过 <img src="cid:IMG1" ...>形式引用图片
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithImage(Session session, String recipient, String subject, String html, DataSource imgData, String cid) throws UnsupportedEncodingException, MessagingException {
		MimeMultipart multi = new MimeMultipart("related");

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(imgData));
		body.setContentID(cid);
		multi.addBodyPart(body);

		sendMultiEmail(session, Arrays.asList(recipient), null, subject, multi);
	}

	/**
	 * 发送带图片的html邮件. 注意, 如果收件人列表与抄送人列表有交叉,某些邮件服务器将报告错误.
	 * 
	 * @param to
	 *            收件人列表
	 * @param cc
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param html
	 *            邮件html内容
	 * @param imgData
	 *            图片数据
	 * @param cid
	 *            图片引用id,在html中通过 <img src="cid:IMG1" ...>形式引用图片
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void sendHtmlEmailWithImage(Session session, List<String> to, List<String> cc, String subject, String html, DataSource imgData, String cid)
			throws MessagingException, UnsupportedEncodingException {

		MimeMultipart multi = new MimeMultipart("related");

		MimeBodyPart body = new MimeBodyPart();
		body.setContent(html, HtmlContentType);
		multi.addBodyPart(body);

		body = new MimeBodyPart();
		body.setDataHandler(new DataHandler(imgData));
		body.setContentID(cid);
		multi.addBodyPart(body);

		sendMultiEmail(session, to, cc, subject, multi);

	}

	public static void sendMultiEmail(Session session, List<String> to, List<String> cc, String subject, Multipart content) throws UnsupportedEncodingException, MessagingException {
		if (to == null || to.size() == 0) {
			return;
		}

		Transport transport = null;
		try {
			transport = session.getTransport();
			Properties props = session.getProperties();
			String protocol = (String) props.get("mail.transport.protocol");
			String host = (String) props.get("mail." + protocol + ".host");
			String username = (String) props.get("mail." + protocol + ".user");
			String password = (String) props.get("mail." + protocol + ".pass");
			String from = session.getProperty("mail." + protocol + ".from");
			String personal = session.getProperty("mail." + protocol + ".personal");
			String p = (String) props.get("mail." + protocol + ".port");
			int port = 25;
			if (StringUtils.isNotBlank(p)) {
				port = Integer.parseInt(p);
			}
			transport.connect(host, port, username, password);

			MimeMessage mimeMsg = new MimeMessage(session);
			mimeMsg.setSentDate(new Date());
			mimeMsg.setFrom(new InternetAddress(from, personal));
			mimeMsg.setSubject(MimeUtility.encodeText(subject, Charset, Encoding));
			mimeMsg.setSentDate(new Date());
			mimeMsg.setContent(content);
			mimeMsg.saveChanges();

			Set<Address> allAddr = new HashSet<Address>();
			Address[] toAddr = new Address[to.size()];
			for (int i = 0; i < toAddr.length; i++) {
				toAddr[i] = new InternetAddress(to.get(i));
			}
			mimeMsg.setRecipients(Message.RecipientType.TO, toAddr);
			Collections.addAll(allAddr, toAddr);

			Address[] ccAddr = null;
			if (cc != null && cc.size() > 0) {
				ccAddr = new Address[cc.size()];
				for (int i = 0; i < ccAddr.length; i++) {
					ccAddr[i] = new InternetAddress(cc.get(i));
				}
				mimeMsg.setRecipients(Message.RecipientType.CC, ccAddr);
				Collections.addAll(allAddr, ccAddr);
			}

			transport.sendMessage(mimeMsg, allAddr.toArray(new Address[0]));
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				throw e;
			}
		}
	}

	/**
	 * 邮件附件
	 * 
	 * @author yanggui
	 *
	 */
	public static class EmailAttach {
		/** 附件名 **/
		private String attachName;
		/** 附件资源 **/
		private DataSource attachData;

		public EmailAttach() {
			super();
		}

		public EmailAttach(String attachName, DataSource attachData) {
			super();
			this.attachName = attachName;
			this.attachData = attachData;
		}

		public String getAttachName() {
			return attachName;
		}

		public void setAttachName(String attachName) {
			this.attachName = attachName;
		}

		public DataSource getAttachData() {
			return attachData;
		}

		public void setAttachData(DataSource attachData) {
			this.attachData = attachData;
		}

		@Override
		public String toString() {
			return "EmailAttach [attachName=" + attachName + ", attachData=" + attachData + "]";
		}

	}
}
