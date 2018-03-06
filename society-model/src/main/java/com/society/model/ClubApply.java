package com.society.model;

import java.util.Date;

import com.society.constant.ClubApplyStatusEnum;

/**
 * 社团申请
 * 
 * @author beyond
 *
 */
public class ClubApply {

	/** ID **/
	private int id;
	/** 用户ID **/
	private int userId;
	/** 社团ID **/
	private int clubId;
	/** 状态 {@link ClubApplyStatusEnum} **/
	private int status;
	/** 创建日期 **/
	private Date posttime;
	/** 修改日期 **/
	private Date lmodify;
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public Date getLmodify() {
		return lmodify;
	}

	public void setLmodify(Date lmodify) {
		this.lmodify = lmodify;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "ClubApply [id=" + id + ", userId=" + userId + ", clubId=" + clubId + ", status=" + status + ", posttime=" + posttime + ", lmodify=" + lmodify + ", del=" + del + "]";
	}

}
