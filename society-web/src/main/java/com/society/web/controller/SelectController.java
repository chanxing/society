package com.society.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.handler.ClubHandler;
import com.society.vo.IdNameVO;
import com.society.web.base.BaseController;
import com.society.web.base.TableId;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/")
public class SelectController extends BaseController {
	Logger logger = LoggerFactory.getLogger(SelectController.class);
	private static String[] HOUR_24 = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
	private static String[] MIN_30 = { "00", "30" };

	@Autowired
	private ClubHandler clubHandler;

	/**
	 * 查询一天小时分钟列表<br>
	 * uri:/listHourMin.do
	 * 
	 * @return
	 */
	@RequestMapping("/listHourMin")
	public String listHourMin() {
		logger.info("listHour");
		List<String> records = new ArrayList<>(HOUR_24.length * MIN_30.length);
		for (String h : HOUR_24) {
			for (String m : MIN_30) {
				records.add(h + ":" + m);
			}
		}
		return toListResult(TableId.TEMP_ID, records, records.size());
	}

	/**
	 * 查询社团列表<br>
	 * uri:/listDepartment.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/listDepartment")
	public String listDepartment(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("listDepartment clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		List<IdNameVO<String>> records = this.clubHandler.listDepartment(clubId);
		records.add(0, new IdNameVO<String>(TableId.TEMP_ID + "", "未分配"));
		records.add(0, new IdNameVO<String>("", "全部部门"));
		return toListResult(TableId.TEMP_ID, records, records.size());
	}

	/**
	 * 查询社团职位列表<br>
	 * uri:/listPosition.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/listPosition")
	public String listPosition(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("listPosition clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		List<IdNameVO<String>> records = this.clubHandler.listPosition(clubId);
		records.add(0, new IdNameVO<String>(TableId.TEMP_ID + "", "未分配"));
		records.add(0, new IdNameVO<String>("", "全部职位"));
		return toListResult(TableId.TEMP_ID, records, records.size());
	}
}
