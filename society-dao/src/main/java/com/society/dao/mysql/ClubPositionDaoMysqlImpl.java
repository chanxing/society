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

import com.society.constant.DelConstant;
import com.society.dao.ClubPositionDao;
import com.society.model.ClubPosition;

@Repository
public class ClubPositionDaoMysqlImpl implements ClubPositionDao {

	private final RowMapper<ClubPosition> rowMapper = new BeanPropertyRowMapper<ClubPosition>(ClubPosition.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public Integer add(final String name, final int clubId) {
		final String sql = "INSERT INTO club_position (name,club_id) VALUES(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setInt(2, clubId);
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
	public List<ClubPosition> list(Integer clubId) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT * FROM club_position WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			sql += " AND club_id=:clubId";
		}
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public ClubPosition get(Integer id) {
		String sql = "SELECT * FROM club_position WHERE del=0 AND id=?";
		List<ClubPosition> list = jdbcTemplate.query(sql, rowMapper, id);
		ClubPosition result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public boolean update(int id, String name) {
		String sql = "UPDATE club_position SET name=? WHERE id=?";
		return this.jdbcTemplate.update(sql, name, id) > 0;
	}

	@Override
	public boolean delete(int id) {
		String sql = "UPDATE club_position SET del=? WHERE id=?";
		return this.jdbcTemplate.update(sql, DelConstant.DEL, id) > 0;
	}
}
