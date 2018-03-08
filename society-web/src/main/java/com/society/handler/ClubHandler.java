package com.society.handler;

import java.util.List;

import com.society.fo.ClubActivityFO;
import com.society.fo.ClubFO;
import com.society.vo.ClubActivityVO;
import com.society.vo.ClubDynamicVO;
import com.society.vo.ClubIndexDetailVO;
import com.society.vo.ClubListVO;
import com.society.vo.ClubMemberVO;
import com.society.vo.ClubPhotoVO;
import com.society.vo.ClubVO;
import com.society.vo.IdNameVO;

public interface ClubHandler {

	List<ClubListVO> list(Integer userId, int start, int size);

	ClubVO get(Integer clubId);

	boolean submit(ClubFO fo, int userId);

	boolean submitDepartment(Integer departmentId, Integer clubId, String name);

	boolean submitPosition(Integer positionId, Integer clubId, String name);

	List<IdNameVO<String>> listDepartment(Integer clubId);

	List<IdNameVO<String>> listPosition(Integer clubId);

	List<ClubMemberVO> listMember(int clubId, Integer departmentId, Integer positionId, String keyword, int start, int size);

	List<ClubPhotoVO> listPhoto(Integer clubId, int start, int size);

	List<ClubActivityVO> listActivity(Integer clubId, Integer type, Long startDateInt, Long endDateInt, int start, int size);

	boolean submitActivity(ClubActivityFO fo, int userId);

	List<ClubDynamicVO> list(String keyword, Integer type, Integer level, Integer sortType, int start, int size);

	ClubIndexDetailVO getDetail(int clubId, Integer user);
}
