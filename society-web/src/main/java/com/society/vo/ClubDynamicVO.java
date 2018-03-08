package com.society.vo;

/**
 * 动态页社团列表
 * 
 * @author beyond
 *
 */
public class ClubDynamicVO {
	/** ID **/
	private Integer clubId;
	/** 社团名称 **/
	private String name;
	/** logo **/
	private String logo;
	/** 介绍 **/
	private String introduction;
	/** 创建日期 **/
	private String createDate;
	/** 人数 **/
	private Integer scale;
	/** 等级 **/
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ClubDynamicVO [clubId=" + clubId + ", name=" + name + ", logo=" + logo + ", introduction=" + introduction + ", createDate=" + createDate + ", scale=" + scale + ", level=" + level
				+ "]";
	}

}
