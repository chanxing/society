package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.ClubDao;
import com.society.model.Club;
import com.society.service.ClubService;
import com.society.util.DateUtil;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubDao clubDao;

	@Override
	public Integer add(String name, String logo, int type, int level, int creater) {
		String introduction = DateUtil.getTodayDayStr();
		return clubDao.add(name, logo, introduction, type, level, creater);
	}

	@Override
	public Club get(Integer id) {
		return clubDao.get(id);
	}

	@Override
	public List<Club> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size) {
		return clubDao.list(keyword, type, level, sortType, start, size);
	}

	@Override
	public boolean update(int clubId, String name, String logo, String introduction, String announcement, int type, int level) {
		return clubDao.update(clubId, name, logo, introduction, announcement, type, level);
	}

	@Override
	public boolean delete(int clubId) {
		return clubDao.delete(clubId);
	}

	@Override
	public int count(String keyword, Integer type, Integer level) {
		return clubDao.count(keyword, type, level);
	}

}
