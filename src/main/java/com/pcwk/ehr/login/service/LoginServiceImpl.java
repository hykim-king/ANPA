package com.pcwk.ehr.login.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.login.domain.Login;
import com.pcwk.ehr.mapper.LoginMapper;
import com.pcwk.ehr.member.domain.Member;

@Service
public class LoginServiceImpl implements LoginService, PLog {

	@Autowired
	LoginMapper loginMapper;
	
	public LoginServiceImpl() {} // 깡통 생성
	
	// 로그인 과정
	@Override
	public int idPasswordCheck(Login inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		
		int status = 0;
		//1. id체크 호출  : 1성공 / 0 실패
		int flag = loginMapper.idCheck(inVO);
		if(0==flag) {
			status = 10;
			log.debug("2. status : " + status);
			return status;
		}else {
			log.debug("2.1 id 오류 발생");			
		}		
		
		//2. 비번체크 : 1성공 / 0 실패
		flag = loginMapper.passwordCheck(inVO);
		if(0==flag) {
			status = 20;
			log.debug("2. status : " + status);
			return status;			
		}else {
			log.debug("2.2 비번 오류 발생");			
		}		
		
		status = 30; // 로그인 성공
		log.debug("2. status : " + status);
		
		return status;
	}

	// 로그인 정보 받기
	@Override
	public Member loginInfo(Login inVO) throws SQLException {
		log.debug("1. param : " + inVO);
		
		Member loginInfo = loginMapper.loginInfo(inVO);
		log.debug("2. loginInfo : " + loginInfo);
		
		return loginInfo;
	}

}
