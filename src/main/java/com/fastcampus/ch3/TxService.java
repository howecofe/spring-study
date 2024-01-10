package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;

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
}
