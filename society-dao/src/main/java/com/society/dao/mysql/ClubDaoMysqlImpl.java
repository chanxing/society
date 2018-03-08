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
import com.society.dao.ClubDao;
import com.society.model.Club;

@Repository
public class ClubDaoMysqlImpl implements ClubDao {
	private final RowMapper<Club> rowMapper = new BeanPropertyRowMapper<Club>(Club.class);
	// private final String tableName =
	// StringUtil.humpToUnderline(UserDaoMysqlImpl.class.getSimpleName());

	/** 创建时间倒序 **/
	private int SORT_TYPE_POSTTIME_DESC = 1;
	/** 人数倒序 **/
	private int SORT_TYPE_SCALE_DESC = 2;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public Integer add(final String name, final String logo, final String section, final int type, final int level, final int creater) {
		final String sql = "INSERT INTO club (name,logo,section,type,level,creater,posttime,lmodify) VALUES(?,?,?,?,?,?,now(),now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, name);
				ps.setString(2, logo);
				ps.setString(3, section);
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
	public List<Club> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size) {
		Map<String, Object> args = new HashMap<>();
		args.put("start", start);
		args.put("size", size);
		String sql = "SELECT * FROM club WHERE del=0 ";
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

		if (null != sortType && sortType.intValue() == SORT_TYPE_POSTTIME_DESC) {
			sql += " ORDER BY posttime DESC,id  ";
		} else if (null != sortType && sortType.intValue() == SORT_TYPE_SCALE_DESC) {
			String userCoutSql = "SELECT club_id, COUNT(1) AS num FROM user_club_map GROUP BY club_id ";
			String joinSql = "SELECT c.*,IFNULL(m.num,0) AS num FROM (" + sql + ") AS c LEFT JOIN (" + userCoutSql + ") AS m ON (c.id=m.club_id) ";
			sql = "SELECT * FROM (" + joinSql + ") AS j ORDER BY j.num DESC,j.id ";
		} else {
			sql += "ORDER  BY level,id ";
		}
		sql += " LIMIT :start,:size";
		return nameJdbcTemplate.query(sql, args, rowMapper);
	}

	@Override
	public boolean update(int clubId, String name, String logo, String introduction, String announcement, int type, int level) {
		String sql = "UPDATE club SET name=?,logo=?,introduction=?,announcement=?,type=?,level=?,lmodify=now() WHERE id=?";
		return jdbcTemplate.update(sql, name, logo, introduction, announcement, type, level, clubId) > 0;
	}

	@Override
	public boolean delete(int clubId) {
		String sql = "UPDATE club SET del=? WHERE id=?";
		return jdbcTemplate.update(sql, DelConstant.DEL, clubId) > 0;
	}

	@Override
	public int count(String keyword, Integer type, Integer level) {
		Map<String, Object> args = new HashMap<>();
		String sql = "SELECT COUNT(1) FROM club WHERE 1=1 ";
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
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

}
