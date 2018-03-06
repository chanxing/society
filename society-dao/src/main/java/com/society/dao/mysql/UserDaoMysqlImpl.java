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

import com.society.dao.UserDao;
import com.society.model.User;

@Repository
public class UserDaoMysqlImpl implements UserDao {
	private final RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
	// private final String tableName =
	// StringUtil.humpToUnderline(UserDaoMysqlImpl.class.getSimpleName());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Override
	public Integer add(final String account, final String username, final String encryptPassword, final String salt) {
		final String sql = "INSERT INTO user (account,username,encrypt_password,salt,posttime,lmodify) VALUES(?,?,?,?,now(),now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, account);
				ps.setString(2, username);
				ps.setString(3, encryptPassword);
				ps.setString(4, salt);
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
	public User get(Integer id) {
		String sql = "SELECT * FROM user WHERE id=? ";
		List<User> list = jdbcTemplate.query(sql, rowMapper, id);
		User result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public User getByAccount(String account) {
		String sql = "SELECT * FROM user WHERE account=? ";
		List<User> list = jdbcTemplate.query(sql, rowMapper, account);
		User result = null;
		if (!list.isEmpty()) {
			result = list.get(0);
		}
		return result;
	}

	@Override
	public boolean updateImage(int userId, String url) {
		String sql = "UPDATE user SET image=? WHERE id=?";
		return jdbcTemplate.update(sql, url, userId) > 0;
	}

	@Override
	public boolean update(int userId, String username, String actualName, int gender, String birthday, String nativePlace, String department, String profession, String grade, String phone,
			String startYear, String qq, String wechat, String hobbies, String selIntroduction, String image) {
		String sql = "UPDATE user SET username=?,actual_name=?,gender=?,birthday=?,native_place=?,department=?,profession=?,grade=?,phone=?,start_year=?,qq=?,wechat=?,hobbies=?,sel_introduction=?,image=?,lmodify=now() WHERE id=?";
		return jdbcTemplate.update(sql, username, actualName, gender, birthday, nativePlace, department, profession, grade, phone, startYear, qq, wechat, hobbies, selIntroduction, image, userId) > 0;
	}

	@Override
	public boolean updatePassword(int userId, String encryptPassword, String salt) {
		String sql = "UPDATE user SET encrypt_password=?,salt=? WHERE id=?";
		return jdbcTemplate.update(sql, encryptPassword, salt, userId) > 0;
	}

	@Override
	public int countByClubId(Integer clubId) {
		Map<String, Object> args = new HashMap<>();
		String sql = " SELECT COUNT(1) FROM user WHERE del=0 ";
		if (null != clubId) {
			args.put("clubId", clubId);
			String userClubMapSql = " SELECT user_id FROM user_club_map WHERE club_id=:clubId ";
			sql += " AND id IN (" + userClubMapSql + ")";
		}
		return nameJdbcTemplate.queryForObject(sql, args, Integer.class);
	}

}
