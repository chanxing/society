package com.society.dao;

import java.util.List;

import com.society.model.Club;

/**
 * 社团
 * 
 * @author beyond
 *
 */
public interface ClubDao {

	/**
	 * @param name
	 * @param logo
	 * @param section
	 *            创建日期 yyyy-MM-dd
	 * @param type
	 * @param level
	 * @param creater
	 * @return
	 */
	Integer add(String name, String logo, String section, int type, int level, int creater);

	Club get(Integer id);

	List<Club> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size);

	int count(String keyword, Integer type, Integer level);

	boolean update(int clubId, String name, String logo, String introduction, String announcement, int type, int level);

	boolean delete(int clubId);

}
