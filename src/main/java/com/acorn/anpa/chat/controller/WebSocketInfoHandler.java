package com.acorn.anpa.chat.controller;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

public class WebSocketInfoHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 클라이언트와의 연결이 성공적으로 설정되었을 때 호출됩니다.
        System.out.println("Connected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지를 받았을 때 호출됩니다.
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // 클라이언트에게 응답 메시지를 전송합니다.
        session.sendMessage(new TextMessage("Hello from server!"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 오류 발생 시 호출됩니다.
        System.out.println("Error: " + exception.getMessage());
        session.close(); // 세션을 닫습니다.
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결이 종료된 후 호출됩니다.
        System.out.println("Disconnected: " + session.getId());
    }
}
