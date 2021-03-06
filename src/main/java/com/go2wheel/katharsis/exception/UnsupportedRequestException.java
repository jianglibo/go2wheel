package com.go2wheel.katharsis.exception;

import com.go2wheel.constant.AppErrorCodes;

public class UnsupportedRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title = "request not supported.";
	private String code = AppErrorCodes.UNSUPPORTED_REQUEST;
	private String detail;
	
	public UnsupportedRequestException(String detail) {
		super(detail);
		setDetail(detail);
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
