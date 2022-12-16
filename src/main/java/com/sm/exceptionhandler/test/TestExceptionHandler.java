package com.sm.exceptionhandler.test;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sm.exceptionhandler.SpringApplicationConfig;

public class TestExceptionHandler {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationConfig.class);
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
        RaisedException object = (RaisedException) context.getBean("raisedException");
//        RaisedException object = new RaisedException();

//        int a =1/0;
//        object.testExceptionOne(0);
//        object.testExceptionTwo();
//        object.testExceptionThree();

        try {
            System.out.println("---------------------Example 1----------------------");
            System.out.println(object.testExceptionOne(1));
        } catch (Exception e) {
//            System.out.println(e);
        }

        try {
            System.out.println("---------------------Example 2----------------------");
            object.testExceptionTwo();
        } catch (Exception e) {
//            System.out.println(e);
        }

        try {
            System.out.println("---------------------Example 3----------------------");
            object.testExceptionThree();
        } catch (Exception e) {
//            System.out.println(e);
        }
    }
}
