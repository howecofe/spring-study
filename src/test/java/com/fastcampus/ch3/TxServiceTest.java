package com.fastcampus.ch3;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TxServiceTest extends TestCase {
    @Autowired TxService txService;

    @Test
    public void insertA1DaoWithoutTxTest() throws Exception {
        txService.deleteAllA1Dao();

        txService.insertA1DaoWithoutTx();
    }

    @Test
    public void insertA1DaoWithTxFailTest() throws Exception {
        txService.deleteAllA1Dao();

        txService.insertA1DaoWithTxFail();
    }

    @Test
    public void insertA1DaoWithTxSuccessTest() throws Exception {
        txService.deleteAllA1Dao();

        txService.insertA1DaoWithTxSuccess();
    }
}