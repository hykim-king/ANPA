package com.acorn.anpa.user.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.PLog; 
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.mapper.UserMapper; 

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, PLog {

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {

    }

    @Override
    public Member login(Member inVO) throws SQLException {
        log.debug("1. param :" + inVO);
        Member member = userMapper.login(inVO);
        log.debug("2. login info :" + member);
        return member;
    }

    @Override
	public int idPasswordCheck(Member inVO) throws SQLException {
		log.debug("1. param :"+inVO);
		int status = 0;
		
		// idCheck : 1 성공/0 실패 -> 10
		
		int loginCnt = userMapper.idCheck(inVO);
		if(0 == loginCnt) {
			status = 10;
			return status;
		}
		
		// passwordCheck : 1 성공/0 실패 -> 20
		int passwordCnt = userMapper.passwordCheck(inVO);
		if(0 == passwordCnt) {
			status = 20;
			return status;
		}
		
		status = 30;
		log.debug("2. status :"+status);
		return status;
	}

    @Override
    public Member loginInfo(Member inVO) throws SQLException {
        log.debug("1. param :" + inVO);
        Member loginInfo = userMapper.loginInfo(inVO);
        log.debug("2. loginInfo :" + loginInfo);
        return loginInfo;
    }	
    
    @Override
    public int signUp(Member member) throws SQLException {
        int flag = 0;
        log.debug(member);
        flag = userMapper.signUp(member);
        log.debug("flag:" + flag);
        return flag;
    }
	
	@Override
	public int idDuplicateCheck(Member inVO) throws SQLException {
		log.debug("1. param :"+inVO);
		
		int count = userMapper.idDuplicateCheck(inVO);
		
		log.debug("2. count :"+count);
		return count;
	}

	 @Override
	    public String findUserId(Member member) {
	        try {
	            return userMapper.findUserId(member);
	        } catch (SQLException e) {
	            // 예외 처리를 위한 로깅이나 사용자 정의 예외 발생
	            throw new RuntimeException("아이디 찾기 중 오류 발생", e);
	        }
	    }

	    @Override
	    public String findPassword(Member member) {
	        try {
	            return userMapper.findPassword(member);
	        } catch (SQLException e) {
	            // 예외 처리를 위한 로깅이나 사용자 정의 예외 발생
	            throw new RuntimeException("비밀번호 찾기 중 오류 발생", e);
	        }
	    }
	
	@Override
	public void deleteUser(String userId) {
		
	}
	
	@Override
    public void deleteAll() throws SQLException {
        log.debug("Deleting all members.");
        userMapper.deleteAll();
    }

}
