package com.acorn.anpa.prevent.domain;

import com.acorn.anpa.cmn.DTO;

public class prevent extends DTO {
    private String preventSeq ;
    private String title      ;
    private int    readCnt    ;
    private String contents   ;
    private String imgSrc     ;

  
    
    public prevent() {}
    public prevent(String preventSeq, String title, int readCnt, String contents, String imgSrc, String modDt,
            String regId, String regDt, String modId) {
        super();
        this.preventSeq = preventSeq;
        this.title = title;
        this.readCnt = readCnt;
        this.contents = contents;
        this.imgSrc = imgSrc;
        
    }
    public String getPreventSeq() {
        return preventSeq;
    }
    public void setPreventSeq(String preventSeq) {
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
    
    
    @Override
    public String toString() {
        return "prevent [preventSeq=" + preventSeq + ", title=" + title + ", readCnt=" + readCnt + ", contents="
                + contents + ", imgSrc=" + imgSrc 
                 + ", toString()=" + super.toString() + "]";
    }
   
    
    
    
}