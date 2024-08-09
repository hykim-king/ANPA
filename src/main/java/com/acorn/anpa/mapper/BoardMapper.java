package com.acorn.anpa.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.WorkDiv;

@Mapper
public interface BoardMapper extends WorkDiv<Board> {

	/**
	 * 테스트용 시퀀스 찾기
	 * @return
	 * @throws SQLException
	 */
	int getSequence() throws SQLException;
	
	/**
	 * 테스트용 데이터 전체삭제
	 * @return
	 * @throws SQLException
	 */
	int deleteAll() throws SQLException;
	
	/**
	 * 테스트용 101건 데이터 주입
	 * @return
	 * @throws SQLException
	 */
	int multipleSave() throws SQLException;
	
	/**
	 * 조회시 조회수 증가
	 * @return
	 * @throws SQLException
	 */
	int readCntUpdate(Board inVO) throws SQLException;
	
}
