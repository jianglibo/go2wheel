package com.go2wheel.katharsis.exception;

import com.go2wheel.constant.AppErrorCodes;

public class MissingRequiredFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title = "missing required field.";
	private String code = AppErrorCodes.MISSING_REQUIRED_FIELD;
	private String detail;
	
	public MissingRequiredFieldException(String detail) {
		super(detail);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
