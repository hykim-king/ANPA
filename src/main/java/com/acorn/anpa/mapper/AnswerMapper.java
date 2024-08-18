package com.acorn.anpa.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.answer.domain.Answer;
import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.WorkDiv;

@Mapper
public interface AnswerMapper extends WorkDiv<Answer> {

	/**
	 * 테스트용 시퀀스 찾기
	 * @return
	 * @throws SQLException
	 */
	int getSequence() throws SQLException;
	
}
