package com.fastcampus.ch3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAdvice {
    // execution(반환타입 패키지명.클래스명.메서드명(매개변수 목록))
    // : advice가 추가될 메서드를 지정하기 위한 패턴
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.add*(..))") // pointcut - 부가기능이 적용될 메서드의 패턴
    public Object methodCalllog(ProceedingJoinPoint pjp) throws Throwable {

        // Before Advice
        long start = System.currentTimeMillis(); // start time
        System.out.println("<<[start] " + pjp.getSignature().getName() // 메서드명
                + Arrays.toString(pjp.getArgs())); // 메서드 매개변수

        Object result = pjp.proceed(); // target의 메서드 호출

        // After Advice
        System.out.println("result = " + result);
        System.out.println("[end]>> " + (System.currentTimeMillis() - start) + "ms");

        return result;
    }
}
