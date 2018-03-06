package com.society.dao;

import java.util.List;

import com.society.model.ClubDepartment;

public interface ClubDepartmentDao {

	Integer add(String name, int clubId);

	List<ClubDepartment> list(Integer clubId);

	ClubDepartment get(Integer id);

	boolean update(int id, String name);

	boolean delete(int id);
}
