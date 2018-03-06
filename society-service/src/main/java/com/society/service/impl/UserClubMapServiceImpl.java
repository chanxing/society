package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.UserClubMapDao;
import com.society.model.UserClubMap;
import com.society.service.UserClubMapService;

@Service
public class UserClubMapServiceImpl implements UserClubMapService {

	@Autowired
	private UserClubMapDao userClubMapDao;

	@Override
	public boolean add(int userId, int clubId, int departmentId, int positionId, int premitId) {
		return userClubMapDao.add(userId, clubId, departmentId, positionId, premitId);
	}

	@Override
	public List<UserClubMap> list(Integer userId, int start, int size) {
		return userClubMapDao.list(userId, start, size);
	}

	@Override
	public int count(Integer userId) {
		return userClubMapDao.count(userId);
	}

	@Override
	public UserClubMap get(int userId, int clubId) {
		return userClubMapDao.get(userId, clubId);
	}

	@Override
	public boolean updateDepartment(int userId, int clubId, int departmentId) {
		return userClubMapDao.updateDepartment(userId, clubId, departmentId);
	}

	@Override
	public boolean updatePosition(int userId, int clubId, int positionId) {
		return userClubMapDao.updatePosition(userId, clubId, positionId);
	}

	@Override
	public boolean updatePremit(int userId, int clubId, int premitId) {
		return userClubMapDao.updatePremit(userId, clubId, premitId);
	}

	@Override
	public boolean delete(int userId, int clubId) {
		return userClubMapDao.delete(userId, clubId);
	}

	@Override
	public int countPremit(int clubId, int premitId) {
		return userClubMapDao.countPremit(clubId, premitId);
	}

	@Override
	public List<UserClubMap> listMember(int clubId, Integer departmentId, Integer positionId, String keyword, int start, int size) {
		return userClubMapDao.listMember(clubId, departmentId, positionId, keyword, start, size);
	}

	@Override
	public int countMember(int clubId, Integer departmentId, Integer positionId, String keyword) {
		return userClubMapDao.countMember(clubId, departmentId, positionId, keyword);
	}

}
