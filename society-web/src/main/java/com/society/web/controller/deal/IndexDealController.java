package com.society.web.controller.deal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.society.fo.UserFO;
import com.society.handler.UserHandler;
import com.society.service.UserService;
import com.society.util.UploadUtil;
import com.society.vo.UserDealVO;
import com.society.web.base.BaseController;
import com.society.web.check.CheckUtil;

@RestController
@RequestMapping("/deal")
public class IndexDealController extends BaseController {
	Logger logger = LoggerFactory.getLogger(IndexDealController.class);
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private UserService userService;

	/**
	 * uri:/deal/getUserDetail.do
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/getUserDetail")
	public String getUserDetail(HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("getUserDetail userId[{}]", new Object[] { userId });
		UserDealVO user = userHandler.get(userId);
		if (null == user) {
			user = new UserDealVO();
		}
		return toRecordResult(user);
	}

	/**
	 * uri:/deal/uploadUserImage.do
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadUserImage", method = { RequestMethod.POST })
	public String uploadUserImage(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		Integer userId = this.getUserId(session);
		logger.info("uploadUserImage userId[{}]", new Object[] { userId });
		MultipartFile patch = request.getFile("uploadFile");
		String url = UploadUtil.uploadImage(patch);
		userService.updateImage(userId, url);
		return toRecordResult(url);
	}

	@RequestMapping("/updateUser")
	public String updateUser(UserFO fo, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("updateUser UserFO[{}], userId[{}]", new Object[] { fo, userId });
		CheckUtil.isValidUserFO(fo);
		boolean result = userService.update(userId, fo.getUsername(), fo.getActualName(), fo.getGender(), fo.getBirthday(), fo.getNativePlace(), fo.getDepartment(), fo.getProfession(), fo.getGrade(),
				fo.getPhone(), fo.getStartYear(), fo.getQq(), fo.getWeixin(), fo.getHobbies(), fo.getSelIntroduction(), fo.getImage());
		return toBooleanResult(result);
	}

	@RequestMapping(value = "/updatePassword", method = { RequestMethod.POST })
	public String updatePassword(String password, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("updatePassword userId[{}]", new Object[] { userId });
		// 检验参数
		CheckUtil.isValidPassword(password);
		boolean result = userService.updatePassword(userId, password);
		return toBooleanResult(result);
	}
}
