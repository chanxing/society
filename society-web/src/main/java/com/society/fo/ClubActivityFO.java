package com.society.fo;

/**
 * 社团活动
 * 
 * @author beyond
 *
 */
public class ClubActivityFO {

	private Integer id;
	/** 社团ID **/
	private Integer clubId;
	/** 活动类型 **/
	private Integer type;
	/** 标题 **/
	private String title;
	/** 开始时间：yyyy-MM-dd HH:mm **/
	private String startDate;
	/** 结束时间：yyyy-MM-dd HH:mm **/
	private String endDate;
	/** 举办地点 **/
	private String place;
	/** 海报URL **/
	private String poster;
	/** 描述 **/
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
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

	@Override
	public String toString() {
		return "ClubActivityFO [id=" + id + ", clubId=" + clubId + ", type=" + type + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", place=" + place + ", poster="
				+ poster + ", description=" + description + "]";
	}

}
