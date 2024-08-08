package com.acorn.anpa.cmn;

public class Search extends DTO{
	
	private String searchDiv; //검색구분
	private String searchWord;//검색어
	
	private String BigNm; // 카테고리 대분류 이름
	private String MidNm; // 카테고리 소분류 이름
	
	private String subCityBigNm;   // 시도 이름
	private String subCityMidNm;   // 시군구 이름
	
	private String searchDateStart;//날짜시작
	private String searchDateEnd;//날짜 끝
	
	private String div; //게시판 구분
	
	public Search() {	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getBigNm() {
		return BigNm;
	}

	public void setBigNm(String bigNm) {
		BigNm = bigNm;
	}

	public String getMidNm() {
		return MidNm;
	}

	public void setMidNm(String midNm) {
		MidNm = midNm;
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

	public String getSearchDateStart() {
		return searchDateStart;
	}

	public void setSearchDateStart(String searchDateStart) {
		this.searchDateStart = searchDateStart;
	}

	public String getSearchDateEnd() {
		return searchDateEnd;
	}

	public void setSearchDateEnd(String searchDateEnd) {
		this.searchDateEnd = searchDateEnd;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	@Override
	public String toString() {
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", BigNm=" + BigNm + ", MidNm=" + MidNm
				+ ", subCityBigNm=" + subCityBigNm + ", subCityMidNm=" + subCityMidNm + ", searchDateStart="
				+ searchDateStart + ", searchDateEnd=" + searchDateEnd + ", div=" + div + ", toString()="
				+ super.toString() + "]";
	}

	
	
	
}
