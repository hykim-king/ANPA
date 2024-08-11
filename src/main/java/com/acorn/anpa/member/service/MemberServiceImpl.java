package com.acorn.anpa.member.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.mapper.CodeMapper;
import com.acorn.anpa.mapper.MemberMapper;
import com.acorn.anpa.member.domain.Member;

@Service
public class MemberServiceImpl implements MemberService,PLog{
	
	@Autowired
	MemberMapper memberMapper;

	public MemberServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│ MemberServiceImpl()");
		log.debug("└─────────────────────────");
	}

	@Override
	public int doSave(Member inVO) throws SQLException {
		// TODO Auto-generated method stub
		return memberMapper.doSave(inVO);
	}

	@Override
	public Member doSelectOne(Member inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> doRetrieve(DTO search) throws SQLException {
		// TODO Auto-generated method stub
		return memberMapper.doRetrieve(search);
	}

	@Override
	public int doUpdate(Member inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(Member inVO) throws SQLException {
		// TODO Auto-generated method stub
		return memberMapper.doDelete(inVO);
	}
	
	
	
}
