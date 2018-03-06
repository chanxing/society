package com.society.vo;

/**
 * 社团加入申请
 * 
 * @author beyond
 *
 */
public class ClubApplyVO {

	private Integer id;
	/** 用户ID **/
	private Integer userId;
	/** 真实姓名 **/
	private String actualName;
	/** 性别 **/
	private Integer gender;
	/** 系别 **/
	private String department;
	/** 入学年份 **/
	private String startYear;
	/** 手机号 **/
	private String phone;
	/** QQ号 **/
	private String qq;
	/** 微信号 **/
	private String weixin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getActualName() {
		return actualName;
	}

	public void setActualName(String actualName) {
		this.actualName = actualName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Override
	public String toString() {
		return "ClubApplyVO [id=" + id + ", userId=" + userId + ", actualName=" + actualName + ", gender=" + gender + ", department=" + department + ", startYear=" + startYear + ", phone=" + phone
				+ ", qq=" + qq + ", weixin=" + weixin + "]";
	}

}
