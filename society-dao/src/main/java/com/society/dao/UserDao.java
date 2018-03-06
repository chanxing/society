package com.society.dao;

import com.society.model.User;

public interface UserDao {

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

	User get(Integer id);

	User getByAccount(String account);

	/**
	 * @param userId
	 * @param url
	 *            图片路径
	 * @return
	 */
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

	boolean updatePassword(int userId, String encryptPassword, String salt);

	int countByClubId(Integer clubId);
}
