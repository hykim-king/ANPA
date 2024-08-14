package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.code.domain.Code;
import com.acorn.anpa.mapper.CodeMapper;
import com.acorn.anpa.mapper.PreventMapper;
import com.acorn.anpa.prevent.domain.prevent;
import com.acorn.anpa.prevent.service.PreventService;

@Service
public class PreventserviceImpl implements PreventService, PLog {

    @Autowired
    private PreventMapper preventMapper;
    
    @Autowired
	CodeMapper codeMapper;
    
    Code code = new Code();
   	List<Code> codeList;
    
    public void PreventServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│PreventServiceImpl()     ");
		log.debug("└─────────────────────────");
	}
    @Override
    public prevent doSelectOne(prevent inVO) throws SQLException {
        log.debug("┌─────────────────────────");
        log.debug("│ doSelectOne()           │");
        log.debug("└─────────────────────────");
        return preventMapper.doSelectOne(inVO);
    }

    @Override
    public List<prevent> doRetrieve(Search search) throws SQLException {
        log.debug("┌─────────────────────────");
        log.debug("│ doRetrieve()            │");
        log.debug("└─────────────────────────");
        return preventMapper.doRetrieve(search);
    }

    @Override
    public int getTotalCount(Search search) {
        log.debug("┌─────────────────────────");
        log.debug("│ getTotalCount()         │");
        log.debug("└─────────────────────────");
        return preventMapper.getTotalCount(search);
    
    }
    
    @Override
    public int doSave(prevent inVO) throws SQLException {
		{  // prevent -> Prevent
		    log.debug("┌─────────────────────────");
		    log.debug("│ doSave()                │");
		    log.debug("└─────────────────────────");
		    return preventMapper.doSave(inVO);
		
		}
    }
    }