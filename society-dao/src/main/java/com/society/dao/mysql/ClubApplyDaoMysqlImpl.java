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

import com.society.constant.ClubApplyStatusEnum;
import com.society.dao.ClubApplyDao;
import com.society.model.ClubApply;

@Repository
public class ClubApplyDaoMysqlImpl implements ClubApplyDao {
	private final RowMapper<ClubApply> rowMapper = new BeanPropertyRowMapper<ClubApply>(ClubApply.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public boolean add(int userId, int clubId) {
		String sql = "INSERT INTO club_apply (user_id,club_id,`status`,posttime,lmodify) VALUES(?,?,?,now(),now())";
		return jdbcTemplate.update(sql, userId, clubId, ClubApplyStatusEnum.APPLYING.getId()) > 0;
	}

	@Override
	public List<ClubApply> list(Integer clubId, Integer status, String keyword, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		args.put("start", start);
		args.put("size", size);
		String sql = "SELECT * FROM club_apply WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		if (null != status) {
			args.put("status", status);
			sql += " AND status=:status ";
		}
		if (StringUtils.isNotBlank(keyword)) {
			args.put("keyword", "%" + keyword + "%");
			sql += " AND actual_name LIKE :keyword ";
		}
		sql += " ORDER BY id LIMIT :start,:size";
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public int count(Integer clubId, Integer status, String keyword) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT COUNT(1) FROM club_apply WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		if (null != status) {
			args.put("status", status);
			sql += " AND status=:status ";
		}
		if (StringUtils.isNotBlank(keyword)) {
			args.put("keyword", "%" + keyword + "%");
			sql += " AND actual_name LIKE :keyword ";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public boolean pass(int userId, int clubId) {
		String sql = "UPDATE club_apply SET status=? WHERE user_id=? AND club_id=? AND status=? ";
		return this.jdbcTemplate.update(sql, ClubApplyStatusEnum.PASS.getId(), userId, clubId, ClubApplyStatusEnum.APPLYING.getId()) > 0;
	}

	@Override
	public boolean reject(int userId, int clubId) {
		String sql = "UPDATE club_apply SET status=? WHERE user_id=? AND club_id=? AND status=? ";
		return this.jdbcTemplate.update(sql, ClubApplyStatusEnum.REJECT.getId(), userId, clubId, ClubApplyStatusEnum.APPLYING.getId()) > 0;
	}

	@Override
	public ClubApply getLast(int userId, int clubId, int status) {
		String sql = "SELECT * FROM club_apply WHERE del=0 AND user_id=? AND club_id=? AND status=? ORDER BY id DESC LIMIT 0,1";
		List<ClubApply> list = jdbcTemplate.query(sql, rowMapper, userId, clubId, status);
		ClubApply result = null;
		if (list.isEmpty() == false) {
			result = list.get(0);
		}
		return result;
	}

}
