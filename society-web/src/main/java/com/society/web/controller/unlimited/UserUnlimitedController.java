package com.society.web.controller.unlimited;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.handler.UserHandler;
import com.society.vo.UserDealVO;
import com.society.web.base.BaseController;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/unlimited/user")
public class UserUnlimitedController extends BaseController {
	Logger logger = LoggerFactory.getLogger(UserUnlimitedController.class);

	@Autowired
	private UserHandler userHandler;

	/**
	 * 获取用户详情<br>
	 * uri:/unlimited/user/getUserDetail.do
	 * 
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserDetail")
	public String getUserDetail(Integer userId, HttpSession session) {
		logger.info("getUserDetail userId[{}]", new Object[] { userId });
		CheckUtil.isValidUserId(userId);
		UserDealVO user = userHandler.get(userId);
		if (null == user) {
			user = new UserDealVO();
		}
		return toRecordResult(user);
	}
}
