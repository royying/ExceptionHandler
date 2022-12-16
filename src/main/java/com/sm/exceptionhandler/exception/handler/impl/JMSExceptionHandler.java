package com.sm.exceptionhandler.exception.handler.impl;

import org.springframework.stereotype.Component;

import com.sm.exceptionhandler.exception.BaseException;
import com.sm.exceptionhandler.exception.handler.IExceptionHandler;
import com.sm.exceptionhandler.model.ErrorDetail;

@Component
public class JMSExceptionHandler implements IExceptionHandler<BaseException> {
	@Override
	public void handleException(final BaseException baseException) {
		final ErrorDetail errorDetail = baseException.getErrorDetail();
		if(errorDetail != null) {
			System.out.println("I am JMS Exception Handler :"+errorDetail.getformatedMessage());
		} else {
			System.out.println("I am JMS Exception Handler.");
		}
	}
}
