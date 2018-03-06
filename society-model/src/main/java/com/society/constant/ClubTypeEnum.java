package com.society.constant;

/**
 * 社团类型
 * 
 * @author beyond
 *
 */
public enum ClubTypeEnum {
	SCIENCE(0, "学术类"), PRACTICE(1, "时间类"), SPORTS(2, "体育类"), ART(3, "艺术类"), GROUP_LEARNING(4, "团学类"), OTHER(5, "其他");
	private final int id;
	private final String name;

	private ClubTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ClubTypeEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		ClubTypeEnum[] enu = values();
		for (ClubTypeEnum e : enu) {
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
