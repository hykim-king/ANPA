package com.pcwk.ehr.firedata.service;

import java.sql.SQLException;

import com.pcwk.ehr.cmn.WorkDiv;
import com.pcwk.ehr.firedata.domain.Firedata;

public interface FireDataService extends WorkDiv<Firedata>{
	void sendEmail(String title, String contents, String userEmail);

	Firedata doSaveData(Firedata inVO) throws SQLException;
}
