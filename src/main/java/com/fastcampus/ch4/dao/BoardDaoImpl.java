package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired SqlSession session;
    String namespace = "com.fastcampus.ch4.dao.BoardMapper.";
    @Override
    public BoardDto select(int bno) throws Exception { // 예외를 서비스계층으로 보고해야하므로 thorws
        return session.selectOne(namespace+"select", bno);
    }

}
