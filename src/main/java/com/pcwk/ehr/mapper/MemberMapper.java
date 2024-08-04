package com.pcwk.ehr.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.member.domain.Member;

@Mapper
public interface MemberMapper<T> extends WorkDiv<Member>{

	/**
	 * 목록 조회
	 * @param String subCity
	 * @return
	 * @throws SQLException
	 */
	List<T> doRetrieveLocEmail(String subCity) throws SQLException;
	
}
