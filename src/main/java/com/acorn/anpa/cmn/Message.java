package com.acorn.anpa.cmn;

public class Message extends DTO {

    private int messageId; // 1 or 0
    private String messageContents; // 메시지 내용
    private String token; // JWT 토큰 추가

    public Message() {
    }

    public Message(int messageId, String messageContents) {
        this.messageId = messageId;
        this.messageContents = messageContents;
    }

    public Message(int messageId, String messageContents, String token) {
        this.messageId = messageId;
        this.messageContents = messageContents;
        this.token = token;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageContents() {
        return messageContents;
    }

    public void setMessageContents(String messageContents) {
        this.messageContents = messageContents;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Message [messageId=" + messageId + ", messageContents=" + messageContents + ", token=" + token + "]";
    }
}
