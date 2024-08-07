package com.acorn.anpa.monthfiredata.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
@Service
public class MonthFireDataServiceImpl implements MonthFireDataService, PLog {

	@Override
	public int doSave(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Firedata doSelectOne(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Firedata> doRetrieve(DTO search) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdate(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(Firedata inVO) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
