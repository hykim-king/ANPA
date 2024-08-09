package com.acorn.anpa.board.domain;

import com.acorn.anpa.cmn.DTO;

public class Board extends DTO{
	private int boardSeq;//게시판 번호
	private String title    ;//제목
	private int readCnt ;//조회수
	private String contents ;//내용
	private String div ;//공지사항 여부
	private String regId   ;//생성자
	private String regDt   ;//생성일
	private String modId   ;//수정자
	private String modDt   ;//수정일

	public Board() {	}

	public Board(int boardSeq, String title, int readCnt, String contents, String div, String regId, String regDt,
			String modId, String modDt) {
		super();
		this.boardSeq = boardSeq;
		this.title = title;
		this.readCnt = readCnt;
		this.contents = contents;
		this.div = div;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "Board [boardSeq=" + boardSeq + ", title=" + title + ", readCnt=" + readCnt + ", contents=" + contents
				+ ", div=" + div + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}
