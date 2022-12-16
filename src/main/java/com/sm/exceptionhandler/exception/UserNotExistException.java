package com.sm.exceptionhandler.exception;

import com.sm.exceptionhandler.annotation.Handlers;
import com.sm.exceptionhandler.model.ErrorDetail;


@Handlers({"fileExceptionHandler", "defaultExceptionHandler", "logExceptionHandler", "JMSExceptionHandler"})
public class UserNotExistException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3772900345779027149L;
	
	public UserNotExistException(RuntimeException e) {
		super(e);
	}
	
	public UserNotExistException(String msg) {
		super(msg);
	}
	
	public UserNotExistException(ErrorDetail errorDetail) {
		super(errorDetail);
		
	}

}
