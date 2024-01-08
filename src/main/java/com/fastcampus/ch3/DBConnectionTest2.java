package com.fastcampus.ch3;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.jdbc.datasource.*;

import javax.sql.*;
import java.sql.*;

public class DBConnectionTest2 {

    public void jdbcConnectionTest() throws Exception {
        // 방법1. 직접 객체 생성해서 DB 연결
//        // 스키마의 이름(springbasic)
//        String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";
//
//        // DB의 userid와 pwd를 알맞게 변경
//        String DB_USER = "greta";
//        String DB_PASSWORD = "qwer1234";
//        String DB_DRIVER = "com.mysql.jdbc.Driver";
//
//        DriverManagerDataSource ds = new DriverManagerDataSource(); // spring jdbc에서 제공
//        ds.setDriverClassName(DB_DRIVER);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_USER);
//        ds.setPassword(DB_PASSWORD);

        // 방법2. root-context.xml에 bean 태그로 DataSource 빈 등록
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
    }
}

/* [root-context.xml]
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd">

	<!-- Root Context: defines shared resources visible to all other web components -->
	<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
		<property name="url" value="jdbc:mysql://localhost:3306/springbasic?useUnicode=true&amp;characterEncoding=utf8"></property>
		<property name="username" value="asdf"></property>
		<property name="password" value="1234"></property>
	</bean>
</beans>
*/