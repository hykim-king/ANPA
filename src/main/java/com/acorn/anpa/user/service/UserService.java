package com.acorn.anpa.user.service;

import java.sql.SQLException;
import com.acorn.anpa.member.domain.Member;

public interface UserService {

	/**
	 * 회원정보수정
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int doUpdate(Member inVO) throws SQLException;
	
	/**
	 * 회원정보 조회
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	Member doSelectOne(Member inVO) throws SQLException;
	
	/**
	 * 단건 삭제
	 * @param user
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doDelete(Member inVO) throws SQLException;
	
	/**
	 * 단건 등록
	 * @param user
	 * @return 1(성공)/0(실패)
	 * @throws SQLException
	 */
	int doSave(Member inVO) throws SQLException;
	
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
	 * email중복 체크 
	 * @param inVO
	 * @return 1(사용불가)/0(사용가능)
	 * @throws SQLException
	 */
	int emailDuplicateCheck(Member inVO) throws SQLException;

	 /**
     * 아이디 찾기
     * - 사용자 이름과 이메일을 기반으로 아이디를 찾음
     * @param userName
     * @param email
     * @return String 찾은 아이디
     * @throws SQLException
     */
	String findUserId(Member member) throws SQLException;

	/**
     * 비밀번호 찾기
     * - 사용자 ID, 이름, 이메일을 기반으로 비밀번호를 찾음
     * @param userId
     * @param userName
     * @param email
     * @return String 찾은 비밀번호(보안상 권장되지 않음)
     * @throws SQLException
     */
	int findPassword(Member inVO) throws SQLException;
	
	/**
	 * 비밀번호 초기화
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int passwordUpdate(Member inVO) throws SQLException;
	
    /**
     * 모든 회원 삭제 (테스트 용도)
     * @throws SQLException
     */
    void deleteAll() throws SQLException;

	void deleteUser(String userId);

}
