package com.acorn.anpa.firedata.service;

import java.sql.SQLException;
import java.util.List;

import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.firedata.domain.Firedata;

public interface FireDataService extends WorkDiv<Firedata>{
	void sendEmail(String title, String contents, String userEmail);

	int doSaveData(Firedata inVO) throws SQLException;
	
	Firedata totalData(Search search) throws SQLException;
	
	Firedata doMainData() throws SQLException;
	
	List<Firedata> totalDataList(Search search) throws SQLException;
}