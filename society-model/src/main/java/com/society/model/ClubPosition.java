package com.society.model;

/**
 * 部门职位
 * 
 * @author beyond
 *
 */
public class ClubPosition {

	/** ID **/
	private int id;
	/** 职位名称 **/
	private String name;
	/** 社团ID **/
	private int clubId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	@Override
	public String toString() {
		return "ClubPosition [id=" + id + ", name=" + name + ", clubId=" + clubId + "]";
	}

}
