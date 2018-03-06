package com.society.web.controller.deal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.constant.ClubApplyStatusEnum;
import com.society.handler.UserHandler;
import com.society.model.Club;
import com.society.model.ClubApply;
import com.society.model.User;
import com.society.model.UserClubMap;
import com.society.service.ClubApplyService;
import com.society.service.ClubService;
import com.society.service.UserClubMapService;
import com.society.service.UserService;
import com.society.util.exception.ProjectException;
import com.society.vo.ClubApplyVO;
import com.society.web.base.BaseController;
import com.society.web.base.TableId;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/deal/clubApply")
public class ClubApplyDealController extends BaseController {
	Logger logger = LoggerFactory.getLogger(ClubApplyDealController.class);
	private static final int PAGE_SIZE = 10;

	@Autowired
	private UserHandler userHandler;
	@Autowired
	private ClubApplyService clubApplyService;
	@Autowired
	private ClubService clubService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserClubMapService userClubMapService;

	/**
	 * 社团加入申请列表<br>
	 * uri:/deal/clubApply/list.do
	 * 
	 * @param clubId
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("list")
	public String list(Integer clubId, Integer page, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("list userId[{}], clubId[{}]", new Object[] { userId, clubId });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidPage(page);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		int count = userHandler.countClubApplyUser(clubId);
		List<ClubApplyVO> records = userHandler.listClubApplyUser(clubId, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 通过 <br>
	 * uri:/deal/clubApply/audit.do
	 * 
	 * @param userId
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "audit", params = { "isAgree=true" })
	public String pass(Integer userId, Integer clubId, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("pass operator[{}], userId[{}], clubId[{}]", new Object[] { operator, userId, clubId });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidUserId(userId);
		User user = userService.get(userId);
		if (null == user) {
			throw new ProjectException("用户不存在");
		}
		Club club = clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		boolean result = clubApplyService.pass(userId, clubId);
		return toBooleanResult(result);
	}

	/**
	 * 拒绝 <br>
	 * uri:/deal/clubApply/audit.do
	 * 
	 * @param userId
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "audit", params = { "isAgree=false" })
	public String reject(Integer userId, Integer clubId, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("reject operator[{}], userId[{}], clubId[{}]", new Object[] { operator, userId, clubId });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidUserId(userId);
		User user = userService.get(userId);
		if (null == user) {
			throw new ProjectException("用户不存在");
		}
		Club club = clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		boolean result = clubApplyService.reject(userId, clubId);
		return toBooleanResult(result);
	}

	/**
	 * 申请加入社团 <br>
	 * uri:/deal/clubApply/apply.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "apply")
	public String apply(Integer clubId, HttpSession session) {
		Integer operator = this.getUserId(session);
		logger.info("apply operator[{}], clubId[{}]", new Object[] { operator, clubId });
		CheckUtil.isValidClubId(clubId);
		Club club = clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		UserClubMap map = userClubMapService.get(operator, clubId);
		if (null == map) {
			throw new ProjectException("已加入，不能重复申请");
		}
		ClubApply apply = clubApplyService.getLast(operator, clubId, ClubApplyStatusEnum.APPLYING.getId());
		if (null != apply) {
			throw new ProjectException("已申请，请勿重复申请");
		}
		boolean result = clubApplyService.add(operator, clubId);
		return toBooleanResult(result);
	}
}
