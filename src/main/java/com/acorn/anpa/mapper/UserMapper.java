package com.acorn.anpa.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.member.domain.Member;

@Mapper
public interface UserMapper extends WorkDiv<Member> {

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
	 * id확인
	 * 
	 * @param inVO
	 * @return 1/0
	 * @throws SQLException
	 */
	int idCheck(Member inVO) throws SQLException;

	/**
	 * 비밀번호 확인
	 * 
	 * @param inVO
	 * @return 1/0
	 * @throws SQLException
	 */
	int passwordCheck(Member inVO) throws SQLException;

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
	 * 
	 * @param userName
	 * @param email
	 * @param tel 
	 * @return String
	 * @throws SQLException
	 */
	String findUserId(Member member) throws SQLException;
		
	 /**
	 * 비밀번호 찾기
	 * 
	 * @param userId
	 * @param userName
	 * @param email
	 * @return String
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
	 * 모든 회원 정보 삭제 (테스트용)
	 * 
	 * @throws SQLException
	 */
	void deleteAll() throws SQLException;

	void insert(Member user01);
}
