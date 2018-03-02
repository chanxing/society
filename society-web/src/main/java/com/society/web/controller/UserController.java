package com.society.web.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.society.constant.VerificationCodeTypeEnum;
import com.society.handler.UserHandler;
import com.society.model.User;
import com.society.model.VerificationCode;
import com.society.queue.SendEmailTaskQueue;
import com.society.queue.model.Email;
import com.society.service.UserService;
import com.society.service.VerificationCodeService;
import com.society.util.RandomUtil;
import com.society.util.exception.ProjectException;
import com.society.vo.UserVO;
import com.society.web.base.BaseController;
import com.society.web.base.WebConstant;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/")
public class UserController extends BaseController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	/** 验证码长度 **/
	private static final int VERIFICATION_CODE_LENGTH = 6;
	/** 邮箱验证码有效时长，单位：秒 **/
	private static final int VERIFICATION_CODE_EMAIL_VALID_TIME = 30 * 60;
	/** 验证码状态：超时 **/
	private static final int VERIFICATION_CODE_STATUS_TIME_OUT = -1;
	/** 验证码状态：错误 **/
	private static final int VERIFICATION_CODE_STATUS_WRONG = 0;
	/** 验证码状态：正确 **/
	private static final int VERIFICATION_CODE_STATUS_RIGHT = 1;

	@Autowired
	private SendEmailTaskQueue sendEmailTaskQueue;
	@Autowired
	private UserService userService;
	@Autowired
	private VerificationCodeService verificationCodeService;
	@Autowired
	private UserHandler userHandler;

	/**
	 * 发送邮箱注册码
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/sendRegisterCode")
	public String sendRegisterCode(String email) {
		logger.info("sendRegisterCode email[{}]", new Object[] { email });
		CheckUtil.isValidEmail(email);
		User user = userService.getByAccount(email);
		if (null != user) {
			throw new ProjectException("账户已存在");
		}
		boolean result = false;
		// 3.创建验证码
		String code = RandomUtil.randomNumbers(VERIFICATION_CODE_LENGTH);
		// 4.设置验证码过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, VERIFICATION_CODE_EMAIL_VALID_TIME);
		// 5.保存验证码
		result = verificationCodeService.add(code, VerificationCodeTypeEnum.REGISTER.getId(), email, calendar.getTime());
		// 6.发送验证码
		if (result) {
			String subject = "社团注册";
			StringBuilder content = new StringBuilder(50);
			content.append("尊敬的用户，你好：\n\n");
			content.append("您的验证码为：\n\n");
			content.append(code);
			content.append("\n\n（30分钟内有效）");
			Email emailEntity = new Email(subject, content.toString(), email);
			sendEmailTaskQueue.push(emailEntity, 2);
		}
		return toBooleanResult(result);
	}

	@RequestMapping(value = "/checkCode")
	@ResponseBody
	public String checkCode(String email, String code, Integer type) {
		logger.info("checkCode email[{}], code[{}], type[{}]", new Object[] { email, code, type });
		CheckUtil.isValidEmail(email);
		CheckUtil.isValidCode(code);
		VerificationCodeTypeEnum vtype = VerificationCodeTypeEnum.get(type);
		if (null == vtype) {
			throw new ProjectException("非法的验证码类型");
		}
		int result = checkVerificationCodeStatus(email, code, type);
		return toRecordResult(result);
	}

	/**
	 * 验证码注册
	 * 
	 * @param email
	 * @param username
	 * @param password
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/registerByCode", method = { RequestMethod.POST })
	public String registerByCode(String email, String username, String password, String code, HttpSession session) {
		logger.info("registerByCode email[{}], username[{}], code[{}]", new Object[] { email, username, code });
		// 检验参数
		CheckUtil.isValidEmail(email);
		CheckUtil.isValidUsername(username);
		CheckUtil.isValidPassword(password);
		CheckUtil.isValidCode(code);
		// 1.检验用户名、帐号、验证码
		User user = this.userService.getByAccount(email);
		if (null != user) {
			throw new ProjectException("帐号已存在");
		}
		int codeStatus = checkVerificationCodeStatus(email, code, VerificationCodeTypeEnum.REGISTER.getId());
		if (codeStatus != VERIFICATION_CODE_STATUS_RIGHT) {
			// 验证码不可用
			throw new ProjectException("验证码已过期或错误");
		}
		// 2.注册
		// 3.激活
		Integer userId = userService.register(email, username, password);
		if (null == userId) {
			return FAIL;
		}
		// 4.使验证码失效
		verificationCodeService.invalidCodes(VerificationCodeTypeEnum.REGISTER.getId(), email);
		// 登录
		User.UserInfo info = new User.UserInfo(userId, email, username);
		session.setAttribute(WebConstant.SESSION_ATTRIBUTE_KEY_USER, info);
		UserVO vo = userHandler.getInfo(userId);
		return toRecordResult(vo);
	}

	private int checkVerificationCodeStatus(String acceptor, String code, int type) {
		VerificationCode verificationCode = verificationCodeService.getLastest(type, acceptor);
		if (null == verificationCode) {
			// 不存在验证码
			return VERIFICATION_CODE_STATUS_TIME_OUT;
		} else {
			// 先校验验证码超时，再验证验证码错误，防止未发送验证码
			String lastCode = verificationCode.getCode();
			Date invalidTime = verificationCode.getInvalidTime();
			long lastMillis = null == invalidTime ? 0L : invalidTime.getTime();
			long currentMillis = System.currentTimeMillis();
			if (currentMillis > lastMillis) {
				// 验证码超时
				return VERIFICATION_CODE_STATUS_TIME_OUT;
			} else if (!code.equals(lastCode)) {
				// 验证码错误
				return VERIFICATION_CODE_STATUS_WRONG;
			}

		}
		return VERIFICATION_CODE_STATUS_RIGHT;// -1.失效,0.错误，1.正确
	}

	/**
	 * 发送邮箱重置密码验证码
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/sendResetPwdCode")
	public String sendResetPwdCode(String email) {
		logger.info("sendResetPwdCode email[{}]", new Object[] { email });
		CheckUtil.isValidEmail(email);
		User user = userService.getByAccount(email);
		if (null == user) {
			throw new ProjectException("账户不存在");
		}
		boolean result = false;
		// 3.创建验证码
		String code = RandomUtil.randomNumbers(VERIFICATION_CODE_LENGTH);
		// 4.设置验证码过期时间
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, VERIFICATION_CODE_EMAIL_VALID_TIME);
		// 5.保存验证码
		result = verificationCodeService.add(code, VerificationCodeTypeEnum.RESET_PASSWORD.getId(), email, calendar.getTime());
		// 6.发送验证码
		if (result) {
			String subject = "社团重置密码";
			StringBuilder content = new StringBuilder(50);
			content.append("尊敬的用户，你好：\n\n");
			content.append("您的验证码为：\n\n");
			content.append(code);
			content.append("\n\n（30分钟内有效）");
			Email emailEntity = new Email(subject, content.toString(), email);
			sendEmailTaskQueue.push(emailEntity, 2);
		}
		return toBooleanResult(result);
	}

	@RequestMapping(value = "/resetPwdByCode", method = { RequestMethod.POST })
	public String resetPwdByCode(String email, String password, String code, HttpSession session) {
		logger.info("resetPwdByCode email[{}], code[{}]", new Object[] { email, code });
		// 检验参数
		CheckUtil.isValidEmail(email);
		CheckUtil.isValidPassword(password);
		CheckUtil.isValidCode(code);
		// 1.检验用户名、帐号、验证码
		User user = this.userService.getByAccount(email);
		if (null == user) {
			throw new ProjectException("帐户不存在");
		}
		int codeStatus = checkVerificationCodeStatus(email, code, VerificationCodeTypeEnum.RESET_PASSWORD.getId());
		if (codeStatus != VERIFICATION_CODE_STATUS_RIGHT) {
			// 验证码不可用
			throw new ProjectException("验证码已过期或错误");
		}
		boolean result = userService.updatePassword(user.getId(), password);
		if (false == result) {
			return FAIL;
		}
		// 4.使验证码失效
		verificationCodeService.invalidCodes(VerificationCodeTypeEnum.RESET_PASSWORD.getId(), email);
		return toBooleanResult(result);
	}
}
