package com.society.constant;

/**
 * 加入社团申请
 * 
 * @author beyond
 *
 */
public enum ClubApplyStatusEnum {
	APPLYING(0, "申请中"), PASS(1, "通过"), REJECT(2, "拒绝");
	private final int id;
	private final String name;

	private ClubApplyStatusEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static ClubApplyStatusEnum get(Integer id) {
		if (null == id) {
			return null;
		}
		ClubApplyStatusEnum[] enu = values();
		for (ClubApplyStatusEnum e : enu) {
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
