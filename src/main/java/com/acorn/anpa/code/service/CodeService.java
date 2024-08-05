package com.acorn.anpa.code.service;

import java.sql.SQLException;
import java.util.List;

import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.code.domain.Code;

public interface CodeService extends WorkDiv<Code>{
	
	/**
	 * 코드 단건 조회
	 * @param code(masterCode, subCode)
	 * @return (bigList, midList)
	 * @throws SQLException
	 */
	List<Code> doSelectCode(Code code) throws SQLException;
	
	/**
	 * 10 -> 대분류 , 20 -> 소분류
	 * @param search
	 * @return
	 * @throws SQLException
	 */
	List<Code> codeList(Search search) throws SQLException;
	
}
