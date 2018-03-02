package com.society.web.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.society.util.exception.ProjectException;

@ControllerAdvice("com.society.web")
public class SocietyHandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(SocietyHandlerExceptionResolver.class);

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResult> resolveException(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
		logger.warn("url:[" + request.getRequestURI() + "]", ex);
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");

		ErrorResult errorResult = new ErrorResult();
		errorResult.setMessage(ex.getMessage());

		int code = 500;
		String error = "遇到点小问题，请联系客服或稍后重试";
		if (ex instanceof IllegalArgumentException) {
			code = 300;
			error = ex.getMessage();
		} else if (ex instanceof ProjectException) {
			ProjectException se = (ProjectException) ex;
			code = se.getCode();
			error = se.getError();
		}
		errorResult.setCode(code);
		errorResult.setMessage(error);
		ResponseEntity<ErrorResult> entity = new ResponseEntity<ErrorResult>(errorResult, HttpStatus.OK);
		return entity;
	}

}
