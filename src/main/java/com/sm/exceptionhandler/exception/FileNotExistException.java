package com.sm.exceptionhandler.exception;

import com.sm.exceptionhandler.annotation.Handlers;
import com.sm.exceptionhandler.model.ErrorDetail;


@Handlers({"fileExceptionHandler"})
public class FileNotExistException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3772900345779027149L;
	
	public FileNotExistException(RuntimeException e) {
		super(e);
	}
	
	public FileNotExistException(String msg) {
		super(msg);
	}
	
	public FileNotExistException(ErrorDetail errorDetail) {
		super(errorDetail);
		
	}

}
