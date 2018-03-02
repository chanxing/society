package com.society.util;

import java.util.UUID;

/**
 * 密码工具类
 * 
 * @author beyond
 *
 */
public class PasswordUtil {

	/**
	 * 加密密码
	 * 
	 * @param password
	 *            待加密密码
	 * @param salt
	 *            盐
	 * @return
	 */
	public static String getEncryptPassword(String password, String salt) {
		return Md5Util.md5(password + ":" + salt);
	}

	/**
	 * 比较已加密密码与未加密密码是否相等
	 * 
	 * @param encryptPassword
	 *            已加密密码
	 * @param password
	 *            待检测密码
	 * @param salt
	 *            盐
	 * @return
	 */
	public static boolean checkPassword(String encryptPassword, String password, String salt) {
		String md5 = getEncryptPassword(password, salt);
		return encryptPassword.equals(md5);
	}

	public static String getSalt() {
		return UUID.randomUUID().toString();
	}
}
