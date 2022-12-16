package com.sm.exceptionhandler.exception.handler;


public interface IExceptionHandler<T> {
	
	public void handleException(final T baseException);

}
