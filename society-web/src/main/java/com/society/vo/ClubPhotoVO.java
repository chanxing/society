package com.society.vo;

public class ClubPhotoVO {

	private Integer id;
	private String url;

	public ClubPhotoVO() {
		super();
	}

	public ClubPhotoVO(Integer id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ClubPhotoVO [id=" + id + ", url=" + url + "]";
	}

}
