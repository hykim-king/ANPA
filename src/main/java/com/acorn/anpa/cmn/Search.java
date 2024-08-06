package com.acorn.anpa.cmn;

public class Search extends DTO{
	
	private String searchDiv; //검색구분
	private String searchWord;//검색어
	private String searchCity;//시군구
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

	
	
	public String getSearchCity() {
		return searchCity;
	}

	public void setSearchCity(String searchCity) {
		this.searchCity = searchCity;
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
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", searchCity=" + searchCity
				+ ", searchDateStart=" + searchDateStart + ", searchDateEnd=" + searchDateEnd + ", div=" + div
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
