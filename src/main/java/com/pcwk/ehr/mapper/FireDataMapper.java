package com.pcwk.ehr.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.firedata.domain.Firedata;

@Mapper
public interface FireDataMapper extends WorkDiv<Firedata> {
	/*
	 * 테스트용 전체 데이터 삭제
	 * @return
	 * @throws SQLException
	 * */
	int deleteAll() throws SQLException; // 테스트용 전체 데이터 삭제 끝
	
	/*
	 * 최신 sequence 조회
	 * @return
	 * @throws SQLException
	 * */
	int getSequence() throws SQLException;
}
