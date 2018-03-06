package com.society.service;

import java.util.List;

import com.society.model.ClubActivity;

public interface ClubActivityService {

	Integer add(int clubId, int type, String title, String startDate, long startDateInt, String endDate, long endDateInt, String place, String poster, String description, int operator);

	boolean update(int id, int clubId, int type, String title, String startDate, long startDateInt, String endDate, long endDateInt, String place, String poster, String description);

	ClubActivity get(Integer id);

	List<ClubActivity> list(Integer clubId, Integer type, Long startDateInt, Long endDateInt, int start, int size);

	int count(Integer clubId, Integer type, Long startDateInt, Long endDateInt);

	boolean delete(Integer id);
}
