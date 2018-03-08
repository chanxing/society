package com.society.web.controller.deal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.constant.ClubPremitEnum;
import com.society.constant.RoleTypeEnum;
import com.society.fo.ClubFO;
import com.society.handler.ClubHandler;
import com.society.model.Club;
import com.society.model.ClubDepartment;
import com.society.model.ClubPosition;
import com.society.model.User;
import com.society.model.UserClubMap;
import com.society.service.ClubDepartmentService;
import com.society.service.ClubPositionService;
import com.society.service.ClubService;
import com.society.service.UserClubMapService;
import com.society.service.UserService;
import com.society.util.exception.ProjectException;
import com.society.vo.ClubListVO;
import com.society.vo.ClubMemberVO;
import com.society.vo.ClubPremitVO;
import com.society.vo.ClubVO;
import com.society.vo.IdNameVO;
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
@RequestMapping("/deal/club")
public class ClubDealController extends BaseController {
	Logger logger = LoggerFactory.getLogger(ClubDealController.class);
	private static final int PAGE_SIZE = 10;
	@Autowired
	private ClubHandler clubHandler;
	@Autowired
	private UserClubMapService userClubMapService;
	@Autowired
	private ClubService clubService;
	@Autowired
	private ClubDepartmentService clubDepartmentService;
	@Autowired
	private ClubPositionService clubPositionService;
	@Autowired
	private UserService userService;

	/**
	 * 我的社团列表<br>
	 * uri:/deal/club/listMyClub.do
	 * 
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/listMyClub")
	public String listMyClub(Integer page, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("listMyClub page[{}], userId[{}]", new Object[] { page, userId });
		CheckUtil.isValidPage(page);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		int count = userClubMapService.count(userId);
		List<ClubListVO> records = clubHandler.list(userId, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 社团详情<br>
	 * uri:/deal/club/get.do
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
		ClubVO result = this.clubHandler.get(clubId);
		return toRecordResult(result);
	}

	/**
	 * 提交社团信息<br>
	 * uri:/deal/club/submit.do
	 * 
	 * @param fo
	 * @param session
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(ClubFO fo, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("submit fo[{}], userId[{}]", new Object[] { fo, userId });
		if (null != fo.getClubId()) {
			Club club = this.clubService.get(fo.getClubId());
			if (null == club) {
				throw new ProjectException("社团不存在");
			}
		}
		boolean result = this.clubHandler.submit(fo, userId);
		return toBooleanResult(result);
	}

	/**
	 * 解散社团<br>
	 * uri:/deal/club/delete.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("delete clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		Club club = this.clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		UserClubMap userClubMap = userClubMapService.get(userId, clubId);
		boolean canoperate = false;
		if (null != userClubMap) {
			canoperate = ClubPremitEnum.ADMIN.getId() == userClubMap.getPremitId();
		}
		if (false == canoperate) {
			User user = userService.get(userId);
			canoperate = null != user && user.getRole() == RoleTypeEnum.ADMIN.getId();
		}
		if (false == canoperate) {
			throw new ProjectException("没有权限删除");
		}
		boolean result = clubService.delete(clubId);
		return toBooleanResult(result);
	}

	/**
	 * 提交社团部门信息<br>
	 * uri:/deal/club/submitDepartment.do
	 * 
	 * @param departmentId
	 * @param clubId
	 * @param name
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitDepartment")
	public String submitDepartment(Integer departmentId, Integer clubId, String name, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("submitDepartment departmentId[{}], clubId[{}], name[{}], userId[{}]", new Object[] { departmentId, clubId, name, userId });
		CheckUtil.isValidName(name);
		if (null != departmentId) {
			ClubDepartment depart = clubDepartmentService.get(departmentId);
			if (null == depart) {
				throw new ProjectException("部门不存在");
			}
		} else {
			CheckUtil.isValidClubId(clubId);
			Club club = this.clubService.get(clubId);
			if (null == club) {
				throw new ProjectException("社团不存在");
			}
		}
		boolean result = this.clubHandler.submitDepartment(departmentId, clubId, name);
		return toBooleanResult(result);
	}

	/**
	 * 提交社团职位信息<br>
	 * uri:/deal/club/submitPosition.do
	 * 
	 * @param positionId
	 * @param clubId
	 * @param name
	 * @param session
	 * @return
	 */
	@RequestMapping("/submitPosition")
	public String submitPosition(Integer positionId, Integer clubId, String name, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("submitPosition positionId[{}], clubId[{}], name[{}], userId[{}]", new Object[] { positionId, clubId, name, userId });
		CheckUtil.isValidName(name);
		if (null != positionId) {
			ClubPosition post = clubPositionService.get(positionId);
			if (null == post) {
				throw new ProjectException("职位不存在");
			}
		} else {
			CheckUtil.isValidClubId(clubId);
			Club club = this.clubService.get(clubId);
			if (null == club) {
				throw new ProjectException("社团不存在");
			}
		}
		boolean result = this.clubHandler.submitPosition(positionId, clubId, name);
		return toBooleanResult(result);
	}

