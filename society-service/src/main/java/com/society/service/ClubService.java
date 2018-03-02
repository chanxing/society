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

	Integer add(String name, String logo, String introduction, int type, int level, int creater);

	Club get(Integer id);

	List<Club> list(String keyword, Integer type, Integer level);
}
