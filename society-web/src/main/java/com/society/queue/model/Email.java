package com.society.queue.model;

import java.util.Arrays;
import java.util.List;

import com.society.util.MailCommon.EmailAttach;

public class Email {

	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 发送邮箱
	 */
	private List<String> toEmails;
	/**
	 * 抄送邮箱
	 */
	private List<String> ccEmails;

	/** 附件 **/
	private List<EmailAttach> attachs;

	public Email(String subject, String content, String toEmail) {
		super();
		this.subject = subject;
		this.content = content;
		this.toEmails = Arrays.asList(toEmail);
	}

	public Email(String subject, String content, List<String> toEmails) {
		super();
		this.subject = subject;
		this.content = content;
		this.toEmails = toEmails;
	}

	public Email(String subject, String content, List<String> toEmails, List<String> ccEmails) {
		this(subject, content, toEmails);
		this.ccEmails = ccEmails;
	}

	public Email(String subject, String content, String toEmail, List<EmailAttach> attachs) {
		super();
		this.subject = subject;
		this.content = content;
		this.toEmails = Arrays.asList(toEmail);
		this.attachs = attachs;
	}

	public Email(String subject, String content, List<String> toEmails, List<String> ccEmails, List<EmailAttach> attachs) {
		this(subject, content, toEmails);
		this.ccEmails = ccEmails;
		this.attachs = attachs;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	public List<String> getToEmails() {
		return toEmails;
	}

	public List<String> getCcEmails() {
		return ccEmails;
	}

	public List<EmailAttach> getAttachs() {
		return attachs;
	}

	@Override
	public String toString() {
		return "Email [subject=" + subject + ", content=" + content + ", toEmails=" + toEmails + ", ccEmails=" + ccEmails + ", attachs=" + attachs + "]";
	}

}
