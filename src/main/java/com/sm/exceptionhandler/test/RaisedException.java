package com.sm.exceptionhandler.test;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.sm.exceptionhandler.annotation.HandleException;
import com.sm.exceptionhandler.annotation.HandleExceptions;
import com.sm.exceptionhandler.exception.FileNotExistException;
import com.sm.exceptionhandler.exception.UserNotExistException;
import com.sm.exceptionhandler.model.ErrorDetail;

@Component
public class RaisedException {

    public void testOne() {
        System.out.println("TestOne");
    }

    @HandleExceptions({
        @HandleException(exceptionType = FileNotExistException.class, handlers = {
            "defaultExceptionHandler", "logExceptionHandler",
            "JMSExceptionHandler"}),
        @HandleException(exceptionType = UserNotExistException.class, handlers = {
            "logExceptionHandler", "JMSExceptionHandler"}),
        @HandleException(exceptionType = RuntimeException.class, handlers = {
                "logExceptionHandler", "JMSExceptionHandler"})
    })
    public Integer testExceptionOne(int type) {
        if (type == 0) {
            ErrorDetail errorDetail = new ErrorDetail.ErrorDetailsBuilder()
                    .setErrorCode("S001").setLayer("B")
                    .setErrorDesc("Failed to process Business Logic").build();
            throw new FileNotExistException(errorDetail);
        } else if (type == 1) {
            ErrorDetail errorDetail = new ErrorDetail.ErrorDetailsBuilder()
                    .setErrorCode("S002").setLayer("B")
                    .setErrorDesc("User not exist.").build();
            throw new UserNotExistException(errorDetail);
        } else {
            throw new RuntimeException("General Runtime  Exception");
        }
    }

    @HandleException(handlers = {"fileExceptionHandler",
        "defaultExceptionHandler", "logExceptionHandler"})
    public void testExceptionTwo() {
        throw new RuntimeException("IO Exception");
    }

    @HandleException
    public void testExceptionThree() throws IOException {
//        throw new UserNotExistException("User not exist Exception");
        int a = 1/0;
    }

    public void exampleOne() {
        try {

			//throw new FileNotExistException();
			//throw new UserNotExistException("User not Exist.");
			//throw new Exception("User not Exist.");
        } catch (FileNotExistException fex) {
			//To do 

			// want to log exception
            // want to send error details to some jms queue
            // want to send mail 
            // want to do some default work 
        } catch (UserNotExistException fex) {
			//To do 

			// want to log exception
            // want to do some default work 
        } catch (Exception ex) {
			//To do 

			// want to do some default  work 
        }

    }
}
