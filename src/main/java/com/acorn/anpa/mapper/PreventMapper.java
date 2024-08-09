package com.acorn.anpa.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acorn.anpa.prevent.domain.prevent;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.WorkDiv;

@Mapper
public interface PreventMapper extends WorkDiv<prevent> {

    /**
     * 단건 조회
     * @param preventSeq 조회할 prevent의 시퀀스 번호
     * @return prevent 객체
     * @throws SQLException
     */
    prevent doSelectOne(@Param("preventSeq") int preventSeq) throws SQLException;

    /**
     * 리스트 조회
     * @param search 검색 조건
     * @return prevent 객체 리스트
     * @throws SQLException
     */
    List<prevent> doRetrieve(Search search) throws SQLException;

    /**
     * 마지막 시퀀스 번호 가져오기
     * @return 시퀀스 번호
     */
    int getSequence();
}
