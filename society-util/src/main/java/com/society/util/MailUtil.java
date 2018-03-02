package com.society.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.mail.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.society.util.MailCommon.EmailAttach;

public class MailUtil {

	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

	private static Session otherSession = null;

	public static Session getOtherSession() {
		if (otherSession == null) {
			otherSession = Session.getInstance(MailConfig.getCONFIG().getOtherConfig());
		}
		return otherSession;
	}

	private static String getHtml(String content) {
		content = content + "\n\n本邮件由系统自动发送，请勿直接回复！\n祝您使用愉快！\n";
		content = content + "\n点击登陆";
		String url = Config.getCONFIG().getDomain() + "/";
		content = content + "\n <a href = '" + url + "'>" + url + "</a> \n";
		return content.replaceAll("\n", "<br/>");
	}

	private static String getSubject(String subject) {
		return subject;
	}

	public static boolean sendHtmlContent(final String subject, final String content, final String toEmail) {
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					if (toEmail == null) {
						return false;
					}
					String mailAddress = toEmail.trim();
					MailCommon.sendHtmlEmail(getOtherSession(), mailAddress, getSubject(subject), getHtml(content));
					logger.info("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]");
					return true;
				} catch (Exception e) {
					logger.warn("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]报错", e);
					return false;
				}
			}
		});

		exec.execute(future);

		try {
			return future.get(1000 * 10, TimeUnit.MILLISECONDS); // 任务处理超时时间设为10s
		} catch (Exception e) {
			logger.warn("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]报错", e);
			return false;
		} finally {
			exec.shutdown();
		}
	}

	public static boolean sendHtmlContent(final String subject, final String content, final List<String> to, final List<String> cc) {
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					List<String> toOtherList = new ArrayList<>();
					for (String addr : to) {
						if (addr == null) {
							continue;
						}
						addr = addr.trim();
						toOtherList.add(addr);
					}

					List<String> ccOtherList = new ArrayList<>();
					if (cc != null) {
						for (String addr : cc) {
							if (addr == null) {
								continue;
							}
							addr = addr.trim();
							ccOtherList.add(addr);
						}
					}
					if (!toOtherList.isEmpty()) {
						MailCommon.sendHtmlEmail(getOtherSession(), toOtherList, ccOtherList, getSubject(subject), getHtml(content));
					}
					logger.info("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]");
					return true;
				} catch (Exception e) {
					logger.warn("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]报错", e);
					return false;
				}
			}
		});

		exec.execute(future);

		try {
			return future.get(1000 * 10, TimeUnit.MILLISECONDS); // 任务处理超时时间设为10s
		} catch (Exception e) {
			logger.warn("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]报错", e);
			return false;
		} finally {
			exec.shutdown();
		}
	}

	/**
	 * 发送单个客户带附件的邮件
	 * 
	 * @param subject
	 * @param content
	 * @param toEmail
	 * @param attachName
	 * @param file
	 * @return
	 */
	public static boolean sendHtmlEmailWithAttach(final String subject, final String content, final String toEmail, final List<EmailAttach> attachs) {
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					if (toEmail == null) {
						return false;
					}
					String mailAddress = toEmail.trim();
					MailCommon.sendHtmlEmailWithAttach(getOtherSession(), mailAddress, getSubject(subject), getHtml(content), attachs);
					logger.info("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]");
					return true;
				} catch (Exception e) {
					logger.warn("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]报错", e);
					return false;
				}
			}
		});

		exec.execute(future);

		try {
			return future.get(1000 * 10, TimeUnit.MILLISECONDS); // 任务处理超时时间设为10s
		} catch (Exception e) {
			logger.warn("发送邮件subject[" + subject + "], toEmail[" + toEmail + "]报错", e);
			return false;
		} finally {
			exec.shutdown();
		}
	}

	/**
	 * 发送多个客户带附件的邮件
	 * 
	 * @param subject
	 * @param content
	 * @param to
	 * @param cc
	 * @param attachName
	 * @param file
	 * @return
	 */
	public static boolean sendHtmlEmailWithAttach(final String subject, final String content, final List<String> to, final List<String> cc, final List<EmailAttach> attachs) {
		final ExecutorService exec = Executors.newFixedThreadPool(1);
		FutureTask<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
			@Override
			public Boolean call() {
				try {
					List<String> toOtherList = new ArrayList<>();
					for (String addr : to) {
						if (addr == null) {
							continue;
						}
						addr = addr.trim();
						toOtherList.add(addr);
					}

					List<String> ccWesdomList = new ArrayList<>();
					List<String> ccWeadList = new ArrayList<>();
					List<String> ccAdbrightList = new ArrayList<>();
					List<String> ccOtherList = new ArrayList<>();
					if (cc != null) {
						for (String addr : cc) {
							if (addr == null) {
								continue;
							}
							addr = addr.trim();
							if (addr.endsWith("@wesdom.me")) {
								ccWesdomList.add(addr);
							} else if (addr.endsWith("@wead.mobi")) {
								ccWeadList.add(addr);
							} else if (addr.endsWith("@email.adbright.cn")) {
								ccAdbrightList.add(addr);
							} else {
								ccOtherList.add(addr);
							}
						}
					}

					if (!toOtherList.isEmpty()) {
						MailCommon.sendHtmlEmailWithAttach(getOtherSession(), toOtherList, ccOtherList, getSubject(subject), getHtml(content), attachs);
					}
					logger.info("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]");
					return true;
				} catch (Exception e) {
					logger.warn("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]报错", e);
					return false;
				}
			}
		});

		exec.execute(future);

		try {
			return future.get(1000 * 10, TimeUnit.MILLISECONDS); // 任务处理超时时间设为10s
		} catch (Exception e) {
			logger.warn("发送邮件subject[" + subject + "], to[" + to + "], cc[" + cc + "]报错", e);
			return false;
		} finally {
			exec.shutdown();
		}
	}
}
