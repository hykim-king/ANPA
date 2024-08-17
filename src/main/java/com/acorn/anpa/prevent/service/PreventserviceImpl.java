package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.mapper.CodeMapper;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.prevent.domain.Prevent;
import com.acorn.anpa.prevent.service.PreventService;

@Service
public class PreventserviceImpl implements PreventService, PLog {

    @Autowired
    private PreventMapper preventMapper;
    
   
    
    public void PreventServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│PreventServiceImpl()     ");
		log.debug("└─────────────────────────");
	} 
    
    
	@Override
	public List<Prevent> doRetrieve(DTO search) throws SQLException {
		log.debug("┌─────────────────────────");
        log.debug("│ doRetrieve()            │");
        log.debug("└─────────────────────────");
        
        log.debug("1. param : " + search);
        return preventMapper.doRetrieve(search);
	}
	@Override
	public int doUpdate(Prevent inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		return preventMapper.doUpdate(inVO);
	}
	@Override
	public int doDelete(Prevent inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		return preventMapper.doDelete(inVO);
	}
    
    @Override
    public int doSave(Prevent inVO) throws SQLException {
		{  // prevent -> Prevent
		    log.debug("┌─────────────────────────");
		    log.debug("│ doSave()                │");
		    log.debug("└─────────────────────────");
		    log.debug("1. param : "+inVO);
		    return preventMapper.doSave(inVO);
		
		}
    }


	@Override
	public Prevent doSelectOne(Prevent inVO) throws SQLException {
		log.debug("1. param : "+inVO);
		
		Prevent outVO = preventMapper.doSelectOne(inVO);
		log.debug("2. outVO : " + outVO);
		
 
		int flag = 0;
//		if(outVO!=null) {
//			flag = preventMapper.readCntUpdate(inVO);
//			log.debug("3. 조회 Count 증가 : " + flag);
//			
//			if (1==flag) {
//				outVO.setReadCnt(outVO.getReadCnt()+1);
//			}
//			
//		}
		
		return outVO;
	}
	
	  
    @Override
    public int getTotalCount(Search search) {
        log.debug("┌─────────────────────────");
        log.debug("│ getTotalCount()         │");
        log.debug("└─────────────────────────");
        return preventMapper.getTotalCount(search);
    
    }
    
 
}