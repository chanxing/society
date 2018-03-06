package com.society.vo;

public class IdNameVO<ID> {

	private ID id;
	private String name;

	public IdNameVO() {
		super();
	}

	public IdNameVO(ID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IdNameVO [id=" + id + ", name=" + name + "]";
	}

}
