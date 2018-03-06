package com.society.web.controller.deal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.fo.ClubActivityFO;
import com.society.handler.ClubHandler;
import com.society.service.ClubActivityService;
import com.society.util.DateUtil;
import com.society.vo.ClubActivityVO;
import com.society.web.base.BaseController;
import com.society.web.base.TableId;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/deal/clubActivity")
public class ClubActivityDealController extends BaseController {
	Logger logger = LoggerFactory.getLogger(ClubActivityDealController.class);
	private static final int PAGE_SIZE = 10;
	@Autowired
	private ClubHandler clubHandler;
	@Autowired
	private ClubActivityService clubActivityService;

	/**
	 * 活动列表 <br>
	 * uri:/deal/clubActivity/list.do
	 * 
	 * @param clubId
	 * @param startDate
	 * @param endDate
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Integer clubId, Integer type, String startDate, String endDate, Integer page, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("submit operator[{}], clubId[{}], type[{}], startDate[{}], endDate[{}], page[{}]", new Object[] { operator, clubId, type, startDate, endDate, page });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidPage(page);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		Long startDateInt = null;
		Long endDateInt = null;
		if (StringUtils.isNotBlank(startDate)) {
			startDateInt = DateUtil.minstr2Int(startDate);
		} else {
			// 6天前
			// String dayBeforeStr = DateUtil.getDayBeforeStr(6);
			// long dateBefore = DateUtil.str2Int(dayBeforeStr);
			// startDateInt = dateBefore * 10000;
		}
		if (StringUtils.isNotBlank(endDate)) {
			endDateInt = DateUtil.minstr2Int(endDate);
		} else {
			// String todayStr = DateUtil.getTodayDayStr();
			// long todayInt = DateUtil.str2Int(todayStr);
			// endDateInt = todayInt * 10000 + 23 * 100 + 59;
		}
		int count = clubActivityService.count(clubId, type, startDateInt, endDateInt);
		List<ClubActivityVO> records = clubHandler.listActivity(clubId, type, startDateInt, endDateInt, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 提交活动<br>
	 * uri:/deal/clubActivity/submit.do
	 * 
	 * @param fo
	 * @param session
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(ClubActivityFO fo, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("submit operator[{}], ClubActivityFO[{}]", new Object[] { operator, fo });
		CheckUtil.isValidClubActivityFO(fo);
		boolean result = clubHandler.submitActivity(fo, operator);
		return toBooleanResult(result);
	}

	/**
	 * 删除活动<br>
	 * uri:/deal/clubActivity/delete.do
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("submit operator[{}], id[{}]", new Object[] { operator, id });
		CheckUtil.isValidId(id);
		boolean result = clubActivityService.delete(id);
		return toBooleanResult(result);
	}
}
