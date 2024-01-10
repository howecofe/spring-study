package com.fastcampus.ch3;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest extends TestCase {
    @Autowired
    A1Dao a1Dao;

    @Autowired
    DataSource ds;

    @Test
    public void insertTest() throws Exception {
        a1Dao.deleteAll();

        // TxManager 생성
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());

        try {
            // Tx 시작
            a1Dao.insert(1, 100);
            a1Dao.insert(2, 200);
            tm.commit(status); // 성공 - Tx 끝
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status); // 실패 - Tx 끝
        } finally {
        }
    }
}