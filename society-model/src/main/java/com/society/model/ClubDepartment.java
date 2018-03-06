package com.society.model;

/**
 * 社团部门
 * 
 * @author beyond
 *
 */
public class ClubDepartment {

	/** ID **/
	private int id;
	/** 部门名称 **/
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
		return "ClubDepartment [id=" + id + ", name=" + name + ", clubId=" + clubId + "]";
	}

}
