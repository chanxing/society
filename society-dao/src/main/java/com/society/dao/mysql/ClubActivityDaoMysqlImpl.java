package com.society.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.society.dao.ClubActivityDao;
import com.society.model.ClubActivity;

@Repository
public class ClubActivityDaoMysqlImpl implements ClubActivityDao {
	private final RowMapper<ClubActivity> rowMapper = new BeanPropertyRowMapper<ClubActivity>(ClubActivity.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public Integer add(final int clubId, final int type, final String title, final String startDate, final long startDateInt, final String endDate, final long endDateInt, final String place,
			final String poster, final String description, final int operator) {
		final String sql = "INSERT INTO club_activity (club_id,type,title,start_date,start_date_int,end_date,end_date_int,place,poster,description,operator,posttime,lmodify) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, clubId);
				ps.setInt(2, type);
				ps.setString(3, title);
				ps.setString(4, startDate);
				ps.setLong(5, startDateInt);
				ps.setString(6, endDate);
				ps.setLong(7, endDateInt);
				ps.setString(8, place);
				ps.setString(9, poster);
				ps.setString(10, description);
				ps.setInt(11, operator);
				return ps;
			}

		};
		jdbcTemplate.update(psc, keyHolder);
		Number id = keyHolder.getKey();
		if (id != null) {
			return id.intValue();
		}
		return null;
	}

	@Override
	public boolean update(int id, int clubId, int type, String title, String startDate, long startDateInt, String endDate, long endDateInt, String place, String poster, String description) {
		String sql = "UPDATE club_activity SET club_id=?,type=?,title=?,start_date=?,start_date_int=?,end_date=?,end_date_int=?,place=?,poster=?,description=?,lmodify=now() WHERE id=? ";
		return this.jdbcTemplate.update(sql, clubId, type, title, startDate, startDateInt, endDate, endDateInt, place, poster, description, id) > 0;
	}

	@Override
	public ClubActivity get(Integer id) {
		String sql = "SELECT * FROM club_activity WHERE id=? ";
		List<ClubActivity> list = this.jdbcTemplate.query(sql, rowMapper, id);
		ClubActivity result = null;
		if (list.isEmpty() == false) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public List<ClubActivity> list(Integer clubId, Integer type, Long startDateInt, Long endDateInt, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		args.put("start", start);
		args.put("size", size);
		String sql = "SELECT * FROM club_activity WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		if (null != type) {
			args.put("type", type);
			sql += " AND type=:type ";
		}
		if (null != startDateInt) {
			args.put("startDateInt", startDateInt);
			sql += " AND start_date_int>=:startDateInt ";
		}
		if (null != endDateInt) {
			args.put("endDateInt", endDateInt);
			sql += " AND end_date_int<=:endDateInt ";
		}
		sql += " ORDER BY id LIMIT :start,:size ";
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public int count(Integer clubId, Integer type, Long startDateInt, Long endDateInt) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT COUNT(1) FROM club_activity WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId ";
		}
		if (null != type) {
			args.put("type", type);
			sql += " AND type=:type ";
		}
		if (null != startDateInt) {
			args.put("startDateInt", startDateInt);
			sql += " AND start_date_int>=:startDateInt ";
		}
		if (null != endDateInt) {
			args.put("endDateInt", endDateInt);
			sql += " AND end_date_int>=:endDateInt ";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

	@Override
	public boolean delete(Integer id) {
		String sql = " UPDATE club_activity SET del=1 WHERE id=?";
		return this.jdbcTemplate.update(sql, id) > 0;
	}

}
