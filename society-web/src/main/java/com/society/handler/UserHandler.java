package com.society.handler;

import com.society.model.User;
import com.society.vo.UserDealVO;
import com.society.vo.UserVO;

public interface UserHandler {

	UserDealVO get(Integer id);

	UserVO getInfo(Integer id);

	UserVO toUserVO(User user);
}
