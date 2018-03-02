package com.society.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.society.constant.DelConstant;
import com.society.handler.UserHandler;
import com.society.model.User;
import com.society.model.User.UserInfo;
import com.society.service.UserService;
import com.society.util.PasswordUtil;
import com.society.web.base.BaseController;
import com.society.web.base.WebConstant;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/")
public class LoginController extends BaseController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private UserHandler userHandler;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String account, String password, HttpSession session) {
		logger.info("login account[{}]", new Object[] { account });
		CheckUtil.isValidAccount(account);
		CheckUtil.isValidPassword(password);
		User user = userService.getByAccount(account);
		if (null == user || user.getDel() != DelConstant.VALID) {
			return FAIL;
		}
		// 校验密码
		if (!PasswordUtil.checkPassword(user.getEncryptPassword(), password, user.getSalt())) {
			return FAIL;
		}
		// 保存到session里
		User.UserInfo info = new User.UserInfo(user.getId(), user.getAccount(), user.getUsername());
		session.setAttribute(WebConstant.SESSION_ATTRIBUTE_KEY_USER, info);
		return toRecordResult(userHandler.toUserVO(user));
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return toBooleanResult(true);
	}

	@RequestMapping("/checkLogin")
	public String checkLogin(HttpSession session) {
		UserInfo userInfo = this.getUser(session);
		if (null == userInfo) {
			return toBooleanResult(false);
		}
		User user = userService.get(userInfo.getId());
		return toRecordResult(userHandler.toUserVO(user));
	}

}
