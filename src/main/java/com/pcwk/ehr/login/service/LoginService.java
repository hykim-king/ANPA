package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.member.domain.Member;

public interface LoginService {

	/**
	 * id/비밀번호 체크
	 * @param inVO
	 * @return 10(ID없음),20(비번 불일치),30(ID,비번 일치)
	 * @throws SQLException
	 */
	int idPasswordCheck(Login inVO) throws SQLException;
	
	
	/**
	 * 회원정보 조회
	 * @param inVO
	 * @return Member
	 * @throws SQLException
	 */
	Member loginInfo(Login inVO) throws SQLException;
	
}
