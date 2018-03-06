package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.ClubPhotoDao;
import com.society.model.ClubPhoto;
import com.society.service.ClubPhotoService;

@Service
public class ClubPhotoServiceImpl implements ClubPhotoService {

	@Autowired
	private ClubPhotoDao clubPhotoDao;

	@Override
	public boolean add(int clubId, String url, int operator) {
		return clubPhotoDao.add(clubId, url, operator);
	}

	@Override
	public List<ClubPhoto> list(Integer clubId, int start, int size) {
		return clubPhotoDao.list(clubId, start, size);
	}

	@Override
	public int count(Integer clubId) {
		return clubPhotoDao.count(clubId);
	}

	@Override
	public boolean delete(int id) {
		return clubPhotoDao.delete(id);
	}

}
