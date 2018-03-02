package com.society.util.exception;

public class ProjectException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProjectException() {
		super();
	}

	public ProjectException(String error) {
		this(400, error);
	}

	public ProjectException(int code, String error) {
		super(error);
		this.code = code;
		this.error = error;
	}

	public ProjectException(int code, String error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.code = code;
		this.error = error;
	}

	public ProjectException(int code, String error, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.error = error;
	}

	public ProjectException(int code, String error, String message) {
		super(message);
		this.code = code;
		this.error = error;
	}

	public ProjectException(int code, String error, Throwable cause) {
		super(cause);
		this.code = code;
		this.error = error;
	}

	private int code;

	private String error;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ProjectException [code=" + code + ", error=" + error + "]";
	}

}
