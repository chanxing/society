package com.society.service;

import java.util.List;

import com.society.model.ClubDepartment;

public interface ClubDepartmentService {

	Integer add(String name, int clubId);

	List<ClubDepartment> list(Integer clubId);

	ClubDepartment get(Integer id);

	boolean update(int id, String name);

	boolean delete(int id);
}
