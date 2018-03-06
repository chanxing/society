package com.society.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.society.constant.DelConstant;
import com.society.dao.ClubPhotoDao;
import com.society.model.ClubPhoto;

@Repository
public class ClubPhotoDaoMysqlImpl implements ClubPhotoDao {
	private final RowMapper<ClubPhoto> rowMapper = new BeanPropertyRowMapper<ClubPhoto>(ClubPhoto.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public boolean add(int clubId, String url, int operator) {
		String sql = "INSERT INTO club_photo (club_id,url,operator,posttime) VALUES(?,?,?,now())";
		return jdbcTemplate.update(sql, clubId, url, operator) > 0;
	}

	@Override
	public List<ClubPhoto> list(Integer clubId, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT * FROM club_photo WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public int count(Integer clubId) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT COUNT(1) FROM club_photo WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public boolean delete(int id) {
		String sql = "UPDATE club_photo SET del=? WHERE id=?";
		return this.jdbcTemplate.update(sql, DelConstant.DEL, id) > 0;
	}

}
