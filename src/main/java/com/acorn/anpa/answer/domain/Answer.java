package com.acorn.anpa.answer.domain;

import com.acorn.anpa.cmn.DTO;

public class Answer extends DTO{
	private int answerSeq  ;//댓글 번호
	private int boardSeq   ;//게시판 번호
	private String contents;//내용

	public Answer() {	}

	public Answer(int answerSeq, int boardSeq, String contents) {
		super();
		this.answerSeq = answerSeq;
		this.boardSeq = boardSeq;
		this.contents = contents;
	}

	public int getAnswerSeq() {
		return answerSeq;
	}

	public void setAnswerSeq(int answerSeq) {
		this.answerSeq = answerSeq;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Answer [answerSeq=" + answerSeq + ", boardSeq=" + boardSeq + ", contents=" + contents + ", toString()="
				+ super.toString() + "]";
	}	
}
