package com.acorn.anpa.chat.controller;

public class ChatRequestDto {
    private String sender;
    private String content;
    
    public ChatRequestDto() {}

	public ChatRequestDto(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    // 빌더 패턴을 구현하기 위한 내부 클래스
    public static class Builder {
        private String sender;
        private String content;

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public ChatRequestDto build() {
            return new ChatRequestDto(sender, content);
        }
    }    
}
