package com.society.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.society.util.UploadUtil;
import com.society.util.UploadUtil.UploadInfo;
import com.society.web.base.BaseController;

@Controller
@RequestMapping("/")
public class UploadController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartHttpServletRequest request) throws Exception {
		logger.info("upload [{}]", new Object[] {});
		MultipartFile patch = request.getFile("uploadFile");
		String dlUrl = UploadUtil.upload(patch);
		return toRecordResult(dlUrl);
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(MultipartHttpServletRequest request) throws Exception {
		logger.info("upload [{}]", new Object[] {});
		MultipartFile patch = request.getFile("uploadFile");
		String dlUrl = UploadUtil.uploadImage(patch);
		return toRecordResult(dlUrl);
	}

	@RequestMapping(value = "/uploadvideo", method = RequestMethod.POST)
	@ResponseBody
	public String uploadVideo(MultipartHttpServletRequest request, HttpSession session) throws Exception {
		logger.info("uploadVideo userId[{}]", new Object[] { this.getUserId(session) });
		MultipartFile patch = request.getFile("uploadFile");
		String originalFilename = null;
		if (patch == null || patch.isEmpty()) {
			originalFilename = patch.getOriginalFilename();
		}
		logger.info("uploadVideo userId[{}], originalFilename[{}]", new Object[] { this.getUserId(session), originalFilename });
		UploadInfo info = UploadUtil.uploadVideo(patch);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("path", info.getFpath());
		result.put("url", info.getUrl());
		return toRecordResult(result);

	}
}
