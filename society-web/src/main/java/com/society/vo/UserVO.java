package com.society.vo;

public class UserVO {
	private Integer userId;
	/** 用户名 **/
	private String username;
	/** 头像 **/
	private String image;
	/** 是否是管理员 **/
	private Boolean isMaster;

	public UserVO() {
		super();
	}

	public UserVO(Integer userId, String username, String image, Boolean isMaster) {
		super();
		this.userId = userId;
		this.username = username;
		this.image = image;
		this.isMaster = isMaster;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Boolean isMaster) {
		this.isMaster = isMaster;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", username=" + username + ", image=" + image + ", isMaster=" + isMaster + "]";
	}

}
