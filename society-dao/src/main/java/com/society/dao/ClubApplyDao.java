package com.society.dao;

import java.util.List;

import com.society.model.ClubApply;

public interface ClubApplyDao {

	boolean add(int userId, int clubId);

	List<ClubApply> list(Integer clubId, Integer status, String keyword, int start, int size);

	int count(Integer clubId, Integer status, String keyword);

	boolean pass(int userId, int clubId);

	boolean reject(int userId, int clubId);

	ClubApply getLast(int userId, int clubId, int status);
}
