package com.sm.exceptionhandler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.sm.exceptionhandler", "com.sm.exceptionhandler.exception.handler"})
public class SpringApplicationConfig {

}
