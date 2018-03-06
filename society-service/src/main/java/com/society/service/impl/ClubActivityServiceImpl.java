package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.ClubActivityDao;
import com.society.model.ClubActivity;
import com.society.service.ClubActivityService;

@Service
public class ClubActivityServiceImpl implements ClubActivityService {

	@Autowired
	private ClubActivityDao clubActivityDao;

	@Override
	public Integer add(int clubId, int type, String title, String startDate, long startDateInt, String endDate, long endDateInt, String place, String poster, String description, int operator) {
		return clubActivityDao.add(clubId, type, title, startDate, startDateInt, endDate, endDateInt, place, poster, description, operator);
	}

	@Override
	public ClubActivity get(Integer id) {
		return clubActivityDao.get(id);
	}

	@Override
	public List<ClubActivity> list(Integer clubId, Integer type, Long startDateInt, Long endDateInt, int start, int size) {
		return clubActivityDao.list(clubId, type, startDateInt, endDateInt, start, size);
	}

	@Override
	public int count(Integer clubId, Integer type, Long startDateInt, Long endDateInt) {
		return clubActivityDao.count(clubId, type, startDateInt, endDateInt);
	}

	@Override
	public boolean delete(Integer id) {
		return clubActivityDao.delete(id);
	}

	@Override
	public boolean update(int id, int clubId, int type, String title, String startDate, long startDateInt, String endDate, long endDateInt, String place, String poster, String description) {
		return clubActivityDao.update(id, clubId, type, title, startDate, startDateInt, endDate, endDateInt, place, poster, description);
	}

}
