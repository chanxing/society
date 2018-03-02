package com.society.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.VerificationCodeDao;
import com.society.model.VerificationCode;
import com.society.service.VerificationCodeService;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

	@Autowired
	private VerificationCodeDao verificationCodeDao;

	@Override
	public boolean add(String code, int type, String acceptor, Date invalidTime) {
		return verificationCodeDao.add(code, type, acceptor, invalidTime);
	}

	@Override
	public boolean invalidCodes(int type, String acceptor) {
		return verificationCodeDao.invalidCodes(type, acceptor);
	}

	@Override
	public VerificationCode getLastest(int type, String acceptor) {
		return verificationCodeDao.getLastest(type, acceptor);
	}

	@Override
	public int countBySectionInt(int sectionInt, String acceptor) {
		return verificationCodeDao.countBySectionInt(sectionInt, acceptor);
	}

}
