package com.pcwk.ehr.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.code.domain.Code;

@Mapper
public interface CodeMapper extends WorkDiv<Code> {

	/**
	 * 코드 단건 조회
	 * @param code(masterCode, subCode)
	 * @return (bigList, midList)
	 * @throws SQLException
	 */
	List<Code> doSelectCode(Code code);
}
