package org.example.spring.mvc.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class JdbcAspect {
    @Pointcut("execution(* org.example.spring.mvc.jdbc.*.*(..))")
    public void service(){}

    @Before("service()")
    public void beforeService(JoinPoint joinPoint){
        System.out.println("----------------before----------------");
        System.out.print(new Date()+":");
        System.out.println("执行的方法：" + joinPoint.getSignature());
    }

    @After("service()")
    public void afterService(){
        System.out.print(new Date()+":");
        System.out.println("方法执行完毕！");
        System.out.println("----------------after----------------");
    }

}
