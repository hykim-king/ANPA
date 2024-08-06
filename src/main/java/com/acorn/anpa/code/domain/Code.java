package com.acorn.anpa.code.domain;

import com.acorn.anpa.cmn.DTO;

public class Code extends DTO{
	private String masterCode;//마스터코드 
	private int    subCode   ;//서브코드 해당 테이블 pk
	private String bigList   ;//대분류
	private String midList   ;//소분류
	private int    useYn     ;//사용여부
	private int    mainCode  ;//대분류 코드
	
	public Code() {	}

	public Code(String masterCode, int subCode, String bigList, String midList, int useYn) {
		super();
		this.masterCode = masterCode;
		this.subCode = subCode;
		this.bigList = bigList;
		this.midList = midList;
		this.useYn = useYn;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public int getSubCode() {
		return subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public String getBigList() {
		return bigList;
	}

	public void setBigList(String bigList) {
		this.bigList = bigList;
	}

	public String getMidList() {
		return midList;
	}

	public void setMidList(String midList) {
		this.midList = midList;
	}

	public int getUseYn() {
		return useYn;
	}

	public void setUseYn(int useYn) {
		this.useYn = useYn;
	}

	public int getMainCode() {
		return mainCode;
	}

	public void setMainCode(int mainCode) {
		this.mainCode = mainCode;
	}

	@Override
	public String toString() {
		return "Code [masterCode=" + masterCode + ", subCode=" + subCode + ", bigList=" + bigList + ", midList="
				+ midList + ", useYn=" + useYn + ", mainCode=" + mainCode + ", toString()=" + super.toString() + "]";
	}
	
	
}
