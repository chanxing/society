package com.society.service;

import java.util.List;

import com.society.model.ClubPhoto;

public interface ClubPhotoService {

	boolean add(int clubId, String url, int operator);

	List<ClubPhoto> list(Integer clubId, int start, int size);

	int count(Integer clubId);

	boolean delete(int id);
}
