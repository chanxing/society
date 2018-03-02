package com.society.dao;

import java.util.Date;

import com.society.model.VerificationCode;

/**
 * 验证码
 * 
 * @author yanggui
 *
 */
public interface VerificationCodeDao {

	/**
	 * 插入
	 * 
	 * @param code
	 *            验证码，必填
	 * @param type
	 *            验证码类型，必填
	 * @param acceptor
	 *            接收方，必填
	 * @param invalidTime
	 *            过期时间，必填
	 * @return
	 */
	boolean add(String code, int type, String acceptor, Date invalidTime);

	/**
	 * 使验证码失效
	 * 
	 * @param type
	 *            验证码类型，必填
	 * @param acceptor
	 *            接收方，必填
	 * @return
	 */
	boolean invalidCodes(int type, String acceptor);

	/**
	 * 获取最新的验证码
	 * 
	 * @param type
	 *            验证码类型，必填
	 * @param acceptor
	 *            接收方，必填
	 * @return
	 */
	VerificationCode getLastest(int type, String acceptor);

	/**
	 * 查询数量
	 * 
	 * @param sectionInt
	 *            发送日期：yyyyMMdd，必填
	 * @param acceptor接收方，必填
	 * @return
	 */
	int countBySectionInt(int sectionInt, String acceptor);

}
