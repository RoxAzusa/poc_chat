package com.example.back.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.back.dto.ChatMessageDto;
import com.example.back.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatService chatService;
	
	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatMessageDto messageDto) {
		chatService.saveMessage(messageDto);
		
		String destination = "/topic/support." + messageDto.getSupportChatId();
		messagingTemplate.convertAndSend(destination, messageDto);
	}
}
