package com.society.constant;

public enum ClubActivityTypeEnum {
	RECRUITING(1, "招新"), SPORTS(2, "体育"), LECTURE(3, "讲座"), PARTY(4, "晚会"), MATCH(5, "比赛"), CAREER_TALK(6, "宣讲会"), OTHER(7, "其他");

	private final int id;
	private final String name;

	private ClubActivityTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ClubActivityTypeEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		ClubActivityTypeEnum[] enu = values();
		for (ClubActivityTypeEnum e : enu) {
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
