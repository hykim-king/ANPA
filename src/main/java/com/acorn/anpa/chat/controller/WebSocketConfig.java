package com.acorn.anpa.chat.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.acorn.anpa.cmn.PLog;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer, /* WebSocketConfigurer, */ PLog {
	
	public WebSocketConfig() {
		log.debug("┌──────────────────────────────────────────");
		log.debug("│ WebSocketConfig()");
		log.debug("└──────────────────────────────────────────");
	}
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();
		log.debug("┌──────────────────────────────────────────");
		log.debug("│ SocketConnecting..()");
		log.debug("└──────────────────────────────────────────");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }
    
	
	
	/*
	 * @Override public void registerWebSocketHandlers(WebSocketHandlerRegistry
	 * registry) { registry.addHandler(new WebSocketInfoHandler(),
	 * "/ws/info").setAllowedOriginPatterns("*"); // CORS 설정 } }
	 */
	 
}
