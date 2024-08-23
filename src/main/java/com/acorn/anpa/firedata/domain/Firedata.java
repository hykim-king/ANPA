package com.acorn.anpa.firedata.domain;

import com.acorn.anpa.cmn.DTO;

public class Firedata extends DTO{
	private	int    fireSeq;	// 화재정보시퀀스
	private	int    injuredSum;	// 피해자 소계
	private	int    dead;		// 사망자
	private int    injured;		// 부상자
	private int    amount;		// 피해액
	private long   amountSum;   // 피해액 통계
	
	private int    subFactor;	// 화재요인코드
	private String subFactorBigNm; // 화재요인 대분류 이름
	private String subFactorMidNm; // 화재요인 소분류 이름
	
	private int    subLoc;		// 화재장소코드
	private String subLocBigNm;    // 화재장소 대분류 이름
	private String subLocMidNm;    // 화재장소 소분류 이름
	
	private int    subCity;		// 시군구 코드
	private String subCityBigNm;   // 시도 이름
	private String subCityMidNm;   // 시군구 이름
	
	//월별화재데이터 검색 시 필요 추가(승희)
	private int todayFireCount        ;//오늘 화재건수&화재요인(대)코드 (준모 작성)
	private int todayDead             ;//오늘 사망자수&화재장소(대)코드 (준모 작성)
	private int todayInjured          ;//오늘 부상자수&시도 (시군구/대) 코드 (준모 작성)
	private int todayAmount           ;//오늘 피해액
	private int monthFireCount        ;//검색 월 화재건수
	private int monthDead             ;//검색 월 사망자수
	private int monthInjured          ;//검색 월 부상자수
	private int monthAmount           ;//검색 월 피해액
	
	private int lastYearDayFireCount  ;//전년동기 일 화재건수
	private int lastYearDayDead       ;//전년동기 일 사망자수
	private int lastYearDayInjured    ;//전년동기 일 부상자수
	private int lastYearDayAmount     ;//전년동기 일 피해액
	private int lastYearMonthFireCount;//전년동기 월 화재건수
	private int lastYearMonthDead     ;//전년동기 월 사망자수
	private int lastYearMonthInjured  ;//전년동기 월 부상자수
	private int lastYearMonthAmount   ;//전년동기 월 피해액
	
	private int monthAvg;//월별 평균 데이터
	
	public Firedata() {}

	public Firedata(int fireSeq, int injuredSum, int dead, int injured, int amount, int subFactor, int subLoc,
			int subCity) {
		super();
		this.fireSeq = fireSeq;
		this.injuredSum = injuredSum;
		this.dead = dead;
		this.injured = injured;
		this.amount = amount;
		this.subFactor = subFactor;
		this.subLoc = subLoc;
		this.subCity = subCity;
	}

	public int getFireSeq() {
		return fireSeq;
	}

	public void setFireSeq(int fireSeq) {
		this.fireSeq = fireSeq;
	}

	public int getInjuredSum() {
		return injuredSum;
	}

	public void setInjuredSum(int injuredSum) {
		this.injuredSum = injuredSum;
	}

	public int getDead() {
		return dead;
	}

	public void setDead(int dead) {
		this.dead = dead;
	}

	public int getInjured() {
		return injured;
	}

