package com.society.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.society.model.User;
import com.society.util.JsonUtil4CamelCase;
import com.society.web.base.UnifyResult;
import com.society.web.base.WebConstant;

public abstract class AbstractLoginFilter implements Filter {

	protected static final String NOLOGIN = JsonUtil4CamelCase.toJson(new UnifyResult(100, "未登录", null));

	protected static final String DENY = JsonUtil4CamelCase.toJson(new UnifyResult(101, "登陆成功、权限不足", null));

	protected static final String REFRESH = JsonUtil4CamelCase.toJson(new UnifyResult(102, "登陆成功，角色、代理不正确", null));

	private static Logger logger = LoggerFactory.getLogger(AbstractLoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 请求路径
		String uri = req.getRequestURI();
		if (!intercept(uri)) {
			logger.info("operate uri[" + uri + "]");
			chain.doFilter(req, res);
			return;
		}

		HttpSession session = req.getSession();
		User.UserInfo user = (User.UserInfo) session.getAttribute(WebConstant.SESSION_ATTRIBUTE_KEY_USER);

		if (user == null) {
			response.getWriter().write(NOLOGIN);
			return;
		}

		if (isNeedRefresh(req)) {
			response.getWriter().write(REFRESH);
			return;
		}

		if (!isRoleOk(req, user)) {
			response.getWriter().write(DENY);
			return;
		}

		logger.info("operate user[" + user + "], uri[" + uri + "]");
		chain.doFilter(req, res);
	}

	protected abstract boolean intercept(String uri);

	protected abstract boolean isRoleOk(HttpServletRequest request, User.UserInfo user);

	protected abstract boolean isNeedRefresh(HttpServletRequest request);

	@Override
	public void destroy() {

	}

}
