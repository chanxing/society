package com.society.handler;

import java.util.List;

import com.society.model.User;
import com.society.vo.ClubApplyVO;
import com.society.vo.UserDealVO;
import com.society.vo.UserVO;

public interface UserHandler {

	UserDealVO get(Integer id);

	UserVO getInfo(Integer id);

	UserVO toUserVO(User user);

	List<ClubApplyVO> listClubApplyUser(int clubId, String keyword, int start, int size);

	int countClubApplyUser(int clubId, String keyword);
}
