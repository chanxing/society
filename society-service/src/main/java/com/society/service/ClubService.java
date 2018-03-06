package com.society.service;

import java.util.List;

import com.society.model.Club;

/**
 * 社团
 * 
 * @author beyond
 *
 */
public interface ClubService {

	Integer add(String name, String logo, int type, int level, int creater);

	Club get(Integer id);

	List<Club> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size);

	int count(String keyword, Integer type, Integer level);

	boolean update(int clubId, String name, String logo, String introduction, String announcement, int type, int level);

	boolean delete(int clubId);
}
