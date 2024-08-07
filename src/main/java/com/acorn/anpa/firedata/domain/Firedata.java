package com.acorn.anpa.firedata.domain;

import com.acorn.anpa.cmn.DTO;

public class Firedata extends DTO{
	private	int    fireSeq;	// 화재정보시퀀스
	private	int    injuredSum;	// 피해자 소계
	private	int    dead;		// 사망자
	private int    injured;		// 부상자
	private int    amount;		// 피해액
	
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
	private int fireCount; //현재 화재건수
	private int monthFireCount;//월별 화재건수
	private int monthDead;//월별 사망자수
	private int monthInjured;//월별 부상자수
	private int monthAmount;//월별 피해액
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

	public int getFireCount() {
		return fireCount;
	}

	public void setFireCount(int fireCount) {
		this.fireCount = fireCount;
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

	@Override
	public String toString() {
		return "Firedata [fireSeq=" + fireSeq + ", injuredSum=" + injuredSum + ", dead=" + dead + ", injured=" + injured
				+ ", amount=" + amount + ", subFactor=" + subFactor + ", subFactorBigNm=" + subFactorBigNm
				+ ", subFactorMidNm=" + subFactorMidNm + ", subLoc=" + subLoc + ", subLocBigNm=" + subLocBigNm
				+ ", subLocMidNm=" + subLocMidNm + ", subCity=" + subCity + ", subCityBigNm=" + subCityBigNm
				+ ", subCityMidNm=" + subCityMidNm + ", fireCount=" + fireCount + ", monthFireCount=" + monthFireCount
				+ ", monthDead=" + monthDead + ", monthInjured=" + monthInjured + ", monthAmount=" + monthAmount
				+ ", toString()=" + super.toString() + "]";
	}

	
}
