package com.acorn.anpa.user.service;

import java.sql.SQLException;
import com.acorn.anpa.member.domain.Member;

public interface UserService {

	/**
	 * 로그인
	 * 
	 * @param userId
	 * @param password
	 * @return Member
	 * @throws SQLException
	 */
	Member login(Member inVO) throws SQLException;

	/**
	 * id/비밀번호 체크
	 * @param inVO
	 * @return 10(ID없음),20(비번 불일치), 30(ID/비번 일치)
	 * @throws SQLException
	 */
	int idPasswordCheck(Member inVO)throws SQLException;

	/**
	 * 회원정보 조회
	 * 
	 * @param inVO
	 * @return Member
	 * @throws SQLException
	 */
	Member loginInfo(Member inVO) throws SQLException;

	/**
	 * 회원가입
	 * 
	 * @param member
	 * @return int
	 * @throws SQLException
	 */
	int signUp(Member member) throws SQLException;
    
	/**
	 * id중복 체크 
	 * @param inVO
	 * @return 1(사용불가)/0(사용가능)
	 * @throws SQLException
	 */
	int idDuplicateCheck(Member inVO) throws SQLException;


    /**
     * 모든 회원 삭제 (테스트 용도)
     * @throws SQLException
     */
    void deleteAll() throws SQLException;

	void deleteUser(String userId);

}
