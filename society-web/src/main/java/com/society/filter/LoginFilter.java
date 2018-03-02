package com.society.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhenzhijian
 * 
 *         普通用户登陆
 */
@WebFilter(filterName = "loginFilter", urlPatterns = { "/deal/*" })
public class LoginFilter extends AbstractLoginFilter {

	@Override
	protected boolean intercept(String uri) {
		return true;
	}

	@Override
	protected boolean isRoleOk(HttpServletRequest request, com.society.model.User.UserInfo user) {
		return true;
	}

	@Override
	protected boolean isNeedRefresh(HttpServletRequest request) {
		return false;
	}

}
