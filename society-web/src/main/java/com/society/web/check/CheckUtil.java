package com.society.web.check;

import org.apache.commons.lang3.StringUtils;

import com.society.constant.GenderEnum;
import com.society.fo.UserFO;
import com.society.util.exception.ProjectException;

public class CheckUtil {
	/** 邮箱正则 **/
	public static final String EMAIL_REGEX = ".+@.+";
	/** 密码最小长度 **/
	public static final int PASSWORD_LENGTH_MIN = 6;

	public static void isValidAccount(String account) {
		if (StringUtils.isBlank(account)) {
			throw new ProjectException("非法的帐号");
		}
	}

	public static void isValidPassword(String password) {
		if (StringUtils.isBlank(password)) {
			throw new ProjectException("非法的密码");
		}
		if (password.length() < PASSWORD_LENGTH_MIN) {
			throw new ProjectException("非法的密码");
		}
	}

	public static void isValidEmail(String email) {
		if (StringUtils.isBlank(email)) {
			throw new ProjectException("非法的邮箱");
		}
		if (!email.matches(EMAIL_REGEX)) {
			throw new ProjectException("非法的邮箱");
		}
	}

	public static void isValidUsername(String username) {
		if (StringUtils.isBlank(username)) {
			throw new ProjectException("非法的用户名");
		}
	}

	public static void isValidCode(String code) {
		if (StringUtils.isBlank(code)) {
			throw new ProjectException("非法的验证码");
		}
	}

	public static void isValidUserFO(UserFO fo) {
		if (null == fo) {
			throw new ProjectException("数据不能为空");
		}
		if (StringUtils.isBlank(fo.getUsername())) {
			throw new ProjectException("非法的用户名");
		}
		if (!GenderEnum.contain(fo.getGender())) {
			throw new ProjectException("非法的性别");
		}
	}
}
