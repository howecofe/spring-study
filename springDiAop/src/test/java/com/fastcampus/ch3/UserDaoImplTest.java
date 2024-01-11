package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
    @Autowired UserDao userDao;

    @Test
    public void deleteUser() throws Exception {
        // 모든 user delete 후 deleteUser 테스트
        userDao.deleteAll();
        int rowCnt = userDao.deleteUser("asdf");
        assertTrue(rowCnt==0);

        // insert
        User user = new User("asdf", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1); // insert 성공 결과 1

        // delete
        rowCnt = userDao.deleteUser(user.getId());
        assertTrue(rowCnt==1); // delete 성공 결과 1

        // select
        assertTrue(userDao.selectUser(user.getId()) == null);
    }

    @Test
    public void selectUser() throws Exception {
        userDao.deleteAll();

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2024,0,7);

        // insert
        User user = new User("asdf", "1234", "abc", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "fb", new Date());
        int rowCnt = userDao.insertUser(user);

        // select
        User user2 = userDao.selectUser(user.getId());
        assertTrue(user.equals(user2));
    }

    @Test
    public void insertUser() throws Exception {
        userDao.deleteAll();

        User user = new User("asdf", "1234", "abc", "aaa@aaa.com", new Date(), "fb", new Date());
        int rowCnt = userDao.insertUser(user);

        assertTrue(rowCnt==1); // 실패하면 rowCnt==0
    }

    @Test
    public void updateUser() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2024,0,7);

        // delete
        userDao.deleteAll();

        // insert
        User user = new User("asdf", "1234", "abc", "aaa@aaa.com", new Date(cal.getTimeInMillis()), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        // update
        user.setPwd("4321");
        user.setEmail("bbb@bbb.com");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt == 1);

        // select
        User user2 = userDao.selectUser(user.getId());
        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);
        assertTrue(user.equals(user2));
    }
}