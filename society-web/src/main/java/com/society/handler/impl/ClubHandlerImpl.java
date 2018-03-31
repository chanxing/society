package com.society.handler.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.society.constant.ClubPremitEnum;
import com.society.constant.RoleTypeEnum;
import com.society.fo.ClubActivityFO;
import com.society.fo.ClubFO;
import com.society.handler.ClubHandler;
import com.society.model.Club;
import com.society.model.ClubActivity;
import com.society.model.ClubDepartment;
import com.society.model.ClubPhoto;
import com.society.model.ClubPosition;
import com.society.model.User;
import com.society.model.UserClubMap;
import com.society.service.ClubActivityService;
import com.society.service.ClubDepartmentService;
import com.society.service.ClubPhotoService;
import com.society.service.ClubPositionService;
import com.society.service.ClubService;
import com.society.service.UserClubMapService;
import com.society.service.UserService;
import com.society.util.DateUtil;
import com.society.vo.ClubActivityVO;
import com.society.vo.ClubDynamicVO;
import com.society.vo.ClubIndexDetailVO;
import com.society.vo.ClubIndexDetailVO.ClubActivityListVO;
import com.society.vo.ClubIndexDetailVO.MemberVO;
import com.society.vo.ClubListVO;
import com.society.vo.ClubMemberVO;
import com.society.vo.ClubPhotoVO;
import com.society.vo.ClubVO;
import com.society.vo.IdNameVO;

@Component
public class ClubHandlerImpl implements ClubHandler {

	@Autowired
	private ClubService clubService;
	@Autowired
	private UserClubMapService userClubMapService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClubDepartmentService clubDepartmentService;
	@Autowired
	private ClubPositionService clubPositionService;
	@Autowired
	private ClubPhotoService clubPhotoService;
	@Autowired
	private ClubActivityService clubActivityService;

