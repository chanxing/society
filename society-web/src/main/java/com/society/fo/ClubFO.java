package com.society.fo;

import com.society.constant.ClubLevelEnum;
import com.society.constant.ClubTypeEnum;

public class ClubFO {
	/** ID **/
	private Integer clubId;
	/** 社团名称 **/
	private String name;
	/** logo **/
	private String logo;
	/** 介绍 **/
	private String introduction;
	/** 公告 **/
	private String announcement;
	/** 社团类型 {@link ClubTypeEnum} **/
	private Integer type;
	/** 社团等级 {@link ClubLevelEnum} **/
	private Integer level;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	@Override
	public String toString() {
		return "ClubFO [clubId=" + clubId + ", name=" + name + ", logo=" + logo + ", introduction=" + introduction + ", announcement=" + announcement + ", type=" + type + ", level=" + level + "]";
	}

}
