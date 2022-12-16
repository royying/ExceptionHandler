package com.sm.exceptionhandler.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sm.exceptionhandler.ExceptionHandlerFramework;
import com.sm.exceptionhandler.annotation.HandleException;
import com.sm.exceptionhandler.annotation.HandleExceptions;
import com.sm.exceptionhandler.exception.BaseException;

@Aspect
@Component
public class HandleExceptionAspect {

    @Autowired
    private ExceptionHandlerFramework<BaseException> exceptionHandlerFramework;

    /**
     * Inspect only those methods annotated with @HandleException
     *
     * @param pjp Process Join Points
     * @param handleExceptionAnnotation HandleException Annotation
     * @throws Throwable
     */
    /*
     * @Around(
     * "execution(@com.sm.exceptionhandler.annotation.HandleException * *(..)) && @annotation(handleExceptionAnnotation)"
     * ) public Object aspectToHandleException(final ProceedingJoinPoint pjp,
     * final HandleException handleExceptionAnnotation) throws Throwable { try {
     * System.out.println("Inside Around"); return pjp.proceed(); } catch
     * (Exception e) { System.out.println("Handled"); if (e instanceof
     * BaseException) { System.out.println("Error in service " +
     * pjp.getSignature() + " with the arguments: " + pjp.getArgs().length +
     * "--" + pjp.getClass());
     * exceptionHandlerFramework.handleException((BaseException) e,
     * handleExceptionAnnotation.handlers()); } else {
     * exceptionHandlerFramework.handleException(new BaseException(e),
     * handleExceptionAnnotation.handlers()); } } return null; }
     */
    /**
     * Called while method annotated with @HanldeExceptions to handle multiple exception
     *
     * @param joinPoint
     * @param exp
     * @param handleExceptionsAnnotation
     */
    @AfterThrowing(value = "execution(@com.sm.exceptionhandler.annotation.HandleExceptions * *(..)) && @annotation(handleExceptionsAnnotation) ", throwing = "exp")
//    @Around(value = "execution(@com.sm.exceptionhandler.annotation.HandleExceptions * *(..)) && @annotation(handleExceptionsAnnotation) ", throwing = "exp")
    public void aspectToHandleMultipleExceptions(final JoinPoint joinPoint,
            final Exception exp,
            final HandleExceptions handleExceptionsAnnotation){

        HandleException[] handleExceptionArr = handleExceptionsAnnotation
                .value();
        for (HandleException handelException : handleExceptionArr) {
            Class<?> clazz = handelException.exceptionType();
            if (exp.getClass().equals(clazz) || clazz.isInstance(exp)) {
                handleException(exp, handelException);
                break;
            }
        }
        joinPoint.getSourceLocation();
    }

//    @Around(value = "execution(@com.sm.exceptionhandler.annotation.HandleExceptions * *(..)) && @annotation(handleExceptionsAnnotation) ")
//    public int aspectToHandleMultipleExceptions(final ProceedingJoinPoint pjp,
//                                                 final HandleExceptions handleExceptionsAnnotation){
//        try {
//            pjp.proceed();
//        }catch (Throwable e){
//            System.out.println("Catch sthsssss");
//            return 300;
//        }
//
//        return pjp.
//
////        HandleException[] handleExceptionArr = handleExceptionsAnnotation
////                .value();
////        for (HandleException e:handleExceptionArr) {
////            System.out.println(e.exceptionType());
////            handleException(e.getClass(),e);
////        }
//    }

    /**
     * Called while method annotated with @HanldeException to handle single exception
     * @param joinPoint
     * @param exp
     * @param handleExceptionAnnotation 
     */
    @AfterThrowing(value = "execution(@com.sm.exceptionhandler.annotation.HandleException * *(..)) && @annotation(handleExceptionAnnotation)   ", throwing = "exp")
//    @Around(value = "execution(@com.sm.exceptionhandler.annotation.HandleException * *(..)) && @annotation(handleExceptionAnnotation)   ", throwing = "exp")
    public void aspectToHandleSingleException(final JoinPoint joinPoint,
            final Exception exp, final HandleException handleExceptionAnnotation){
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSourceLocation());
        handleException(exp, handleExceptionAnnotation);
    }


//    @Around(value = "execution(@com.sm.exceptionhandler.annotation.HandleException * *(..)) && @annotation(handleExceptionAnnotation)   ")
//    public void aspectToHandleSingleException(final ProceedingJoinPoint pjp,
//                                              final HandleException handleExceptionAnnotation){
//        try {
//            pjp.proceed();
//        }catch (Throwable e){
//            System.out.println("Catch sth");
//        }
////        handleException(exp, handleExceptionAnnotation);
//    }

    private void handleException(final Exception exp,
            HandleException handelException) {
        BaseException baseException = null;
        if (exp instanceof BaseException) {

            baseException = (BaseException) exp;

        } else {
            baseException = new BaseException(exp);
        }

        exceptionHandlerFramework.handleException(baseException,
                handelException.handlers());
    }
}
