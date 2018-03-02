package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.society.dao.ClubDao;
import com.society.model.Club;
import com.society.service.ClubService;

public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubDao clubDao;

	@Override
	public Integer add(String name, String logo, String introduction, int type, int level, int creater) {
		return clubDao.add(name, logo, introduction, type, level, creater);
	}

	@Override
	public Club get(Integer id) {
		return clubDao.get(id);
	}

	@Override
	public List<Club> list(String keyword, Integer type, Integer level) {
		return clubDao.list(keyword, type, level);
	}

}
