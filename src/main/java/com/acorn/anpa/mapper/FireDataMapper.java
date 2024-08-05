package com.acorn.anpa.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.firedata.domain.Firedata;

@Mapper
public interface FireDataMapper extends WorkDiv<Firedata> {
	
	/*
	 * 테스트용 데이터 삭제
	 * @param regId
	 * @return
	 * @throws SQLException
	 * */
	int doDeleteTest(Firedata inVO) throws SQLException; // 테스트용 단건 삭제
	
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
	
	/*
	 * 다건 데이터 등록
	 * @return
	 * @throws SQLException
	 */
	int multipleSave() throws SQLException;	
}
