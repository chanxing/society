package com.society.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.UserDao;
import com.society.model.User;
import com.society.service.UserService;
import com.society.util.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Integer add(String account, String username, String encryptPassword, String salt) {
		return userDao.add(account, username, encryptPassword, salt);
	}

	@Override
	public User get(Integer id) {
		return userDao.get(id);
	}

	@Override
	public User getByAccount(String account) {
		return userDao.getByAccount(account);
	}

	@Override
	public Integer register(String account, String username, String password) {
		String salt = PasswordUtil.getSalt();
		String encryptPassword = PasswordUtil.getEncryptPassword(password, salt);
		return this.add(account, username, encryptPassword, salt);
	}

	@Override
	public boolean updateImage(int userId, String url) {
		return userDao.updateImage(userId, url);
	}

	@Override
	public boolean update(int userId, String username, String actualName, int gender, String birthday, String nativePlace, String department, String profession, String grade, String phone,
			String startYear, String qq, String wechat, String hobbies, String selIntroduction, String image) {
		return userDao.update(userId, username, actualName, gender, birthday, nativePlace, department, profession, grade, phone, startYear, qq, wechat, hobbies, selIntroduction, image);
	}

	@Override
	public boolean updatePassword(int userId, String password) {
		String salt = PasswordUtil.getSalt();
		String encryptPassword = PasswordUtil.getEncryptPassword(password, salt);
		return userDao.updatePassword(userId, encryptPassword, salt);
	}

}
