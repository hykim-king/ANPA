package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.prevent.domain.prevent;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.mapper.PreventMapper;

@Service
public class PreventServiceImpl implements PreventService,PLog{ 

    @Autowired
    private PreventMapper preventMapper;
    
    public PreventServiceImpl() {
		log.debug("┌─────────────────────────");
		log.debug("│PreventServiceImpl()     ");
		log.debug("└─────────────────────────");
	}

    @Override
    public prevent doSelectOne(int preventSeq) throws SQLException {
        return preventMapper.doSelectOne(preventSeq);
    }

    @Override
    public List<prevent> doRetrieve(Search search) throws SQLException {
        return preventMapper.doRetrieve(search);
    }
}

