package com.society.service;

import java.util.List;

import com.society.model.ClubApply;

public interface ClubApplyService {

	boolean add(int userId, int clubId);

	List<ClubApply> list(Integer clubId, Integer status, int start, int size);

	int count(Integer clubId, Integer status);

	boolean pass(int userId, int clubId);

	boolean reject(int userId, int clubId);

	ClubApply getLast(int userId, int clubId, int status);
}
