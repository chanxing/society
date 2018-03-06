package com.society.constant;

/**
 * 社团等级
 * 
 * @author beyond
 *
 */
public enum ClubLevelEnum {
	SCHOOL_LEVEL(0, "校级组织"), COLLEGE_LEVEL(1, "院级组织"), INTEREST_LEVEL(2, "兴趣组织");
	private final int id;
	private final String name;

	private ClubLevelEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ClubLevelEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		ClubLevelEnum[] enu = values();
		for (ClubLevelEnum e : enu) {
			if (e.id == id.intValue()) {
				return e;
			}
		}
		return null;
	}

	public static boolean contain(Integer id) {
		return null != get(id);
	}
}
