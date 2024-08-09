package com.acorn.anpa.user.service;

import java.sql.SQLException;
import java.util.List;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.member.domain.Member;

public interface UserService {

    /**
     * 로그인 처리
     * @param userId
     * @param password
     * @return Member 로그인 성공 시 사용자 정보 반환
     * @throws SQLException
     */
    Member login(String userId, String password) throws SQLException;

    /**
     * 회원가입 처리
     * - 가입 전에 ID 중복 확인을 하고, 필요 시 추가적인 비즈니스 로직을 수행할 수 있음.
     * @param member
     * @return int 1(성공)/0(실패)
     * @throws SQLException
     */
    int signUp(Member member) throws SQLException;
    
    /**
     * ID 중복 체크
     * @param userId
     * @return boolean true(중복됨)/false(사용 가능)
     * @throws SQLException
     */
    boolean isUserIdAvailable(String userId) throws SQLException;

    /**
     * 아이디 찾기
     * - 사용자 이름과 이메일을 기반으로 아이디를 찾음
     * @param userName
     * @param email
     * @return String 찾은 아이디
     * @throws SQLException
     */
    String findUserId(String userName, String email) throws SQLException;

    /**
     * 비밀번호 찾기
     * - 사용자 ID, 이름, 이메일을 기반으로 비밀번호를 찾음
     * @param userId
     * @param userName
     * @param email
     * @return String 찾은 비밀번호(보안상 권장되지 않음)
     * @throws SQLException
     */
    String findPassword(String userId, String userName, String email) throws SQLException;

    /**
     * 모든 회원 삭제 (테스트 용도)
     * @throws SQLException
     */
    void deleteAll() throws SQLException;
}
