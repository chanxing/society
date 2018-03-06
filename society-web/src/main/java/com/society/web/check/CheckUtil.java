package com.society.web.check;

import org.apache.commons.lang3.StringUtils;

import com.society.constant.ClubActivityTypeEnum;
import com.society.constant.ClubLevelEnum;
import com.society.constant.ClubPremitEnum;
import com.society.constant.ClubTypeEnum;
import com.society.constant.GenderEnum;
import com.society.fo.ClubActivityFO;
import com.society.fo.ClubFO;
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

	public static void isValidPage(Integer page) {
		if (null == page || page.intValue() <= 0) {
			throw new ProjectException("非法的页码");
		}
	}

	public static void isValidClubId(Integer clubId) {
		if (null == clubId || clubId.intValue() <= 0) {
			throw new ProjectException("非法的社团ID");
		}
	}

	public static void isValidClubFO(ClubFO fo) {
		if (StringUtils.isBlank(fo.getName())) {
			throw new ProjectException("非法的社团名称");
		}
		if (!ClubTypeEnum.contain(fo.getType())) {
			throw new ProjectException("非法的社团类型");
		}
		if (!ClubLevelEnum.contain(fo.getLevel())) {
			throw new ProjectException("非法的社团类型");
		}
	}

	public static void isValidName(String name) {
		if (StringUtils.isBlank(name)) {
			throw new ProjectException("非法的名称");
		}
	}

	public static void isValidId(Integer id) {
		if (null == id || id.intValue() <= 0) {
			throw new ProjectException("非法的ID");
		}
	}

	public static void isValidUserId(Integer userId) {
		if (null == userId || userId.intValue() <= 0) {
			throw new ProjectException("非法的用户ID");
		}
	}

	public static void isValidPremitId(Integer premitId) {
		if (ClubPremitEnum.contain(premitId) == false) {
			throw new ProjectException("非法的权限ID");
		}
	}

	public static void isValidClubActivityFO(ClubActivityFO fo) {
		if (null == fo) {
			throw new ProjectException("非法的数据");
		}
		if (null == fo.getClubId() || fo.getClubId().intValue() <= 0) {
			throw new ProjectException("非法的社团ID");
		}
		if (!ClubActivityTypeEnum.contain(fo.getType())) {
			throw new ProjectException("非法的活动类型");
		}
		if (StringUtils.isBlank(fo.getTitle())) {
			throw new ProjectException("非法的标题");
		}
		if (StringUtils.isBlank(fo.getStartDate())) {
			throw new ProjectException("非法的开始日期");
		}
		if (StringUtils.isBlank(fo.getEndDate())) {
			throw new ProjectException("非法的结束日期");
		}
		if (StringUtils.isBlank(fo.getPlace())) {
			throw new ProjectException("非法的活动地点");
		}
		if (StringUtils.isBlank(fo.getPoster())) {
			throw new ProjectException("非法的海报");
		}
		if (StringUtils.isBlank(fo.getDescription())) {
			throw new ProjectException("非法的描述");
		}
	}
}
