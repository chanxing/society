package com.society.dao;

import java.util.List;

import com.society.model.ClubPosition;

public interface ClubPositionDao {

	Integer add(String name, int clubId);

	List<ClubPosition> list(Integer clubId);

	ClubPosition get(Integer id);

	boolean update(int id, String name);

	boolean delete(int id);
}
