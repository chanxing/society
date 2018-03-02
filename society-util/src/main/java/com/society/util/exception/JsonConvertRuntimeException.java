package com.society.util.exception;

public class JsonConvertRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JsonConvertRuntimeException() {
		super();
	}

	public JsonConvertRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JsonConvertRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonConvertRuntimeException(String message) {
		super(message);
	}

	public JsonConvertRuntimeException(Throwable cause) {
		super(cause);
	}

}
