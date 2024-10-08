package com.acorn.anpa.board.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService,PLog{
	
	@Autowired
	BoardMapper boardMapper;
	
	public BoardServiceImpl() {	}

	@Override
	public int doSave(Board inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		
		return boardMapper.doSave(inVO);
	}

	@Override
	public Board doSelectOne(Board inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		
		Board outVO = boardMapper.doSelectOne(inVO);
		log.debug("2. outVO : " + outVO);
		
//		조회시 조회수 증가  게시글 작성자는 x
		int flag = 0;
		if(outVO!=null) {
			flag = boardMapper.readCntUpdate(inVO);
			log.debug("3. 조회 Count 증가 : " + flag);
			
			if (1==flag) {
				outVO.setReadCnt(outVO.getReadCnt()+1);
			}
			
		}
		
		return outVO;
	}

	@Override
	public List<Board> doRetrieve(DTO search) throws SQLException {
		log.debug("1. param : " + search);
		
		return boardMapper.doRetrieve(search);
	}

	@Override
	public int doUpdate(Board inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		
		return boardMapper.doUpdate(inVO);
	}

	@Override
	public int doDelete(Board inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		
		return boardMapper.doDelete(inVO);
	}

}
