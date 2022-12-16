package com.sm.exceptionhandler.exception;

import com.sm.exceptionhandler.model.ErrorDetail;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1488638787124982982L;
	protected ErrorDetail errorDetail;
	public BaseException(String message){
		super(message);
	}
	public BaseException(Exception rexp){
		super(rexp);
	}
	
	public BaseException(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	public ErrorDetail getErrorDetail() {
		return errorDetail;
	}
	public void setErrorDetail(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}
}
