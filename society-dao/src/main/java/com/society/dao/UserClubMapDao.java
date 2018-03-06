package com.society.dao;

import java.util.List;

import com.society.model.UserClubMap;

public interface UserClubMapDao {

	boolean add(int userId, int clubId, int departmentId, int positionId, int premitId);

	List<UserClubMap> list(Integer userId, int start, int size);

	int count(Integer userId);

	UserClubMap get(int userId, int clubId);

	boolean updateDepartment(int userId, int clubId, int departmentId);

	boolean updatePosition(int userId, int clubId, int positionId);

	boolean updatePremit(int userId, int clubId, int premitId);

	boolean delete(int userId, int clubId);

	int countPremit(int clubId, int premitId);

	List<UserClubMap> listMember(int clubId, Integer departmentId, Integer positionId, String keyword, int start, int size);

	int countMember(int clubId, Integer departmentId, Integer positionId, String keyword);
}
