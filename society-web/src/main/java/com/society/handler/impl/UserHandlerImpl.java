package com.society.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.society.handler.UserHandler;
import com.society.model.User;
import com.society.service.UserService;
import com.society.vo.UserDealVO;
import com.society.vo.UserVO;

@Component
public class UserHandlerImpl implements UserHandler {

	@Autowired
	private UserService userService;

	@Override
	public UserDealVO get(Integer id) {
		User user = userService.get(id);
		return toUserDealVO(user);
	}

	private UserDealVO toUserDealVO(User user) {
		if (null == user) {
			return null;
		}
		UserDealVO vo = new UserDealVO();
		vo.setUserId(user.getId());
		vo.setActualName(user.getActualName());
		vo.setBirthday(user.getBirthday());
		vo.setDepartment(user.getDepartment());
		vo.setEmail(user.getAccount());
		vo.setGender(user.getGender());
		vo.setHobbies(user.getHobbies());
		vo.setImage(user.getImage());
		vo.setNativePlace(user.getNativePlace());
		vo.setPhone(user.getPhone());
		vo.setProfession(user.getProfession());
		vo.setQq(user.getQq());
		vo.setSelIntroduction(user.getSelIntroduction());
		vo.setStartYear(user.getStartYear());
		vo.setUsername(user.getUsername());
		vo.setWeixin(user.getWechat());
		return vo;
	}

	@Override
	public UserVO toUserVO(User user) {
		if (null == user) {
			return null;
		}
		// TODO:
		boolean isMaster = true;
		return new UserVO(user.getId(), user.getUsername(), user.getImage(), isMaster);
	}

	@Override
	public UserVO getInfo(Integer id) {
		User user = userService.get(id);
		return toUserVO(user);
	}

}
