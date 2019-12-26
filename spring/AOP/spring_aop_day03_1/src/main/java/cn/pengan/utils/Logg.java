package cn.pengan.utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class Logg {
    public void printAfterLogg() {
        System.out.println("after...");
    }

    public void printAfterReturningLogg() {
        System.out.printf("after-returning...");
    }

    public void printAfterThrowingLogg() {
        System.out.printf("after-throwing...");
    }

    public void printBeforeLogg() {
        System.out.println("Before...");
    }

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
