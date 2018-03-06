package com.society.model;

import java.util.Date;

import com.society.constant.RoleTypeEnum;

public class User {

	/** ID **/
	private int id;
	/** 帐号 **/
	private String account;
	/** 已加密密码 **/
	private String encryptPassword;
	/**  **/
	private String salt;
	/** 用户名 **/
	private String username;
	/** 真实姓名 **/
	private String actualName;
	/** 性别 **/
	private int gender;
	/** 生日日期：yyyy-MM-dd **/
	private String birthday;
	/** 籍贯 **/
	private String nativePlace;
	/** 系别 **/
	private String department;
	/** 专业 **/
	private String profession;
	/** 班级 **/
	private String grade;
	/** 电话 **/
	private String phone;
	/** 入学年份：yyyy **/
	private String startYear;
	/** QQ **/
	private String qq;
	/** 微信 **/
	private String wechat;
	/** 爱好 **/
	private String hobbies;
	/** 自我介绍 **/
	private String selIntroduction;
	/** 头像 **/
	private String image;
	/** 角色 {@link RoleTypeEnum} **/
	private int role;
	private Date posttime;
	private Date lmodify;
	private int del;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActualName() {
		return actualName;
	}

	public void setActualName(String actualName) {
		this.actualName = actualName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getSelIntroduction() {
		return selIntroduction;
	}

	public void setSelIntroduction(String selIntroduction) {
		this.selIntroduction = selIntroduction;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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
		return "User [id=" + id + ", account=" + account + ", encryptPassword=" + encryptPassword + ", salt=" + salt + ", username=" + username + ", actualName=" + actualName + ", gender=" + gender
				+ ", birthday=" + birthday + ", nativePlace=" + nativePlace + ", department=" + department + ", profession=" + profession + ", grade=" + grade + ", phone=" + phone + ", startYear="
				+ startYear + ", qq=" + qq + ", wechat=" + wechat + ", hobbies=" + hobbies + ", selIntroduction=" + selIntroduction + ", image=" + image + ", role=" + role + ", posttime=" + posttime
				+ ", lmodify=" + lmodify + ", del=" + del + "]";
	}

	public static class UserInfo {
		private int id;
		/** 帐号 **/
		private String account;
		/** 用户名 **/
		private String username;

		public UserInfo() {
			super();
		}

		public UserInfo(int id, String account, String username) {
			super();
			this.id = id;
			this.account = account;
			this.username = username;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@Override
		public String toString() {
			return "UserInfo [id=" + id + ", account=" + account + ", username=" + username + "]";
		}

	}
}
