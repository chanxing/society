package com.society.web.controller.deal;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.society.handler.ClubHandler;
import com.society.service.ClubPhotoService;
import com.society.util.UploadUtil;
import com.society.vo.ClubPhotoVO;
import com.society.web.base.BaseController;
import com.society.web.base.TableId;
import com.society.web.check.CheckUtil;

/**
 * 社团相册
 * 
 * @author beyond
 *
 */
@RestController
@RequestMapping("/deal/clubPhoto")
public class ClubPhotoDealController extends BaseController {
	Logger logger = LoggerFactory.getLogger(ClubPhotoDealController.class);
	private static final int PAGE_SIZE = 100;
	@Autowired
	private ClubPhotoService clubPhotoService;
	@Autowired
	private ClubHandler clubHandler;
	@Autowired
	MultipartResolver multipartResolver;

	/**
	 * 上传相册图片<br>
	 * /deal/clubPhoto/uploadPhoto.do
	 * 
	 * @param clubId
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadPhoto", method = { RequestMethod.POST })
	public String uploadPhoto(Integer clubId, MultipartHttpServletRequest request, HttpSession session) throws Exception {
		Integer userId = this.getUserId(session);
		logger.info("clubId: " + request.getParameter("clubId"));
		logger.info("uploadClubImage userId[{}], clubId[{}]", new Object[] { userId, clubId });
		CheckUtil.isValidClubId(clubId);
		MultipartFile patch = request.getFile("uploadFile");
		String url = UploadUtil.uploadImage(patch);
		boolean result = clubPhotoService.add(clubId, url, userId);
		return toRecordResult(result);
	}

	/**
	 * 相册图片列表<br>
	 * /deal/clubPhoto/list.do
	 * 
	 * @param clubId
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Integer clubId, Integer page, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("uploadClubImage userId[{}] clubId[{}], page[{}]", new Object[] { userId, clubId, page });
		CheckUtil.isValidClubId(clubId);
		CheckUtil.isValidPage(page);
		int size = PAGE_SIZE;
		int start = (page.intValue() - 1) * size;
		int count = clubPhotoService.count(clubId);
		List<ClubPhotoVO> records = clubHandler.listPhoto(clubId, start, size);
		return toListResult(TableId.TEMP_ID, records, count, page, size);
	}

	/**
	 * 删除相册图片<br>
	 * /deal/clubPhoto/delete.do
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, HttpSession session) {
		Integer userId = this.getUserId(session);
		logger.info("uploadClubImage userId[{}] id[{}]", new Object[] { userId, id });
		CheckUtil.isValidId(id);
		boolean result = clubPhotoService.delete(id);
		return toBooleanResult(result);
	}

}
