package com.society.vo;

/**
 * 社团成员
 * 
 * @author beyond
 *
 */
public class ClubMemberVO {
	/** ID **/
	private Integer userId;
	/** 真实姓名 **/
	private String actualName;
	/** 部门ID **/
	private Integer clubDepartment;
	private String clubDepartmentName;
	/** 职位ID **/
	private Integer position;
	private String positionName;
	/** 系别 **/
	private String department;
	/** 电话 **/
	private String phone;
	/** 入学年份：yyyy **/
	private String startYear;
	/** QQ **/
	private String qq;
	/** 权限 **/
	private Integer permit;
	private String permitName;

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

	public Integer getClubDepartment() {
		return clubDepartment;
	}

	public void setClubDepartment(Integer clubDepartment) {
		this.clubDepartment = clubDepartment;
	}

	public String getClubDepartmentName() {
		return clubDepartmentName;
	}

	public void setClubDepartmentName(String clubDepartmentName) {
		this.clubDepartmentName = clubDepartmentName;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getPermit() {
		return permit;
	}

	public void setPermit(Integer permit) {
		this.permit = permit;
	}

	public String getPermitName() {
		return permitName;
	}

	public void setPermitName(String permitName) {
		this.permitName = permitName;
	}

	@Override
	public String toString() {
		return "ClubMemberVO [userId=" + userId + ", actualName=" + actualName + ", clubDepartment=" + clubDepartment + ", clubDepartmentName=" + clubDepartmentName + ", position=" + position
				+ ", positionName=" + positionName + ", department=" + department + ", phone=" + phone + ", startYear=" + startYear + ", qq=" + qq + ", permit=" + permit + ", permitName=" + permitName
				+ "]";
	}

}
