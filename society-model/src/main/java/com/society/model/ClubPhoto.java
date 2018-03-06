package com.society.model;

import java.util.Date;

/**
 * 社团相册
 * 
 * @author beyond
 *
 */
public class ClubPhoto {

	private int id;
	/** 社团ID **/
	private int clubId;
	/** url **/
	private String url;
	/** 操作人 **/
	private int operator;
	private Date posttime;
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClubId() {
		return clubId;
	}

	public void setClubId(int clubId) {
		this.clubId = clubId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	@Override
	public String toString() {
		return "ClubPhoto [id=" + id + ", clubId=" + clubId + ", url=" + url + ", operator=" + operator + ", posttime=" + posttime + ", del=" + del + "]";
	}

}
