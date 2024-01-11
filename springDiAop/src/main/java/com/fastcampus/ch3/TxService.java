package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;
    @Autowired
    DataSource ds;

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1DaoWithTx() throws Exception {
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = tm.getTransaction(txd);

        // Tx 시작
        try {
            a1Dao.insert(1, 100); // 성공
            insertB1DaoWithTx();
            a1Dao.insert(2, 200); // 성공
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {
        }
    }

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1DaoWithTx() throws Exception {
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = tm.getTransaction(txd);

        // Tx 시작
        try {
            b1Dao.insert(1, 100); // 성공
            b1Dao.insert(1, 200); // 실패
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        } finally {
        }
    }

    public void insertA1DaoWithoutTx() throws Exception {
        a1Dao.insert(1, 100); // 성공
        a1Dao.insert(1, 200); // 실패
    }

    // @Transactional : RuntimeException, Error만 rollback
    // 따라서 @Transactional(rollbackFor = Exception.class)를 명시해야 Exception과 그 자손들을 rollback할 수 있어진다.
    @Transactional(rollbackFor = Exception.class) // aop가 자동으로 TxManager 코드 주입
//    @Transactional
    public void insertA1DaoWithTxFail() throws Exception {
        a1Dao.insert(1, 100); // 성공
//        throw new RuntimeException(); // @Transactional 만 명시해도 rollback 된다.
//        throw new Exception(); // @Transactional(rollbackFor = Exception.class)를 명시해야 rollback 된다.
        a1Dao.insert(1, 200); // 실패
    }

    @Transactional // aop가 자동으로 TxManager 코드 주입
    public void insertA1DaoWithTxSuccess() throws Exception {
        a1Dao.insert(1, 100); // 성공
        a1Dao.insert(2, 200); // 성공
    }

    public void deleteAllA1Dao() throws Exception {
        a1Dao.deleteAll();
    }

    public void deleteAllB1Dao() throws Exception {
        b1Dao.deleteAll();
    }
}
