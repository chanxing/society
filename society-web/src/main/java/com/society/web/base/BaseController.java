package com.society.web.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.society.model.User.UserInfo;
import com.society.util.JsonUtil4CamelCase;

public abstract class BaseController {

	protected static final int PAGE_SIZE = 10;

	protected static final String SUCCESS = JsonUtil4CamelCase.toJson(new UnifyResult(200, "", true));

	protected static final String FAIL = JsonUtil4CamelCase.toJson(new UnifyResult(200, "", false));

	protected static final String ERROR = JsonUtil4CamelCase.toJson(new UnifyResult(400, "", null));

	// 状态码code
	//
	// 1XX，登陆权限异常
	// 100，未登录，跳转到登陆页面
	// 101，该用户无权访问该请求
	// 2XX，请求成功
	// 200，请求成功
	// 1. 请求为操作动作时，data == true 代表操作成功，前端提示写死在页面的数据（如，“登录成功”）
	// 2. 请求为操作动作时，data == false 代表操作失败，前端提示写死在页面的数据（如，“登录失败”）
	// 3. 请求为查询动作时，data为查询得到的json数据
	// 201，请求成功，需要展示给用户返回的data
	// 3XX，跳转链接
	// 300，直接跳转到链接（data）
	// 301，展示给用户返回的结果后，用户确认后，再跳转到链接
	// 4XX，业务逻辑错误
	// 400，请求错误，提示message给用户
	// 5XX，服务器异常
	// 500，服务器异常
	// 501，服务器停机维护中

	protected static String toSelectResult(List<Map<String, String>> selects) {

		Map<String, String> result = new LinkedHashMap<>();

		for (Map<String, String> select : selects) {

			result.put(select.get("id"), select.get("name"));

		}
		UnifyResult unifyResult = new UnifyResult(200, "", result);
		return JsonUtil4CamelCase.toJson(unifyResult);
	}

	protected static String toArrayResult(List<String> array) {
		UnifyResult unifyResult = new UnifyResult(200, "", array);
		return JsonUtil4CamelCase.toJson(unifyResult);
	}

	// data：{
	// pageCount：分页总数
	// page：当前页数
	// count：记录总数
	// records：记录列表or null，不同的请求记录结构不同
	// id：表格 id 用于自定义列
	// }
	protected static String toListResult(int id, List<? extends Object> records, int count, int page, int size, Map<String, Object> params) {

		params.put("records", records);
		params.put("page", page);
		params.put("count", count);
		int pageCount = (int) Math.ceil(count * 1.0 / size);
		params.put("pageCount", pageCount);
		params.put("id", id);

		UnifyResult unifyResult = new UnifyResult(200, "", params);
		return JsonUtil4CamelCase.toJson(unifyResult);
	}

	protected static String toListResult(int id, List<? extends Object> records, int count, int page, int size) {
		return toListResult(id, records, count, page, size, new HashMap<String, Object>());
	}

	protected static String toListResult(int id, List<? extends Object> records, int count) {
		return toListResult(id, records, count, 1, count, new HashMap<String, Object>());
	}

	protected static String toBooleanResult(boolean result) {
		UnifyResult unifyResult = new UnifyResult(200, "", result);
		return JsonUtil4CamelCase.toJson(unifyResult);
	}

	protected static String toRecordResult(Object result) {
		UnifyResult unifyResult = new UnifyResult(200, "", result);
		return JsonUtil4CamelCase.toJson(unifyResult);
	}

	protected UserInfo getUser(HttpSession session) {
		return (UserInfo) session.getAttribute(WebConstant.SESSION_ATTRIBUTE_KEY_USER);
	}

	protected Integer getUserId(HttpSession session) {
		UserInfo user = this.getUser(session);
		if (null == user) {
			return null;
		}
		return user.getId();
	}

	public static void main(String[] args) {
		List<Map<String, String>> selects = new ArrayList<>();

		Map<String, String> select2 = new HashMap<String, String>();
		select2.put("id", "id2");
		select2.put("name", "name2");
		selects.add(select2);

		Map<String, String> select1 = new HashMap<String, String>();
		select1.put("id", "id1");
		select1.put("name", "name1");
		selects.add(select1);

		Map<String, String> select0 = new HashMap<String, String>();
		select0.put("id", "id0");
		select0.put("name", "name0");
		selects.add(select0);

		System.out.println(toSelectResult(selects));

	}

}
