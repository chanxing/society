package com.society.vo;

public class ClubActivityVO {

	private Integer id;
	/** 标题 **/
	private String name;
	/** 开始时间 **/
	private String startDate;
	/** 结束时间 **/
	private String endDate;
	/** 地点 **/
	private String place;

	public ClubActivityVO() {
		super();
	}

	public ClubActivityVO(Integer id, String name, String startDate, String endDate, String place) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.place = place;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "ClubActivityVO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", place=" + place + "]";
	}

}
