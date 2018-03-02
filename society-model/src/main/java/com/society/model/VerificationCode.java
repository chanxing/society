package com.society.model;

import java.util.Date;

/**
 * 验证码
 * 
 * @author yanggui
 *
 */
public class VerificationCode {

	/** ID **/
	private int id;
	/** 验证码 **/
	private String code;
	/** 验证码类型 1.注册，2.找回密码 **/
	private int type;
	/** 接收方 **/
	private String acceptor;
	/** 失效时间 **/
	private Date invalidTime;
	/** 是否失效：0.未失效，1.已失效 **/
	private int del;
	/** 最后修改时间 **/
	private Date lmodify;
	/** 创建日期：yyyyMMdd **/
	private int sectionInt;
	/** 创建时间 **/
	private Date posttime;

	public VerificationCode() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public Date getLmodify() {
		return lmodify;
	}

	public void setLmodify(Date lmodify) {
		this.lmodify = lmodify;
	}

	public int getSectionInt() {
		return sectionInt;
	}

	public void setSectionInt(int sectionInt) {
		this.sectionInt = sectionInt;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	@Override
	public String toString() {
		return "VerificationCode [id=" + id + ", code=" + code + ", type=" + type + ", acceptor=" + acceptor + ", invalidTime=" + invalidTime + ", del=" + del + ", lmodify=" + lmodify
				+ ", sectionInt=" + sectionInt + ", posttime=" + posttime + "]";
	}

}
