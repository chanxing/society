package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.ClubPositionDao;
import com.society.model.ClubPosition;
import com.society.service.ClubPositionService;

@Service
public class ClubPositionServiceImpl implements ClubPositionService {

	@Autowired
	private ClubPositionDao clubPositionDao;

	@Override
	public Integer add(String name, int clubId) {
		return clubPositionDao.add(name, clubId);
	}

	@Override
	public List<ClubPosition> list(Integer clubId) {
		return clubPositionDao.list(clubId);
	}

	@Override
	public ClubPosition get(Integer id) {
		return clubPositionDao.get(id);
	}

	@Override
	public boolean update(int id, String name) {
		return clubPositionDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return clubPositionDao.delete(id);
	}
}
