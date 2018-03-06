package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.constant.ClubPremitEnum;
import com.society.dao.ClubApplyDao;
import com.society.dao.UserClubMapDao;
import com.society.model.ClubApply;
import com.society.model.UserClubMap;
import com.society.service.ClubApplyService;

@Service
public class ClubApplyServiceImpl implements ClubApplyService {

	@Autowired
	private ClubApplyDao clubApplyDao;
	@Autowired
	private UserClubMapDao userClubMapDao;

	@Override
	public boolean add(int userId, int clubId) {
		return clubApplyDao.add(userId, clubId);
	}

	@Override
	public List<ClubApply> list(Integer clubId, Integer status, int start, int size) {
		return clubApplyDao.list(clubId, status, start, size);
	}

	@Override
	public int count(Integer clubId, Integer status) {
		return clubApplyDao.count(clubId, status);
	}

	@Override
	public boolean pass(int userId, int clubId) {
		boolean result = clubApplyDao.pass(userId, clubId);
		if (result == false) {
			return false;
		}
		UserClubMap map = userClubMapDao.get(userId, clubId);
		if (null == map) {
			return userClubMapDao.add(userId, clubId, 0, 0, ClubPremitEnum.MEMBER.getId());
		}
		return true;
	}

	@Override
	public boolean reject(int userId, int clubId) {
		return clubApplyDao.reject(userId, clubId);
	}

	@Override
	public ClubApply getLast(int userId, int clubId, int status) {
		return clubApplyDao.getLast(userId, clubId, status);
	}

}
