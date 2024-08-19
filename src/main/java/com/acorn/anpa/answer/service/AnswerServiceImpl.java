package com.acorn.anpa.answer.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.answer.domain.Answer;
import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements PLog, AnswerService {

	@Autowired
	AnswerMapper answerMapper;

	public AnswerServiceImpl() {}
	
	@Override
	public int doSave(Answer inVO) throws SQLException {
		return answerMapper.doSave(inVO);
	}

	@Override
	public Answer doSelectOne(Answer inVO) throws SQLException {
		return null;
	}

	@Override
	public List<Answer> doRetrieve(DTO search) throws SQLException {
		log.debug("1. param : " + search);
		return answerMapper.doRetrieve(search);
	}

	@Override
	public int doUpdate(Answer inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		return answerMapper.doUpdate(inVO);
	}

	@Override
	public int doDelete(Answer inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		return answerMapper.doDelete(inVO);
	}
	
	
}
