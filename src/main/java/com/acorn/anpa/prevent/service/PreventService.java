package com.acorn.anpa.prevent.service;

import java.sql.SQLException;
import java.util.List;

import com.acorn.anpa.board.domain.Board;
import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.prevent.domain.prevent;

public interface PreventService extends WorkDiv<prevent> {

   
	int getTotalCount(Search search);
 
}