	public void setInjured(int injured) {
		this.injured = injured;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSubFactor() {
		return subFactor;
	}

	public void setSubFactor(int subFactor) {
		this.subFactor = subFactor;
	}

	public String getSubFactorBigNm() {
		return subFactorBigNm;
	}

	public void setSubFactorBigNm(String subFactorBigNm) {
		this.subFactorBigNm = subFactorBigNm;
	}

	public String getSubFactorMidNm() {
		return subFactorMidNm;
	}

	public void setSubFactorMidNm(String subFactorMidNm) {
		this.subFactorMidNm = subFactorMidNm;
	}

	public int getSubLoc() {
		return subLoc;
	}

	public void setSubLoc(int subLoc) {
		this.subLoc = subLoc;
	}

	public String getSubLocBigNm() {
		return subLocBigNm;
	}

	public void setSubLocBigNm(String subLocBigNm) {
		this.subLocBigNm = subLocBigNm;
	}

	public String getSubLocMidNm() {
		return subLocMidNm;
	}

	public void setSubLocMidNm(String subLocMidNm) {
		this.subLocMidNm = subLocMidNm;
	}

	public int getSubCity() {
		return subCity;
	}

	public void setSubCity(int subCity) {
		this.subCity = subCity;
	}

	public String getSubCityBigNm() {
		return subCityBigNm;
	}

	public void setSubCityBigNm(String subCityBigNm) {
		this.subCityBigNm = subCityBigNm;
	}

	public String getSubCityMidNm() {
		return subCityMidNm;
	}

	public void setSubCityMidNm(String subCityMidNm) {
		this.subCityMidNm = subCityMidNm;
	}

	public int getTodayFireCount() {
		return todayFireCount;
	}

	public void setTodayFireCount(int todayFireCount) {
		this.todayFireCount = todayFireCount;
	}

	public int getTodayDead() {
		return todayDead;
	}

	public void setTodayDead(int todayDead) {
		this.todayDead = todayDead;
	}

	public int getTodayInjured() {
		return todayInjured;
	}

	public void setTodayInjured(int todayInjured) {
		this.todayInjured = todayInjured;
	}

	public int getTodayAmount() {
		return todayAmount;
	}

	public void setTodayAmount(int todayAmount) {
		this.todayAmount = todayAmount;
	}

	public int getMonthFireCount() {
		return monthFireCount;
	}

	public void setMonthFireCount(int monthFireCount) {
		this.monthFireCount = monthFireCount;
	}

	public int getMonthDead() {
		return monthDead;
	}

	public void setMonthDead(int monthDead) {
		this.monthDead = monthDead;
	}

	public int getMonthInjured() {
		return monthInjured;
	}

	public void setMonthInjured(int monthInjured) {
		this.monthInjured = monthInjured;
	}

	public int getMonthAmount() {
		return monthAmount;
	}

	public void setMonthAmount(int monthAmount) {
		this.monthAmount = monthAmount;
	}

	public int getLastYearDayFireCount() {
		return lastYearDayFireCount;
	}

	public void setLastYearDayFireCount(int lastYearDayFireCount) {
		this.lastYearDayFireCount = lastYearDayFireCount;
	}

	public int getLastYearDayDead() {
		return lastYearDayDead;
	}

	public void setLastYearDayDead(int lastYearDayDead) {
		this.lastYearDayDead = lastYearDayDead;
	}

	public int getLastYearDayInjured() {
		return lastYearDayInjured;
	}

	public void setLastYearDayInjured(int lastYearDayInjured) {
		this.lastYearDayInjured = lastYearDayInjured;
	}

	public int getLastYearDayAmount() {
		return lastYearDayAmount;
	}

	public void setLastYearDayAmount(int lastYearDayAmount) {
		this.lastYearDayAmount = lastYearDayAmount;
	}

	public int getLastYearMonthFireCount() {
		return lastYearMonthFireCount;
	}

	public void setLastYearMonthFireCount(int lastYearMonthFireCount) {
		this.lastYearMonthFireCount = lastYearMonthFireCount;
	}

	public int getLastYearMonthDead() {
		return lastYearMonthDead;
	}

	public void setLastYearMonthDead(int lastYearMonthDead) {
		this.lastYearMonthDead = lastYearMonthDead;
	}

	public int getLastYearMonthInjured() {
		return lastYearMonthInjured;
	}

	public void setLastYearMonthInjured(int lastYearMonthInjured) {
		this.lastYearMonthInjured = lastYearMonthInjured;
	}

	public int getLastYearMonthAmount() {
		return lastYearMonthAmount;
	}

	public void setLastYearMonthAmount(int lastYearMonthAmount) {
		this.lastYearMonthAmount = lastYearMonthAmount;
	}

	public int getMonthAvg() {
		return monthAvg;
	}

	public void setMonthAvg(int monthAvg) {
		this.monthAvg = monthAvg;
	}

	@Override
	public String toString() {
		return "Firedata [fireSeq=" + fireSeq + ", injuredSum=" + injuredSum + ", dead=" + dead + ", injured=" + injured
				+ ", amount=" + amount + ", subFactor=" + subFactor + ", subFactorBigNm=" + subFactorBigNm
				+ ", subFactorMidNm=" + subFactorMidNm + ", subLoc=" + subLoc + ", subLocBigNm=" + subLocBigNm
				+ ", subLocMidNm=" + subLocMidNm + ", subCity=" + subCity + ", subCityBigNm=" + subCityBigNm
				+ ", subCityMidNm=" + subCityMidNm + ", todayFireCount=" + todayFireCount + ", todayDead=" + todayDead
				+ ", todayInjured=" + todayInjured + ", todayAmount=" + todayAmount + ", monthFireCount="
				+ monthFireCount + ", monthDead=" + monthDead + ", monthInjured=" + monthInjured + ", monthAmount="
				+ monthAmount + ", lastYearDayFireCount=" + lastYearDayFireCount + ", lastYearDayDead="
				+ lastYearDayDead + ", lastYearDayInjured=" + lastYearDayInjured + ", lastYearDayAmount="
				+ lastYearDayAmount + ", lastYearMonthFireCount=" + lastYearMonthFireCount + ", lastYearMonthDead="
				+ lastYearMonthDead + ", lastYearMonthInjured=" + lastYearMonthInjured + ", lastYearMonthAmount="
				+ lastYearMonthAmount + ", monthAvg=" + monthAvg + ", toString()=" + super.toString() + "]";
	}

	
	
}
