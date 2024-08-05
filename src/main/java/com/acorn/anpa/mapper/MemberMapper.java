package com.acorn.anpa.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.member.domain.Member;

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