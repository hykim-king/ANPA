package com.acorn.anpa.board.domain;

import com.acorn.anpa.cmn.DTO;

public class Board extends DTO{
	private int boardSeq;//게시판 번호
	private String title    ;//제목
	private int readCnt ;//조회수
	private String contents ;//내용
	private String divYn ;//공지사항 여부

	public Board() {	}

	public Board(int boardSeq, String title, int readCnt, String contents, String divYn) {
		super();
		this.boardSeq = boardSeq;
		this.title = title;
		this.readCnt = readCnt;
		this.contents = contents;
		this.divYn = divYn;
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

	public String getDivYn() {
		return divYn;
	}

	public void setDivYn(String divYn) {
		this.divYn = divYn;
	}

	@Override
	public String toString() {
		return "Board [boardSeq=" + boardSeq + ", title=" + title + ", readCnt=" + readCnt + ", contents=" + contents
				+ ", divYn=" + divYn + ", toString()=" + super.toString() + "]";
	}
	
}
