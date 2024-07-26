package com.pcwk.ehr.code.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.code.domain.Code;
import com.pcwk.ehr.mapper.CodeMapper;

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
	
}
