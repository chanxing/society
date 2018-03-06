package com.society.constant;

/**
 * 权限
 * 
 * @author beyond
 *
 */
public enum ClubPremitEnum {
	ADMIN(1, "最高管理员"), GENERAL_MANAGER(2, "普通管理员"), MEMBER(3, "社团成员");
	private final int id;
	private final String name;

	private ClubPremitEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ClubPremitEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		ClubPremitEnum[] enu = values();
		for (ClubPremitEnum e : enu) {
			if (e.id == id.intValue()) {
				return e;
			}
		}
		return null;
	}

	public static boolean contain(Integer id) {
		return null != get(id);
	}

	public static String getName(Integer id) {
		ClubPremitEnum e = get(id);
		return null == e ? "" : e.getName();
	}
}
