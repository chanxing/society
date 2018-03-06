package com.society.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.society.util.exception.ProjectException;

/**
 * 文件上传工具类
 * 
 * @author zhenzhijian
 * 
 */
public class UploadUtil {
	private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);
	private static String BASE_PATH = "/data/dl/file/";
	private static String BASE_URI = "/dl/file/";

	private static String BASE_VIDEO_PATH = "/data/dl/video/";
	private static String BASE_VIDEO_URI = "/dl/video/";

	/** UID **/
	public static int UID = 0;
	/** PHONE **/
	public static int PHONE = 1;
	/** IDFA_IMEI **/
	public static int IDFA_IMEI = 2;

	static {
		File file = new File(BASE_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 上传并获取下载URL
	 * 
	 * @param patch
	 * @return
	 * @throws IOException
	 */
	public static String upload(MultipartFile patch) throws IOException {
		if (patch == null || patch.isEmpty()) {
			throw new ProjectException("上传文件不存在file名的multipart。");
		}
		String name = Md5Util.md5(patch.getInputStream());
		String requestFileName = patch.getOriginalFilename(); // 获取整个文件名
		String suffix = StringUtil.substringAfterLast(requestFileName, "."); // 获取后缀名

		String filepath = BASE_PATH + name + "." + suffix; // 存储的路径
		File file = new File(filepath);

		if (!file.exists()) {
			file.mkdirs();
		}

		patch.transferTo(file);

		if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) || "gif".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
			return Config.getImagesDomain() + BASE_URI + name + "." + suffix;
		}

		return Config.getDomain() + BASE_URI + name + "." + suffix;
	}

	/**
	 * 上传图片并获取下载URL
	 * 
	 * @param patch
	 * @return
	 * @throws IOException
	 */
	public static String uploadImage(MultipartFile patch) throws IOException {
		if (patch == null || patch.isEmpty()) {
			throw new ProjectException("上传文件不存在file名的multipart。");
		}
		String requestFileName = patch.getOriginalFilename(); // 获取整个文件名
		String suffix = StringUtil.substringAfterLast(requestFileName, "."); // 获取后缀名.jsp
		String name = Md5Util.md5(patch.getInputStream());
		String savePath = BASE_PATH;
		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			savePathFile.mkdirs();
		}
		String filepath = savePath + name + "." + suffix; // 存储的路径
		File file = new File(filepath);
		patch.transferTo(file);

		return Config.getImagesDomain() + BASE_URI + name + "." + suffix;
	}

	/**
	 * 上传视频并获取文件地址
	 * 
	 * @param patch
	 * @return
	 * @throws IOException
	 */
	public static UploadInfo uploadVideo(MultipartFile patch) throws IOException {
		if (patch == null || patch.isEmpty()) {
			throw new ProjectException("上传文件不存在file名的multipart。");
		}
		String requestFileName = patch.getOriginalFilename();
		String suffix = StringUtil.substringAfterLast(requestFileName, ".");
		if (!"mp4".equalsIgnoreCase(suffix)) {
			throw new ProjectException("非法视频格式！");
		}

		String name = Md5Util.md5(patch.getInputStream());
		if (StringUtils.isBlank(name)) {
			throw new ProjectException("读取文件出现异常！");
		}
		String url = Config.getVideoDomain() + BASE_VIDEO_URI + name + "." + suffix;

		String filepath = BASE_VIDEO_PATH + name + "." + suffix;
		File file = new File(filepath);
		if (!file.exists()) {
			file.mkdirs();
		} else {
			return new UploadInfo(filepath, url);
		}
		patch.transferTo(file);
		return new UploadInfo(filepath, url);
	}

	public static String getRealPath(String name, String suffix) {
		if (StringUtils.isBlank(name)) {
			return null;
		}

		if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) || "gif".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
			return BASE_PATH + name + "." + suffix;
		} else if ("mp4".equalsIgnoreCase(suffix) || "avi".equalsIgnoreCase(suffix)) {
			return BASE_VIDEO_PATH + name + "." + suffix;
		}
		return "";
	}

	public static String getFilePath(String url) {
		if (StringUtils.isBlank(url) || url.indexOf("http://") < 0) {
			return url;
		}
		String suffix = StringUtil.substringAfterLast(url, ".");
		String filename = StringUtil.substringAfterLast(url, "/");
		if ("jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix) || "gif".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
			return BASE_PATH + filename;
		} else if ("mp4".equalsIgnoreCase(suffix) || "avi".equalsIgnoreCase(suffix)) {
			return BASE_VIDEO_PATH + filename;
		}
		return BASE_PATH + filename;
	}

	/**
	 * 获取文件名+后缀
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		if (StringUtils.isBlank(path)) {
			return "";
		}
		return StringUtils.substringAfterLast(path, "/");
	}

	public static class UploadInfo {
		private String fpath;
		private String url;
		private Boolean status;
		private String message;

		public Boolean getStatus() {
			return status;
		}

		public void setStatus(Boolean status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public UploadInfo(String fpath, String url) {
			this.fpath = fpath;
			this.url = url;
			this.status = true;
		}

		public UploadInfo(boolean status, String message) {
			this.status = status;
			this.message = message;
		}

		public String getFpath() {
			return fpath;
		}

		public void setFpath(String fpath) {
			this.fpath = fpath;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

	}

}
