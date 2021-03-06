package com.society.web.spring;

public class ErrorResult {

	private int code;

	private String message;

	public ErrorResult() {
	}

	public ErrorResult(int code, String error) {
		super();
		this.code = code;
		this.message = error;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