	/**
	 * 查询社团列表<br>
	 * uri:/deal/club/listDepartment.do
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
		// records.add(0, new IdNameVO<Integer>(TableId.TEMP_ID, "全部部门"));
		return toListResult(TableId.TEMP_ID, records, records.size());
	}

	/**
	 * 查询社团职位列表<br>
	 * uri:/deal/club/listPosition.do
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
		// records.add(0, new IdNameVO<Integer>(TableId.TEMP_ID, "全部职位"));
		return toListResult(TableId.TEMP_ID, records, records.size());
	}

	/**
	 * 删除社团部门<br>
	 * uri:/deal/club/deleteDepartment.do
	 * 
	 * @param id
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteDepartment")
	public String deleteDepartment(Integer id, Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("deleteDepartment id[{}], clubId[{}], userId[{}]", new Object[] { id, clubId, userId });
		CheckUtil.isValidId(id);
		CheckUtil.isValidClubId(clubId);
		ClubDepartment depart = clubDepartmentService.get(id);
		if (null == depart) {
			throw new ProjectException("部门不存在");
		}
		if (depart.getClubId() != clubId) {
			throw new ProjectException("社团ID不正确");
		}
		boolean result = clubDepartmentService.delete(id);
		return toBooleanResult(result);
	}

	/**
	 * 删除社团职位<br>
	 * uri:/deal/club/deletePosition.do
	 * 
	 * @param id
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/deletePosition")
	public String deletePosition(Integer id, Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("deletePosition id[{}], clubId[{}], userId[{}]", new Object[] { id, clubId, userId });
		CheckUtil.isValidId(id);
		CheckUtil.isValidClubId(clubId);
		ClubPosition post = clubPositionService.get(id);
		if (null == post) {
			throw new ProjectException("职位不存在");
		}
		if (post.getClubId() != clubId) {
			throw new ProjectException("社团ID不正确");
		}
		boolean result = clubPositionService.delete(id);
		return toBooleanResult(result);
	}

	/**
	 * 分配成员的社团部门<br>
	 * uri:/deal/club/distributeDepartment.do
	 * 
	 * @param clubId
	 * @param id
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/distributeDepartment")
	public String distributeDepartment(Integer clubId, Integer id, Integer userId, HttpSession session) {
		Integer operater = this.getUserId(session);
		logger.info("distributeDepartment id[{}], clubId[{}], userId[{}], operater[{}]", new Object[] { id, clubId, userId, operater });
		CheckUtil.isValidId(id);
		CheckUtil.isValidClubId(clubId);
		ClubDepartment depart = clubDepartmentService.get(id);
		if (null == depart) {
			throw new ProjectException("部门不存在");
		}
		if (depart.getClubId() != clubId) {
			throw new ProjectException("社团ID不正确");
		}
		boolean result = userClubMapService.updateDepartment(userId, clubId, id);
		return toBooleanResult(result);
	}

	/**
	 * 分配成员的社团职位<br>
	 * uri:/deal/club/distributePosition.do
	 * 
	 * @param clubId
	 * @param id
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/distributePosition")
	public String distributePosition(Integer clubId, Integer id, Integer userId, HttpSession session) {
		Integer operater = this.getUserId(session);
		logger.info("distributePosition id[{}], clubId[{}], userId[{}], operater[{}]", new Object[] { id, clubId, userId, operater });
		CheckUtil.isValidId(id);
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidUserId(userId);
		ClubPosition post = clubPositionService.get(id);
		if (null == post) {
			throw new ProjectException("职位不存在");
		}
		if (post.getClubId() != clubId) {
			throw new ProjectException("社团ID不正确");
		}
		boolean result = userClubMapService.updatePosition(userId, clubId, id);
		return toBooleanResult(result);
	}

	/**
	 * 分配成员的社团权限<br>
	 * uri:/deal/club/distributePremit.do
	 * 
	 * @param clubId
	 * @param premit
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/distributePremit")
	public String distributePremit(Integer clubId, Integer premit, Integer userId, HttpSession session) {
		Integer operater = this.getUserId(session);
		logger.info("distributePremit premit[{}], clubId[{}], userId[{}], operater[{}]", new Object[] { premit, clubId, userId, operater });
		CheckUtil.isValidPremitId(premit);
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidUserId(userId);
		UserClubMap userMap = userClubMapService.get(operater, clubId);
		if (userMap.getPremitId() != ClubPremitEnum.ADMIN.getId()) {
			throw new ProjectException("操作权限不足");
		}
		if (operater.intValue() == userId.intValue() && premit.intValue() != ClubPremitEnum.ADMIN.getId()) {
			// 更改自己的权限
			int count = userClubMapService.countPremit(clubId, ClubPremitEnum.ADMIN.getId());
			if (count <= 1) {
				throw new ProjectException("请先移交管理权限");
			}
		}
		boolean result = userClubMapService.updatePremit(userId, clubId, premit);
		if (result == true && operater.intValue() != userId.intValue() && premit.intValue() == ClubPremitEnum.ADMIN.getId()) {
			userClubMapService.updatePremit(operater, clubId, ClubPremitEnum.GENERAL_MANAGER.getId());
		}
		return toBooleanResult(result);
	}

	/**
	 * 删除社团成员 <br>
	 * uri:/deal/club/deleteMember.do
	 * 
	 * @param clubId
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteMember")
	public String deleteMember(Integer clubId, Integer userId, HttpSession session) {
		Integer operater = this.getUserId(session);
		logger.info("distributePremit clubId[{}], userId[{}], operater[{}]", new Object[] { clubId, userId, operater });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidUserId(userId);
		UserClubMap userMap = userClubMapService.get(operater, clubId);
		if (userMap.getPremitId() != ClubPremitEnum.ADMIN.getId()) {
			throw new ProjectException("操作权限不足");
		}
		if (operater.intValue() == userId.intValue()) {
			int count = userClubMapService.countPremit(clubId, ClubPremitEnum.ADMIN.getId());
			if (count <= 1) {
				throw new ProjectException("请先移交管理权限");
			}
		}
		boolean result = userClubMapService.delete(userId, clubId);
		return toBooleanResult(result);
	}

	/**
	 * 查询社团成员列表 <br>
	 * uri:/deal/club/listMember.do
	 * 
	 * @param clubId
	 * @param clubDepartment
	 * @param position
	 * @param keyword
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/listMember")
	public String listMember(Integer clubId, Integer clubDepartment, Integer position, String keyword, Integer page, HttpSession session) {
		Integer operater = this.getUserId(session);
		logger.info("listMember clubId[{}], clubDepartment[{}], position[{}], keyword[{}], operater[{}]", new Object[] { clubId, clubDepartment, position, keyword, operater });
		CheckUtil.isValidPage(page);
		CheckUtil.isValidClubId(clubId);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		int count = userClubMapService.countMember(clubId, clubDepartment, position, keyword);
		List<ClubMemberVO> records = clubHandler.listMember(clubId, clubDepartment, position, keyword, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 退出社团 <br>
	 * uri:/deal/club/quitClub.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/quitClub")
	public String quitClub(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("listMember clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		UserClubMap userMap = userClubMapService.get(userId, clubId);
		if (null == userMap) {
			return toBooleanResult(true);
		}
		if (userMap.getPremitId() == ClubPremitEnum.ADMIN.getId()) {
			int count = userClubMapService.countPremit(clubId, ClubPremitEnum.ADMIN.getId());
			if (count <= 1) {
				throw new ProjectException("请先移交管理权限");
			}
		}
		boolean result = userClubMapService.delete(userId, clubId);
		return toBooleanResult(result);
	}

	/**
	 * 获取我在社团的权限 <br>
	 * uri:/deal/club/getMyClubPremit.do
	 * 
	 * @param clubId
	 * @param session
	 * @return
	 */
	@RequestMapping("/getMyClubPremit")
	public String getMyClubPremit(Integer clubId, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("listMember clubId[{}], userId[{}]", new Object[] { clubId, userId });
		CheckUtil.isValidClubId(clubId);
		Club club = clubService.get(clubId);
		if (null == club) {
			throw new ProjectException("社团不存在");
		}
		UserClubMap userMap = userClubMapService.get(userId, clubId);
		Integer premitId = TableId.TEMP_ID;
		if (null != userMap) {
			premitId = userMap.getPremitId();
		}
		// String premitName = ClubPremitEnum.getName(premitId);
		ClubPremitVO result = new ClubPremitVO(premitId, club.getName());
		return toRecordResult(result);
	}

}
