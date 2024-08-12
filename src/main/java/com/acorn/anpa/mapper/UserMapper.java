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
     * @param userId
     * @param password
     * @return Member
     * @throws SQLException
     */
    Member login(@Param("userId") String userId, @Param("password") String password) throws SQLException;

    /**
     * 회원가입
     * @param member
     * @return int
     * @throws SQLException
     */
    int signUp(Member member) throws SQLException;
    
    /**
     * id중복 체크 
     * @param userId
     * @return 1(사용불가)/0(사용가능)
     * @throws SQLException
     */
    int idCheck(@Param("userId") String userId) throws SQLException;
    
    /**
     * 아이디 찾기
     * @param userName
     * @param email
     * @return String
     * @throws SQLException
     */
    String findUserId(@Param("userName") String userName, @Param("email") String email) throws SQLException;

    /**
     * 비밀번호 찾기
     * @param userId
     * @param userName
     * @param email
     * @return String
     * @throws SQLException
     */
    String findPassword(@Param("userId") String userId, @Param("userName") String userName, @Param("email") String email) throws SQLException;

    /**
     * 비밀번호 재설정
     * @param userId
     * @param newPassword
     * @return int (업데이트된 행 수)
     * @throws SQLException
     */
    String resetPassword(@Param("userId") String userId, @Param("newPassword") String newPassword) throws SQLException;


    /**
     * 모든 회원 정보 삭제 (테스트용)
     * @throws SQLException
     */
    void deleteAll() throws SQLException;
}
