package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.prevent.domain.prevent;

public interface PreventService {

    /**
     * Prevent 단건 조회
     * @param preventSeq 조회할 Prevent의 시퀀스 번호
     * @return Prevent 객체
     * @throws SQLException
     */
    prevent doSelectOne(int preventSeq) throws SQLException;

//    /**
//     * Prevent 리스트 조회
//     * @param search 검색 조건
//     * @return Prevent 객체 리스트
//     * @throws SQLException
//     */
//    List<prevent> doRetrieve(Search search) throws SQLException;
//    
//    /**
//     * Prevent 리스트 조회
//     * @param search 검색 조건
//     * @return Prevent 객체 리스트
//     * @throws SQLException
//     */
//    List<prevent> doRetrieve(Search search) throws SQLException;

}
