package com.society.vo;

/**
 * 社团列表
 * 
 * @author beyond
 *
 */
public class ClubListVO {
	private Integer clubId;
	private String name;
	private String logo;
	private String introduction;
	private Integer premit;
	private String premitName;

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

	public Integer getPremit() {
		return premit;
	}

	public void setPremit(Integer premit) {
		this.premit = premit;
	}

	public String getPremitName() {
		return premitName;
	}

	public void setPremitName(String premitName) {
		this.premitName = premitName;
	}

}
