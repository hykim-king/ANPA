package com.acorn.anpa.prevent.domain;

import com.acorn.anpa.cmn.DTO;

public class Prevent extends DTO {
    private int preventSeq ;
    private String title      ;
    private int    readCnt    ;
    private String contents   ;
    private String imgSrc     ;
    
    //2024/08/17 추가
	private String regId   ;//생성자
	private String regDt   ;//생성일
	private String modId   ;//수정자
	private String modDt   ;//수정일    
    
  //((1, "제목01", 0, "내용01", 0,  "사용안함", "사용안함", "ADMIN01", "사용안함","ADMIN01")
   
    public Prevent() {}
	public Prevent(int preventSeq, String title, int readCnt, String contents, String imgSrc) {
		super();
		this.preventSeq = preventSeq;
		this.title = title;
		this.readCnt = readCnt;
		this.contents = contents;
		this.imgSrc = imgSrc;
	}
	public int getPreventSeq() {
		return preventSeq;
	}
	public void setPreventSeq(int preventSeq) {
		this.preventSeq = preventSeq;
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
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
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
		return "Prevent [preventSeq=" + preventSeq + ", title=" + title + ", readCnt=" + readCnt + ", contents="
				+ contents + ", imgSrc=" + imgSrc + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId
				+ ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}

	
	    
    
	
    
    
    
}