package com.society.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.society.dao.VerificationCodeDao;
import com.society.model.VerificationCode;
import com.society.util.DateUtil;

@Repository
public class VerificationCodeDaoMysqlImpl implements VerificationCodeDao {
	private final RowMapper<VerificationCode> rowMapper = new BeanPropertyRowMapper<VerificationCode>(VerificationCode.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(String code, int type, String acceptor, Date invalidTime) {
		String todayStr = DateUtil.getTodayDayStr2();
		int sectionInt = Integer.parseInt(todayStr);
		String sql = " INSERT INTO verification_code (`code`,`type`,`acceptor`,`invalid_time`,`del`,`lmodify`,`section_int`,`posttime`) VALUES(?,?,?,?,0,now(),?,now()) ";
		int count = jdbcTemplate.update(sql, code, type, acceptor, invalidTime, sectionInt);
		return count > 0;
	}

	@Override
	public boolean invalidCodes(int type, String acceptor) {
		String sql = " UPDATE verification_code SET del=1,`lmodify`=now() WHERE del=0 AND `type`=? AND `acceptor`=? ";
		int count = jdbcTemplate.update(sql, type, acceptor);
		return count > 0;
	}

	@Override
	public VerificationCode getLastest(int type, String acceptor) {
		String sql = " SELECT * FROM verification_code WHERE del=0 AND `type`=? AND `acceptor`=? ORDER BY `posttime` DESC LIMIT 0,1 ";
		List<VerificationCode> records = jdbcTemplate.query(sql, rowMapper, type, acceptor);
		VerificationCode result = null;
		if (null != records && !records.isEmpty()) {
			result = records.get(0);
		}
		return result;
	}

	@Override
	public int countBySectionInt(int sectionInt, String acceptor) {
		String sql = " SELECT COUNT(1) FROM `verification_code`  WHERE section_int=? AND acceptor=? ";
		return jdbcTemplate.queryForObject(sql, Integer.class, sectionInt, acceptor);
	}

}
