package com.society.constant;

/**
 * 验证码类型
 * 
 * @author yanggui
 *
 */
public enum VerificationCodeTypeEnum {

	REGISTER(1, "注册"), RESET_PASSWORD(2, "找回密码");

	private final int id;
	private final String name;

	private VerificationCodeTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 根据ID获取枚举值
	 * 
	 * @param id
	 * @return
	 */
	public static VerificationCodeTypeEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		for (VerificationCodeTypeEnum type : VerificationCodeTypeEnum.values()) {
			if (type.getId() == id.intValue()) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 根据ID获取枚举值的名称
	 * 
	 * @param id
	 * @return
	 */
	public static String getName(Integer id) {
		if (null == id) {
			return null;
		}
		for (VerificationCodeTypeEnum type : VerificationCodeTypeEnum.values()) {
			if (type.getId() == id.intValue()) {
				return type.getName();
			}
		}
		return null;
	}
}
