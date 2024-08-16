package com.acorn.anpa.user.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.PLog; 
import com.acorn.anpa.member.domain.Member;
import com.acorn.anpa.mapper.UserMapper; 

@Service
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
		
		//1. idCheck호출 : 1 성공/0 실패 -> 10
		
		int loginCnt = userMapper.idCheck(inVO);
		if(0 == loginCnt) {
			status = 10;
			return status;
		}
		
		//2. 비번확인 : 1 성공/0 실패 -> 20
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

	/*
	 * @Override public int signUp(Member member) throws SQLException {
	 * log.debug("1. param :" + member); int result = userMapper.signUp(member);
	 * log.debug("2. signUp result :" + result); return result; }
	 * 
	 * @Override public boolean idCheck(String userId) throws SQLException {
	 * log.debug("1. param :" + userId); int count = userMapper.idCheck(userId);
	 * log.debug("2. idCheck count :" + count); return count > 0; }
	 * 
	 */
    
    @Override
    public void deleteAll() throws SQLException {
        log.debug("Deleting all members.");
        userMapper.deleteAll();
    }

	@Override
	public int signUp(Member member) throws SQLException {

		return 0;
	}

	@Override
	public boolean idCheck(String userId) throws SQLException {

		return false;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
	}
}
