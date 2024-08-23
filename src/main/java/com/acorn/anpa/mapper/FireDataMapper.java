package com.acorn.anpa.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.cmn.Search;
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
	
	/**
	 * 화재통계
	 * @param search
	 * @return Firedata
	 * @throws SQLException
	 */
	Firedata totalData(Search search) throws SQLException;

	/**
	 * 메인 화재통계
	 * @param 
	 * @return Firedata
	 * @throws SQLException
	 */
	Firedata doMainData() throws SQLException;
	
	/**
	 * 화재 통계 테이블 자료
	 * @param search
	 * @return
	 * @throws SQLException
	 */
	List<Firedata> totalDataList(Search search) throws SQLException;
	
	/**
	 * 데이터 존재 기간 최소 맥스
	 */
	Search minMaxDate() throws SQLException;
}