	@Override
	public List<ClubListVO> list(Integer userId, int start, int size) {
		if (null == userId) {
			List<Club> clubs = clubService.list(null, null, null, null, start, size);
			List<ClubListVO> clubVos = new ArrayList<>(clubs.size());
			for (Club c : clubs) {
				ClubListVO vo = new ClubListVO();
				vo.setClubId(c.getId());
				vo.setIntroduction(c.getIntroduction());
				vo.setLogo(c.getLogo());
				vo.setName(c.getName());
				Integer premit = ClubPremitEnum.ADMIN.getId();
				String premitName = ClubPremitEnum.getName(premit);
				vo.setPremit(premit);
				vo.setPremitName(premitName);
				clubVos.add(vo);
			}
			return clubVos;
		}
		List<UserClubMap> list = userClubMapService.list(userId, start, size);
		List<ClubListVO> vos = new ArrayList<>(list.size());
		for (UserClubMap map : list) {
			Club c = clubService.get(map.getClubId());
			if (null == c) {
				c = new Club();
			}
			ClubListVO vo = new ClubListVO();
			vo.setClubId(c.getId());
			vo.setIntroduction(c.getIntroduction());
			vo.setLogo(c.getLogo());
			vo.setName(c.getName());
			Integer premit = map.getPremitId();
			String premitName = ClubPremitEnum.getName(premit);
			vo.setPremit(premit);
			vo.setPremitName(premitName);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public int count(Integer userId) {
		if (null == userId) {
			return this.clubService.count(null, null, null);
		}
		return this.userClubMapService.count(userId);
	}

	@Override
	public ClubVO get(Integer clubId) {
		Club club = clubService.get(clubId);
		if (null == club) {
			return null;
		}
		ClubVO vo = new ClubVO();
		vo.setClubId(club.getId());
		vo.setAnnouncement(club.getAnnouncement());
		vo.setIntroduction(club.getIntroduction());
		vo.setLevel(club.getLevel());
		vo.setLogo(club.getLogo());
		vo.setName(club.getName());
		Integer scale = userService.countByClubId(clubId);
		vo.setScale(scale);
		vo.setType(club.getType());
		return vo;
	}

	@Override
	public boolean submit(ClubFO fo, int userId) {
		if (fo.getClubId() == null) {
			Integer clubId = clubService.add(fo.getName(), fo.getLogo(), fo.getType(), fo.getLevel(), userId);
			userClubMapService.add(userId, clubId, 0, 0, ClubPremitEnum.ADMIN.getId());
			return null != clubId;
		}
		boolean result = clubService.update(fo.getClubId(), fo.getName(), fo.getLogo(), fo.getIntroduction(), fo.getAnnouncement(), fo.getType(), fo.getLevel());
		return result;
	}

	@Override
	public boolean submitDepartment(Integer departmentId, Integer clubId, String name) {
		if (null == departmentId) {
			Integer id = this.clubDepartmentService.add(name, clubId);
			return id != null;
		}
		return clubDepartmentService.update(departmentId, name);
	}

	@Override
	public boolean submitPosition(Integer positionId, Integer clubId, String name) {
		if (null == positionId) {
			Integer id = this.clubPositionService.add(name, clubId);
			return id != null;
		}
		return clubPositionService.update(positionId, name);
	}

	@Override
	public List<IdNameVO<String>> listDepartment(Integer clubId) {
		List<ClubDepartment> list = clubDepartmentService.list(clubId);
		List<IdNameVO<String>> result = new ArrayList<>(list.size());
		for (ClubDepartment c : list) {
			result.add(new IdNameVO<String>(c.getId() + "", c.getName()));
		}
		return result;
	}

	@Override
	public List<IdNameVO<String>> listPosition(Integer clubId) {
		List<ClubPosition> list = clubPositionService.list(clubId);
		List<IdNameVO<String>> result = new ArrayList<>(list.size());
		for (ClubPosition c : list) {
			result.add(new IdNameVO<String>(c.getId() + "", c.getName()));
		}
		return result;
	}

	@Override
	public List<ClubMemberVO> listMember(int clubId, Integer departmentId, Integer positionId, String keyword, int start, int size) {
		List<UserClubMap> list = userClubMapService.listMember(clubId, departmentId, positionId, keyword, start, size);
		List<ClubMemberVO> vos = new ArrayList<>(list.size());
		Map<Integer, String> departmentNameMap = new HashMap<>();
		Map<Integer, String> positioNameMap = new HashMap<>();
		for (UserClubMap map : list) {
			User user = userService.get(map.getUserId());
			if (null == user) {
				user = new User();
			}
			ClubMemberVO vo = new ClubMemberVO();
			vo.setActualName(user.getActualName());
			// 部门
			Integer clubDepartmentId = map.getDepartmentId();
			vo.setClubDepartment(clubDepartmentId);
			String clubDepartmentName = departmentNameMap.get(clubDepartmentId);
			if (null == clubDepartmentName) {
				ClubDepartment clubDepartment = clubDepartmentService.get(clubDepartmentId);
				if (null != clubDepartment) {
					clubDepartmentName = clubDepartment.getName();
				}
				if (null == clubDepartmentName) {
					clubDepartmentName = "";
				}
				departmentNameMap.put(clubDepartmentId, clubDepartmentName);
			}
			vo.setClubDepartmentName(clubDepartmentName);

			vo.setDepartment(user.getDepartment());
			Integer clubPermitId = map.getPremitId();
			vo.setPermit(clubPermitId);
			vo.setPermitName(ClubPremitEnum.getName(clubPermitId));
			vo.setPhone(user.getPhone());

			// 职位
			Integer clubPositionId = map.getPositionId();
			vo.setPosition(clubPositionId);
			String clubPositionName = positioNameMap.get(clubPositionId);
			if (null == clubPositionName) {
				ClubPosition clubPosition = clubPositionService.get(clubPositionId);
				if (null != clubPosition) {
					clubPositionName = clubPosition.getName();
				}
				if (null == clubPositionName) {
					clubPositionName = "";
				}
				positioNameMap.put(clubPositionId, clubPositionName);
			}
			vo.setPositionName(clubPositionName);

			vo.setQq(user.getQq());
			vo.setStartYear(user.getStartYear());
			vo.setUserId(map.getUserId());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<ClubPhotoVO> listPhoto(Integer clubId, int start, int size) {
		List<ClubPhoto> list = clubPhotoService.list(clubId, start, size);
		List<ClubPhotoVO> vos = new ArrayList<>(list.size());
		for (ClubPhoto c : list) {
			vos.add(new ClubPhotoVO(c.getId(), c.getUrl()));
		}
		return vos;
	}

	@Override
	public List<ClubActivityVO> listActivity(Integer clubId, Integer type, Long startDateInt, Long endDateInt, int start, int size) {
		List<ClubActivity> list = clubActivityService.list(clubId, type, startDateInt, endDateInt, start, size);
		List<ClubActivityVO> vos = new ArrayList<>(list.size());
		for (ClubActivity c : list) {
			vos.add(new ClubActivityVO(c.getId(), c.getTitle(), c.getStartDate(), c.getEndDate(), c.getPlace()));
		}
		return vos;
	}

	@Override
	public boolean submitActivity(ClubActivityFO fo, int userId) {
		long startDateInt = DateUtil.minstr2Int(fo.getStartDate());
		long endDateInt = DateUtil.minstr2Int(fo.getEndDate());
		if (fo.getId() == null) {
			Integer id = clubActivityService.add(fo.getClubId(), fo.getType(), fo.getTitle(), fo.getStartDate(), startDateInt, fo.getEndDate(), endDateInt, fo.getPlace(), fo.getPoster(),
					fo.getDescription(), userId);
			return null != id;
		}
		return clubActivityService.update(fo.getId(), fo.getClubId(), fo.getType(), fo.getTitle(), fo.getStartDate(), startDateInt, fo.getEndDate(), endDateInt, fo.getPlace(), fo.getPoster(),
				fo.getDescription());
	}

	@Override
	public List<ClubDynamicVO> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size) {
		List<Club> list = clubService.list(keyword, type, level, sortType, start, size);
		List<ClubDynamicVO> vos = new ArrayList<>(list.size());
		for (Club c : list) {
			ClubDynamicVO vo = new ClubDynamicVO();
			vo.setClubId(c.getId());
			String createDate = DateUtil.date2String(c.getPosttime(), DateUtil.yyyy1MM1dd);
			vo.setCreateDate(createDate);
			vo.setIntroduction(c.getIntroduction());
			vo.setLogo(c.getLogo());
			vo.setName(c.getName());
			int scale = userClubMapService.countMember(c.getId(), null, null, null);
			vo.setScale(scale);
			vo.setLevel(c.getLevel());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public ClubIndexDetailVO getDetail(int clubId, Integer userId) {
		Club club = clubService.get(clubId);
		if (null == club) {
			club = new Club();
		}
		ClubIndexDetailVO vo = new ClubIndexDetailVO();
		vo.setAnnouncement(club.getAnnouncement());
		vo.setClubId(club.getId());
		vo.setIntroduction(club.getIntroduction());
		boolean isMember = false;
		if (null != userId) {
			UserClubMap map = userClubMapService.get(userId, clubId);
			if (null != map) {
				isMember = true;
			} else {
				// 系统管理员
				User user = userService.get(userId);
				isMember = null != user && user.getRole() == RoleTypeEnum.ADMIN.getId();
			}
		}
		vo.setIsMember(isMember);
		vo.setLevel(club.getLevel());
		vo.setLogo(club.getLogo());
		vo.setName(club.getName());
		vo.setType(club.getType());
		if (isMember == true) {
			List<MemberVO> memberList = new ArrayList<>();
			List<UserClubMap> list = userClubMapService.listMember(clubId, null, null, null, 0, 10);
			for (UserClubMap map : list) {
				User user = userService.get(map.getUserId());
				if (null == user) {
					user = new User();
				}
				memberList.add(new MemberVO(user.getImage(), user.getUsername(), user.getId()));
			}
			vo.setMemberList(memberList);
		}
		List<ClubActivity> clubActivityList = clubActivityService.list(clubId, null, null, null, 0, 10);
		List<ClubActivityListVO> activityList = new ArrayList<>();
		for (ClubActivity c : clubActivityList) {
			activityList.add(new ClubActivityListVO(c.getId(), c.getType(), c.getTitle(), c.getStartDate(), c.getEndDate(), c.getPlace(), c.getPoster(), c.getDescription()));
		}
		vo.setActivityList(activityList);
		List<ClubPhoto> list = clubPhotoService.list(clubId, 0, 10);
		List<ClubPhotoVO> albumList = new ArrayList<>();
		for (ClubPhoto c : list) {
			albumList.add(new ClubPhotoVO(c.getId(), c.getUrl()));
		}
		vo.setAlbumList(albumList);
		return vo;
	}

}
