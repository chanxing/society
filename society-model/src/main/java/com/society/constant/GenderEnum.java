package com.society.constant;

/**
 * 性别
 * 
 * @author beyond
 *
 */
public enum GenderEnum {
	MALE(0, "男"), FEMAIL(1, "女");
	private final int id;
	private final String name;

	private GenderEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static GenderEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		GenderEnum[] enu = values();
		for (GenderEnum e : enu) {
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
