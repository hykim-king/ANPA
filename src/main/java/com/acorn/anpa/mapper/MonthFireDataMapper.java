package com.acorn.anpa.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.acorn.anpa.cmn.Search;
import com.acorn.anpa.cmn.WorkDiv;
import com.acorn.anpa.firedata.domain.Firedata;
@Mapper
public interface MonthFireDataMapper extends WorkDiv<Firedata> {
	
	/**
	 * 오늘/검색 월에 대한 화재건수,사망자수,부상자수,피해액
	 * @param fire01 
	 * @return
	 * @throws SQLException
	 */
	Firedata todayMonthData(Firedata date) throws SQLException;
	
	/**
	 * 검색 월 화재장소 대분류 상위 3순위
	 * @return
	 * @throws SQLException
	 */
	List<Firedata> locBigData(Firedata date) throws SQLException;
	
	/**
	 * 검색 월 화재장소 소분류 상위 3순위
	 * @return
	 * @throws SQLException
	 */
	List<Firedata> locMidData(Firedata date) throws SQLException;
	
	/**
	 * 검색 월 발화원인 소분류 상위 3순위
	 * @return
	 * @throws SQLException
	 */
	List<Firedata> factorMidData(Firedata date) throws SQLException;
	
	
}
