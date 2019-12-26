package cn.pengan.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Logg {

    @Pointcut("execution(* cn.pengan.service.impl.*.*(..))")
    private void pt(){}

//    @After("pt()")
//    public void printAfterLogg() {
//        System.out.println("after...");
//    }
//
//    @AfterReturning("pt()")
//    public void printAfterReturningLogg() {
//        System.out.printf("after-returning...");
//    }
//    @AfterThrowing("pt()")
//    public void printAfterThrowingLogg() {
//        System.out.printf("after-throwing...");
//    }
//
//    @Before("pt()")
//    public void printBeforeLogg() {
//        System.out.println("Before...");
//    }

    @Around("pt()")
    public Object printAroundLogger(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        System.out.println("printAroundLogger.....");
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println("before......");
            result = proceedingJoinPoint.proceed(args);
            System.out.println("after.......");
        } catch (Throwable throwable) {
            System.out.println("throwing.....");
            throwable.printStackTrace();
        } finally {
            System.out.println("after-returning.....");
        }
        return result;
    }
}
