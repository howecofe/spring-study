package com.fastcampus.ch3.aop;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AopMain {
    public static void main(String[] args) throws Exception {
        MyAdvice myAdvice = new MyAdvice();

        // Reflection API 사용
        // : 구체적인 클래스 타입을 알지 못해도 그 클래스의 정보(메서드, 타입, 변수 등등)에 접근할 수 있게 해주는 자바 API
        Class myClass = Class.forName("com.fastcampus.ch3.aop.MyClass");
        Object obj = myClass.newInstance();

        for (Method m : myClass.getDeclaredMethods()) {
            myAdvice.invoke(m, obj, null);
        }
    }
}

class MyAdvice {
    Pattern p = Pattern.compile("a.*"); // advice 추가하려는 곳을 지정하기 위한 패턴 생성
    boolean matches(Method m) {
        Matcher matcher = p.matcher(m.getName());
        return matcher.matches();
    }

    void invoke(Method m, Object obj, Object... args) throws Exception {
        if (matches(m)) {
            System.out.println("[before]{");
        }
        if (m.getAnnotation(Transactional.class) != null) { // @Transactional 붙은 메서드만 advice 코드 추가하고 싶은 경우
            System.out.println("@Transactional 어노테이션이 붙었네요!");
        }
        m.invoke(obj, args); // aaa(), aaa2(), bbb() 호출 가능
        if (matches(m)) {
            System.out.println("}[after]");
        }
    }
}

class MyClass {
    @Transactional
    public void aaa() {
        System.out.println("aaa() is called.");
    }

    void aaa2() {
        System.out.println("aaa2() is called.");
    }

    void bbb() {
        System.out.println("bbb() is called.");
    }
}