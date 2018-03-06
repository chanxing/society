package com.society.vo;

/**
 * 社团权限
 * 
 * @author beyond
 *
 */
public class ClubPremitVO {
	private Integer premit;
	private String name;

	public ClubPremitVO() {
		super();
	}

	public ClubPremitVO(Integer premit, String name) {
		super();
		this.premit = premit;
		this.name = name;
	}

	public Integer getPremit() {
		return premit;
	}

	public void setPremit(Integer premit) {
		this.premit = premit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClubPremitVO [premit=" + premit + ", name=" + name + "]";
	}

}
