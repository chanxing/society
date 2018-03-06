package com.society.vo;

import java.util.ArrayList;
import java.util.List;

import com.society.constant.ClubLevelEnum;
import com.society.constant.ClubTypeEnum;

/**
 * 社团首页详情
 * 
 * @author beyond
 *
 */
public class ClubIndexDetailVO {
	/** ID **/
	private Integer clubId;
	/** 社团名称 **/
	private String name;
	/** 社团类型 {@link ClubTypeEnum} **/
	private Integer type;
	/** 社团等级 {@link ClubLevelEnum} **/
	private Integer level;
	/** 介绍 **/
	private String introduction;
	/** 公告 **/
	private String announcement;
	/** logo **/
	private String logo;
	/** 当前是否是成员 **/
	private boolean isMember;
	/** 活动列表 **/
	private List<ClubActivityListVO> activityList = new ArrayList<>();
	/** 相册列表 **/
	private List<ClubPhotoVO> albumList = new ArrayList<>();
	/** 成员 **/
	private List<MemberVO> memberList = new ArrayList<>();

	public static class ClubActivityListVO {
		private Integer id;
		/** 类型 **/
		private Integer type;
		/** 标题 **/
		private String title;
		/** 开始日期 **/
		private String startDate;
		/** 结束日期 **/
		private String endDate;
		/** 地点 **/
		private String place;
		/** 海报url **/
		private String poster;
		/** 描述 **/
		private String description;

		public ClubActivityListVO() {
			super();
		}

		public ClubActivityListVO(Integer id, Integer type, String title, String startDate, String endDate, String place, String poster, String description) {
			super();
			this.id = id;
			this.type = type;
			this.title = title;
			this.startDate = startDate;
			this.endDate = endDate;
			this.place = place;
			this.poster = poster;
			this.description = description;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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
			return "ClubActivityListVO [id=" + id + ", type=" + type + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", place=" + place + ", poster=" + poster
					+ ", description=" + description + "]";
		}

	}

	public static class MemberVO {
		private String image;
		private String username;
		private Integer userId;

		public MemberVO() {
			super();
		}

		public MemberVO(String image, String username, Integer userId) {
			super();
			this.image = image;
			this.username = username;
			this.userId = userId;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		@Override
		public String toString() {
			return "MemberVO [image=" + image + ", username=" + username + ", userId=" + userId + "]";
		}

	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean getIsMember() {
		return isMember;
	}

	public void setIsMember(boolean isMember) {
		this.isMember = isMember;
	}

	public List<ClubActivityListVO> getActivityList() {
		return activityList;
	}

	public void setActivityList(List<ClubActivityListVO> activityList) {
		this.activityList = activityList;
	}

	public List<ClubPhotoVO> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<ClubPhotoVO> albumList) {
		this.albumList = albumList;
	}

	public List<MemberVO> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<MemberVO> memberList) {
		this.memberList = memberList;
	}

	@Override
	public String toString() {
		return "ClubIndexDetailVO [clubId=" + clubId + ", name=" + name + ", type=" + type + ", level=" + level + ", introduction=" + introduction + ", announcement=" + announcement + ", logo=" + logo
				+ ", isMember=" + isMember + ", activityList=" + activityList + ", albumList=" + albumList + ", memberList=" + memberList + "]";
	}

}
