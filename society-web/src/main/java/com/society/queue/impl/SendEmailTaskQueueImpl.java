package com.society.queue.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.society.util.MailCommon.EmailAttach;
import com.society.queue.SendEmailTaskQueue;
import com.society.queue.model.Email;
import com.society.util.MailUtil;

@Component
public class SendEmailTaskQueueImpl extends BaseTaskQueue<Email> implements SendEmailTaskQueue {

	@Override
	protected boolean work(Email email, int left) {
		boolean result = false;
		List<EmailAttach> attachs = email.getAttachs();
		if (null == attachs || attachs.isEmpty()) {
			result = MailUtil.sendHtmlContent(email.getSubject(), email.getContent(), email.getToEmails(), email.getCcEmails());
		} else {
			result = MailUtil.sendHtmlEmailWithAttach(email.getSubject(), email.getContent(), email.getToEmails(), email.getCcEmails(), attachs);
		}
		return result;
	}

}
