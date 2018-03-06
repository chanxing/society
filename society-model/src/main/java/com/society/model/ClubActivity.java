package com.society.model;

import java.util.Date;

import com.society.constant.ClubActivityTypeEnum;

/**
 * 社团活动
 * 
 * @author beyond
 *
 */
public class ClubActivity {

	private int id;
	/** 社团ID **/
	private int clubId;
	/** 活动类型 {@link ClubActivityTypeEnum} **/
	private int type;
	/** 标题 **/
	private String title;
	/** 开始时间：yyyy-MM-dd HH:mm **/
	private String startDate;
	private long startDateInt;
	/** 结束时间：yyyy-MM-dd HH:mm **/
	private String endDate;
	private long endDateInt;
	/** 举办地点 **/
	private String place;
	/** 海报URL **/
	private String poster;
	/** 描述 **/
	private String description;
	/** 创建人 **/
	private int operator;
	private Date posttime;
	private Date lmodify;
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public long getStartDateInt() {
		return startDateInt;
	}

	public void setStartDateInt(long startDateInt) {
		this.startDateInt = startDateInt;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getEndDateInt() {
		return endDateInt;
	}

	public void setEndDateInt(long endDateInt) {
		this.endDateInt = endDateInt;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
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
		return "ClubActivity [id=" + id + ", clubId=" + clubId + ", type=" + type + ", title=" + title + ", startDate=" + startDate + ", startDateInt=" + startDateInt + ", endDate=" + endDate
				+ ", endDateInt=" + endDateInt + ", place=" + place + ", poster=" + poster + ", description=" + description + ", operator=" + operator + ", posttime=" + posttime + ", lmodify="
				+ lmodify + ", del=" + del + "]";
	}

}
