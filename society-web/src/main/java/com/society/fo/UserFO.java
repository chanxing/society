package com.society.fo;

public class UserFO {
	/** 帐号 **/
	// private String email;
	/** 用户名 **/
	private String username;
	/** 真实姓名 **/
	private String actualName;
	/** 性别 **/
	private Integer gender;
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
	private String weixin;
	/** 爱好 **/
	private String hobbies;
	/** 自我介绍 **/
	private String selIntroduction;
	/** 头像 **/
	private String image;

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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
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

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
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

	@Override
	public String toString() {
		return "UserFO [username=" + username + ", actualName=" + actualName + ", gender=" + gender + ", birthday=" + birthday + ", nativePlace=" + nativePlace + ", department=" + department
				+ ", profession=" + profession + ", grade=" + grade + ", phone=" + phone + ", startYear=" + startYear + ", qq=" + qq + ", weixin=" + weixin + ", hobbies=" + hobbies
				+ ", selIntroduction=" + selIntroduction + ", image=" + image + "]";
	}

}
