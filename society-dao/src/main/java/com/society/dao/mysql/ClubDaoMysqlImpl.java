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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.society.dao.ClubDao;
import com.society.model.Club;

public class ClubDaoMysqlImpl implements ClubDao {
	private final RowMapper<Club> rowMapper = new BeanPropertyRowMapper<Club>(Club.class);
	// private final String tableName =
	// StringUtil.humpToUnderline(UserDaoMysqlImpl.class.getSimpleName());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer add(final String name, final String logo, final String introduction, final int type, final int level, final int creater) {
		final String sql = "INSERT INTO club (name,logo,introduction,type,level,creater) VALUES(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, logo);
				ps.setString(3, introduction);
				ps.setInt(4, type);
				ps.setInt(5, level);
				ps.setInt(6, creater);
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
	public Club get(Integer id) {
		String sql = "SELECT * FROM club WHERE id=? ";
		List<Club> list = jdbcTemplate.query(sql, rowMapper, id);
		Club result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public List<Club> list(String keyword, Integer type, Integer level) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT * FROM club WHERE 1=1 ";
		if (null != keyword) {
			args.put("keyword", "%" + keyword + "%");
			sql += " AND name LIKE :keyword ";
		}
		if (null != type) {
			args.put("type", type);
			sql += " AND type=:type ";
		}
		if (null != level) {
			args.put("level", level);
			sql += " AND level=:level ";
		}
		return jdbcTemplate.query(sql, rowMapper, args);
	}

}
