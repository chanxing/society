package com.society.constant;

/**
 * 角色
 * 
 * @author beyond
 *
 */
public enum RoleTypeEnum {
	ADMIN(1, "系统管理人员");

	private final int id;
	private final String name;

	private RoleTypeEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static RoleTypeEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		RoleTypeEnum[] enu = values();
		for (RoleTypeEnum e : enu) {
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
