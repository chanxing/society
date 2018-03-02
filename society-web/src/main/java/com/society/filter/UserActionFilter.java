package com.society.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作过滤
 * 
 * 创建时间:2016年12月13日 下午5:01:30
 * 
 * @author zhou
 * @version 1.0.0
 * 
 */
// @WebFilter(filterName = "loginFilter", urlPatterns = { "/*" })
public class UserActionFilter extends AbstractActionFilter {

	@Override
	protected boolean intercept(String uri) {

		List<String> uris = new ArrayList<>();

		for (String str : uris) {
			if (str.equals(uri)) {
				return true;
			}
		}
		return false;
	}
}
