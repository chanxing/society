package com.society.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.dao.ClubDepartmentDao;
import com.society.model.ClubDepartment;
import com.society.service.ClubDepartmentService;

@Service
public class ClubDepartmentServiceImpl implements ClubDepartmentService {

	@Autowired
	private ClubDepartmentDao clubDepartmentDao;

	@Override
	public Integer add(String name, int clubId) {
		return clubDepartmentDao.add(name, clubId);
	}

	@Override
	public List<ClubDepartment> list(Integer clubId) {
		return clubDepartmentDao.list(clubId);
	}

	@Override
	public ClubDepartment get(Integer id) {
		return clubDepartmentDao.get(id);
	}

	@Override
	public boolean update(int id, String name) {
		return clubDepartmentDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return clubDepartmentDao.delete(id);
	}

}
