package com.pcwk.ehr.mapper;

import java.sql.SQLException;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.member.domain.Member;

public interface LoginMapper extends WorkDiv<Login>{

	/**
	 * id확인
	 * @param inVO
	 * @return 1/0
	 * @throws SQLException
	 */
	int idCheck(Login inVO) throws SQLException;
	
	/**
	 * 비밀번호 확인
	 * @param inVO
	 * @return 1/0
	 * @throws SQLException
	 */
	int passwordCheck(Login inVO) throws SQLException;
	
	/**
	 * 회원정보 조회
	 * @param inVO
	 * @return Member
	 * @throws SQLException
	 */
	Member loginInfo(Login inVO) throws SQLException;
	
}
