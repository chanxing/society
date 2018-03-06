package com.society.web.controller.unlimited;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.handler.ClubHandler;
import com.society.model.Club;
import com.society.service.ClubService;
import com.society.util.exception.ProjectException;
import com.society.vo.ClubDynamicVO;
import com.society.vo.ClubIndexDetailVO;
import com.society.web.base.BaseController;
import com.society.web.base.TableId;
import com.society.web.check.CheckUtil;

/**
 * 社团
 * 
 * @author beyond
 *
 */
@RestController
@RequestMapping("/unlimited/club")
public class ClubUnlimitedController extends BaseController {
	Logger logger = LoggerFactory.getLogger(ClubUnlimitedController.class);
	private static final int PAGE_SIZE = 10;
	@Autowired
	private ClubHandler clubHandler;
	@Autowired
	private ClubService clubService;

	/**
	 * 社团动态列表<br>
	 * uri:/unlimited/club/list.do
	 * 
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Integer page, Integer sort, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("list userId[{}], page[{}], sort[{}]", new Object[] { page, userId, sort });
		CheckUtil.isValidPage(page);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		int count = clubService.count(null, null, null);
		List<ClubDynamicVO> records = clubHandler.list(null, null, null, sort, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 社团详情<br>
	 * uri:/unlimited/club/get.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/get")
	public String get(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("get clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		Club club = clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		ClubIndexDetailVO result = this.clubHandler.getDetail(clubId, userId);
		return toRecordResult(result);
	}

}
