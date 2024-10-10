package com.acorn.anpa.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
    private final SimpMessagingTemplate messagingTemplate;

    // 생성자
    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/chat") // 클라이언트가 이 경로로 GET 요청 시 chatPage.jsp를 반환
    public ModelAndView chatPage() {
        return new ModelAndView("user/chatPage"); // chatPage.jsp를 반환
    }

    // 입장을 할 때 사용하는 루트입니다.
    @MessageMapping("/enter")
    public void enter(ChatRequestDto dto) {
        messagingTemplate.convertAndSend("/sub/chat/room/1", dto);
    }

    // 퇴장을 할 때 사용하는 루트입니다.
    @MessageMapping("/leave")
    public void leave(ChatRequestDto dto) {
        messagingTemplate.convertAndSend("/sub/chat/room/1", dto);
    }
    
    // 메세지를 전송할 때 사용하는 루트입니다.
    @MessageMapping("/message")
    public void message(ChatRequestDto dto) {
        messagingTemplate.convertAndSend("/sub/chat/room/1", dto);
    }
}
