package com.society.filter;

import java.io.IOException;
import java.util.regex.Pattern;

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

import com.society.util.JsonUtil4CamelCase;
import com.society.web.base.UnifyResult;

/**
 * 用户行为过滤器
 * 
 * 创建时间:2016年12月13日 上午9:37:52
 * 
 * @author zhou
 * @version 1.0.0
 * 
 */
public abstract class AbstractActionFilter implements Filter {

	/** 操作提示 */
	protected static final String RESUBMIT = JsonUtil4CamelCase.toJson(new UnifyResult(400, "重复提交", null));

	protected static final String SAMEACTION2HIGH = JsonUtil4CamelCase.toJson(new UnifyResult(400, "操作过于频繁", null));

	protected static final String DIFFACTION2HIGH = JsonUtil4CamelCase.toJson(new UnifyResult(400, "操作过于频繁", null));

	private static long ONE_SECOND = 1000;

	private static long HALF_SECOND = 500;

	private static long HALF_HOUR = 30000;

	private static Logger logger = LoggerFactory.getLogger(AbstractLoginFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();

		if (!intercept(uri)) {
			chain.doFilter(req, res);
			return;
		}

		/** 过滤提交操作 **/
		if (isReSumbit(req, uri) && isTimeStampOk(req, HALF_HOUR)) {
			logger.info("operate uri:[" + uri + "]重复提交");
			response.getWriter().write(RESUBMIT);
			return;
		}

		/** 过滤其他操作 **/
		if (isSameUri(req, uri) && isTimeStampOk(req, HALF_SECOND)) {
			logger.info("operate uri:[" + uri + "]频率过高");
			response.getWriter().write(SAMEACTION2HIGH);
			return;
		} else if (!isSameUri(req, uri) && isTimeStampOk(req, HALF_SECOND)) {
			response.getWriter().write(DIFFACTION2HIGH);
			return;
		}

		HttpSession session = req.getSession();
		session.setAttribute("timestamp", System.currentTimeMillis());
		session.setAttribute("uri", uri);
		chain.doFilter(req, res);
	}

	/**
	 * 对指定的uri不进行过滤
	 * 
	 * @param uri
	 *            请求
	 */
	protected abstract boolean intercept(String uri);

	/**
	 * 时间间隔对比
	 * 
	 * @param interval
	 *            时间间隔(单位：秒)
	 */
	private boolean isTimeStampOk(HttpServletRequest request, long interval) {
		HttpSession session = request.getSession();
		Long sessionTimestamp = (Long) session.getAttribute("timestamp");
		long crrentTimestamp = System.currentTimeMillis();

		if (sessionTimestamp == null || crrentTimestamp - sessionTimestamp > interval) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * uri对比
	 * 
	 * @param uri
	 *            请求
	 */
	private boolean isSameUri(HttpServletRequest request, String uri) {
		HttpSession session = request.getSession();
		String sessionUri = (String) session.getAttribute("uri");

		if (sessionUri == null) {
			return false;
		} else if (uri.equals(sessionUri)) {
			return true;
		}
		return false;
	}

	/**
	 * 匹配表单操作
	 */

	private boolean isReSumbit(HttpServletRequest request, String uri) {
		HttpSession session = request.getSession();
		String sessionUri = (String) session.getAttribute("uri");
		if (uri.equals(sessionUri)) {
			if (Pattern.matches(".+submit\\S+", uri)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
