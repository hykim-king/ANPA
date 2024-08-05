package com.acorn.anpa.firedata.domain;

import com.acorn.anpa.cmn.DTO;

public class Firedata extends DTO{
	private	int    fireSeq;	// 화재정보시퀀스
	private	int    injuredSum;	// 피해자 소계
	private	int    dead;		// 사망자
	private int    injured;		// 부상자
	private int    amount;		// 피해액
	private int    subFactor;	// 화재요인코드
	private int    subLoc;		// 화재장소코드
	private int    subCity;		// 시군구 코드
	private String regId;		// 수정자
	private String modId;		// 등록자
	private String regDt;		// 수정일
	private String modDt;		// 등록일
	
	public Firedata() {}

	public Firedata(int fireSeq, int injuredSum, int dead, int injured, int amount, int subFactor, int subLoc,
			int subCity, String regId, String modId, String regDt, String modDt) {
		super();
		this.fireSeq = fireSeq;
		this.injuredSum = injuredSum;
		this.dead = dead;
		this.injured = injured;
		this.amount = amount;
		this.subFactor = subFactor;
		this.subLoc = subLoc;
		this.subCity = subCity;
		this.regId = regId;
		this.modId = modId;
		this.regDt = regDt;
		this.modDt = modDt;
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

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "firedata [fireSeq=" + fireSeq + ", injuredSum=" + injuredSum + ", dead=" + dead + ", injured=" + injured
				+ ", amount=" + amount + ", subFactor=" + subFactor + ", subLoc=" + subLoc + ", subCity=" + subCity
				+ ", regId=" + regId + ", modId=" + modId + ", regDt=" + regDt + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}
}
