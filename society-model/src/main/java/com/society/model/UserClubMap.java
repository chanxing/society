package com.society.model;

import java.util.Date;

/**
 * 用户－社团映射
 * 
 * @author beyond
 *
 */
public class UserClubMap {
	/** 用户ID **/
	private int userId;
	/** 社团ID **/
	private int clubId;
	/** 部门ID **/
	private int departmentId;
	/** 职位ID **/
	private int positionId;
	/** 社团权限ID **/
	private int premitId;
	private Date posttime;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getPremitId() {
		return premitId;
	}

	public void setPremitId(int premitId) {
		this.premitId = premitId;
	}

	@Override
	public String toString() {
		return "UserClubMap [userId=" + userId + ", clubId=" + clubId + ", departmentId=" + departmentId + ", positionId=" + positionId + ", premitId=" + premitId + ", posttime=" + posttime + "]";
	}

}
