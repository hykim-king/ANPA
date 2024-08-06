package com.acorn.anpa.firedata.domain;

import com.acorn.anpa.cmn.DTO;

public class Firedata extends DTO{
	private	int    fireSeq;	// 화재정보시퀀스
	private	int    injuredSum;	// 피해자 소계
	private	int    dead;		// 사망자
	private int    injured;		// 부상자
	private int    amount;		// 피해액
	
	private int    subFactor;	// 화재요인코드
	private String subFactorNm; // 화재요인이름
	
	private int    subLoc;		// 화재장소코드
	private String subLocNm;    // 화재장소이름
	
	private int    subCity;		// 시군구 코드
	private String subCityNm;   // 시군구 이름
	
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

	
	public String getSubFactorNm() {
		return subFactorNm;
	}

	public void setSubFactorNm(String subFactorNm) {
		this.subFactorNm = subFactorNm;
	}

	public String getSubLocNm() {
		return subLocNm;
	}

	public void setSubLocNm(String subLocNm) {
		this.subLocNm = subLocNm;
	}

	public String getSubCityNm() {
		return subCityNm;
	}

	public void setSubCityNm(String subCityNm) {
		this.subCityNm = subCityNm;
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

	public int getSubLoc() {
		return subLoc;
	}

	public void setSubLoc(int subLoc) {
		this.subLoc = subLoc;
	}

	public int getSubCity() {
		return subCity;
	}

	public void setSubCity(int subCity) {
		this.subCity = subCity;
	}

	@Override
	public String toString() {
		return "Firedata [fireSeq=" + fireSeq + ", injuredSum=" + injuredSum + ", dead=" + dead + ", injured=" + injured
				+ ", amount=" + amount + ", subFactor=" + subFactor + ", subFactorNm=" + subFactorNm + ", subLoc="
				+ subLoc + ", subLocNm=" + subLocNm + ", subCity=" + subCity + ", subCityNm=" + subCityNm
				+ ", toString()=" + super.toString() + "]";
	}

	
	
	
}
