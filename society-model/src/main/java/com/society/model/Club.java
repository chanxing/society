package com.society.model;

import java.util.Date;

/**
 * 社团
 * 
 * @author beyond
 *
 */
public class Club {
	/** ID **/
	private int id;
	/** 社团名称 **/
	private String name;
	/** logo **/
	private String logo;
	/** 介绍 **/
	private String introduction;
	/** 社团类型 **/
	private int type;
	/** 审团等级 **/
	private int level;
	/** 创建人 **/
	private int creater;
	private Date posttime;
	private Date lmodify;
	private int del;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCreater() {
		return creater;
	}

	public void setCreater(int creater) {
		this.creater = creater;
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
		return "Club [id=" + id + ", name=" + name + ", logo=" + logo + ", introduction=" + introduction + ", type=" + type + ", level=" + level + ", creater=" + creater + ", posttime=" + posttime
				+ ", lmodify=" + lmodify + ", del=" + del + "]";
	}

}
