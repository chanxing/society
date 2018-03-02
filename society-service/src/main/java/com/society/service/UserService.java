package com.society.service;

import com.society.model.User;

public interface UserService {
	/**
	 * @param account
	 *            账户
	 * @param username
	 *            用户名
	 * @param encryptPassword
	 *            已加密密码
	 * @param salt
	 *            盐
	 * @return
	 */
	Integer add(String account, String username, String encryptPassword, String salt);

	/**
	 * @param account
	 *            账户
	 * @param username
	 *            用户名
	 * @param password
	 *            原始密码
	 * @return
	 */
	Integer register(String account, String username, String password);

	User get(Integer id);

	User getByAccount(String account);

	boolean updateImage(int userId, String url);

	/**
	 * 更新用户信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param username
	 *            用户名
	 * @param actualName
	 *            真实姓名
	 * @param gender
	 *            性别
	 * @param birthday
	 *            出生日期
	 * @param nativePlace
	 *            籍贯
	 * @param department
	 *            系别
	 * @param profession
	 *            专业
	 * @param grade
	 *            班级
	 * @param phone
	 *            电话
	 * @param startYear
	 *            入学年份：yyyy
	 * @param qq
	 *            QQ
	 * @param wechat
	 *            微信
	 * @param hobbies
	 *            爱好
	 * @param selIntroduction
	 *            自我介绍
	 * @param image
	 *            头像
	 * @return
	 */
	boolean update(int userId, String username, String actualName, int gender, String birthday, String nativePlace, String department, String profession, String grade, String phone, String startYear,
			String qq, String wechat, String hobbies, String selIntroduction, String image);

	/**
	 * 更新密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	boolean updatePassword(int userId, String password);
}
