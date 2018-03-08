package com.society.handler.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.society.constant.ClubApplyStatusEnum;
import com.society.constant.RoleTypeEnum;
import com.society.handler.UserHandler;
import com.society.model.ClubApply;
import com.society.model.User;
import com.society.service.ClubApplyService;
import com.society.service.UserService;
import com.society.vo.ClubApplyVO;
import com.society.vo.UserDealVO;
import com.society.vo.UserVO;

@Component
public class UserHandlerImpl implements UserHandler {

	@Autowired
	private UserService userService;
	@Autowired
	private ClubApplyService clubApplyService;

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
		vo.setGrade(user.getGrade());
		return vo;
	}

	@Override
	public UserVO toUserVO(User user) {
		if (null == user) {
			return null;
		}
		boolean isMaster = user.getRole() == RoleTypeEnum.ADMIN.getId();
		return new UserVO(user.getId(), user.getUsername(), user.getImage(), isMaster);
	}

	@Override
	public UserVO getInfo(Integer id) {
		User user = userService.get(id);
		return toUserVO(user);
	}

	@Override
	public List<ClubApplyVO> listClubApplyUser(int clubId, String keyword, int start, int size) {
		List<ClubApply> list = clubApplyService.list(clubId, ClubApplyStatusEnum.APPLYING.getId(), keyword, start, size);
		List<ClubApplyVO> vos = new ArrayList<>(list.size());
		for (ClubApply apply : list) {
			User user = userService.get(apply.getUserId());
			if (null == user) {
				user = new User();
			}
			ClubApplyVO vo = new ClubApplyVO();
			vo.setActualName(user.getActualName());
			vo.setDepartment(user.getDepartment());
			vo.setGender(user.getGender());
			vo.setId(apply.getId());
			vo.setPhone(user.getPhone());
			vo.setQq(user.getQq());
			vo.setStartYear(user.getStartYear());
			vo.setUserId(user.getId());
			vo.setWeixin(user.getWechat());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public int countClubApplyUser(int clubId, String keyword) {
		return clubApplyService.count(clubId, ClubApplyStatusEnum.APPLYING.getId(), keyword);
	}

}
