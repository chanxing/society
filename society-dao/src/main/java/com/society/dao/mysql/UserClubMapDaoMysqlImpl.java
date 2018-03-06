package com.society.dao.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.society.dao.UserClubMapDao;
import com.society.model.UserClubMap;

@Repository
public class UserClubMapDaoMysqlImpl implements UserClubMapDao {
	private final RowMapper<UserClubMap> rowMapper = new BeanPropertyRowMapper<UserClubMap>(UserClubMap.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public boolean add(int userId, int clubId, int departmentId, int positionId, int premitId) {
		String sql = "INSERT INTO user_club_map (user_id,club_id,department_id,position_id,premit_id,posttime) VALUES(?,?,?,?,?,now())";
		return jdbcTemplate.update(sql, userId, clubId, departmentId, positionId, premitId) > 0;
	}

	@Override
	public List<UserClubMap> list(Integer userId, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		args.put("start", start);
		args.put("size", size);
		String clubSql = " SELECT id FROM club WHERE del=0 AND id=user_club_map.club_id ";
		String sql = "SELECT * FROM user_club_map WHERE 1=1 AND EXISTS (" + clubSql + ") ";

		if (null != userId) {
			args.put("userId", userId);
			sql += " AND user_id=:userId ";
		}
		sql += " ORDER BY posttime,club_id,user_id LIMIT :start,:size ";
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public int count(Integer userId) {
		Map<String, Object> args = new HashMap<>();
		String clubSql = " SELECT id FROM club WHERE del=0 AND id=user_club_map.club_id ";
		String sql = "SELECT COUNT(1) FROM user_club_map WHERE 1=1 AND EXISTS (" + clubSql + ") ";

		if (null != userId) {
			args.put("userId", userId);
			sql += " AND user_id=:userId ";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public UserClubMap get(int userId, int clubId) {
		String sql = "SELECT * FROM user_club_map WHERE user_id=? AND club_id=?";
		List<UserClubMap> list = jdbcTemplate.query(sql, rowMapper, userId, clubId);
		UserClubMap result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public boolean updateDepartment(int userId, int clubId, int departmentId) {
		String sql = "UPDATE user_club_map SET department_id=? WHERE user_id=? AND club_id=? ";
		return this.jdbcTemplate.update(sql, departmentId, userId, clubId) > 0;
	}

	@Override
	public boolean updatePosition(int userId, int clubId, int positionId) {
		String sql = "UPDATE user_club_map SET position_id=? WHERE user_id=? AND club_id=? ";
		return this.jdbcTemplate.update(sql, positionId, userId, clubId) > 0;
	}

	@Override
	public boolean updatePremit(int userId, int clubId, int premitId) {
		String sql = "UPDATE user_club_map SET premit_id=? WHERE user_id=? AND club_id=? ";
		return this.jdbcTemplate.update(sql, premitId, userId, clubId) > 0;
	}

	@Override
	public boolean delete(int userId, int clubId) {
		String sql = "DELETE FROM user_club_map WHERE user_id=? AND club_id=? ";
		return this.jdbcTemplate.update(sql, userId, clubId) > 0;
	}

	@Override
	public int countPremit(int clubId, int premitId) {
		Map<String, Object> args = new HashMap<>();
		args.put("clubId", clubId);
		args.put("premitId", premitId);
		String sql = "SELECT COUNT(1) FROM user_club_map WHERE 1=1 AND club_id=:clubId AND premit_id=:premitId ";
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public List<UserClubMap> listMember(int clubId, Integer departmentId, Integer positionId, String keyword, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		args.put("start", start);
		args.put("size", size);
		args.put("clubId", clubId);
		String userSql = " SELECT id FROM user WHERE del=0 AND id=user_club_map.user_id ";
		if (StringUtils.isNotBlank(keyword)) {
			args.put("keyword", "%" + keyword + "%");
			userSql += " AND actual_name LIKE :keyword ";
		}
		String sql = "SELECT * FROM user_club_map WHERE 1=1 AND club_id=:clubId AND EXISTS (" + userSql + ") ";
		if (null != departmentId) {
			args.put("departmentId", departmentId);
			sql += " AND department_id=:departmentId ";
		}
		if (null != positionId) {
			args.put("positionId", positionId);
			sql += " AND position_id=:positionId ";
		}
		sql += " ORDER BY posttime LIMIT :start,:size ";
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public int countMember(int clubId, Integer departmentId, Integer positionId, String keyword) {
		Map<String, Object> args = new HashMap<>();
		args.put("clubId", clubId);
		String userSql = " SELECT id FROM user WHERE del=0 AND id=user_club_map.user_id ";
		if (StringUtils.isNotBlank(keyword)) {
			args.put("keyword", "%" + keyword + "%");
			userSql += " AND actual_name LIKE :keyword ";
		}
		String sql = "SELECT COUNT(1) FROM user_club_map WHERE 1=1 AND club_id=:clubId AND EXISTS (" + userSql + ") ";
		if (null != departmentId) {
			args.put("departmentId", departmentId);
			sql += " AND department_id=:departmentId ";
		}
		if (null != positionId) {
			args.put("positionId", positionId);
			sql += " AND position_id=:positionId ";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

}
