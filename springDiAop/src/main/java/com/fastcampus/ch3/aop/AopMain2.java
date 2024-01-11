package com.fastcampus.ch3.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context_aop.xml");
        MyMath mm = (MyMath) ac.getBean("myMath");
        mm.add(3, 5);
        mm.add(1, 2, 3);
        System.out.println("mm.subtract(1, 2) = " + mm.subtract(1, 2));
        System.out.println("mm.multifly(3, 5) = " + mm.multifly(3, 5));
    }
}
