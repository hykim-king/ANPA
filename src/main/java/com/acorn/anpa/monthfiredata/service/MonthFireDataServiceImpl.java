package com.acorn.anpa.monthfiredata.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acorn.anpa.cmn.DTO;
import com.acorn.anpa.cmn.PLog;
import com.acorn.anpa.firedata.domain.Firedata;
import com.acorn.anpa.mapper.MonthFireDataMapper;
@Service("monthFireDataServiceImpl")
public class MonthFireDataServiceImpl implements MonthFireDataService, PLog {

	@Autowired
	MonthFireDataMapper monthFireDataMapper;
	
	public MonthFireDataServiceImpl() {
		
	}
	
	@Override
	public Firedata todayMonthData(Firedata date) throws SQLException {
		log.debug("1. param : " + date);
		return this.monthFireDataMapper.todayMonthData(date);
	}

	@Override
	public List<Firedata> locBigData(Firedata date) throws SQLException {
		log.debug("1. param : " + date);
		return this.monthFireDataMapper.locBigData(date);
	}

	@Override
	public List<Firedata> locMidData(Firedata date) throws SQLException {
		log.debug("1. param : " + date);
		return this.monthFireDataMapper.locMidData(date);
	}

	@Override
	public List<Firedata> factorMidData(Firedata date) throws SQLException {
		log.debug("1. param : " + date);
		return this.monthFireDataMapper.factorMidData(date);
	}

	
	//사용안함---------------------------------------------------------------------

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
