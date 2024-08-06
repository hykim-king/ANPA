package com.acorn.anpa.code.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.mapper.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService,PLog{
	
	@Autowired
	CodeMapper codeMapper;

	public CodeServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│ CodeServiceImpl()");
		log.debug("└─────────────────────────");
	}
	
	@Override
	public List<Code> doRetrieve(DTO search) throws SQLException{
		log.debug("1. param : "+search);
		
		return codeMapper.doRetrieve(search);
	}

	@Override
	public int doSave(Code inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Code doSelectOne(Code inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(Code inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(Code inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Code> doSelectCode(Code code) throws SQLException {
		
		return codeMapper.doSelectCode(code);
	}

	@Override
	public List<Code> codeList(Search search) throws SQLException {
		
		return codeMapper.codeList(search);
	}
	
}
