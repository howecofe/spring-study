package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class A1Dao {
    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. connection을 가져온다.
//            conn = ds.getConnection();
            conn = DataSourceUtils.getConnection(ds);
            System.out.println("conn = " + conn);
            // 2. pstmt에 쿼리를 저장한다.
            pstmt = conn.prepareStatement("insert into a1 values(?, ?)");
            // 3. prepareStatement의 물음표를 채운다.
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            // 3. DB에 쿼리를 날린다.
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
//            close(conn, pstmt); conn 닫으면 Tx 종료
            close(pstmt);
            DataSourceUtils.releaseConnection(conn, ds);
        }
    }

    public void deleteAll() throws Exception {
//        Connection conn = DataSourceUtils.getConnection(ds);
        Connection conn = ds.getConnection();
        System.out.println("deleteAll() conn = " + conn);
        String sql = "delete from a1 ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        close(pstmt, conn);
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}
