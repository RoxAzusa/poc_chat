package com.example.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.dto.ChatMessageDto;
import com.example.back.model.ChatModel;
import com.example.back.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatService chatService;

	@MessageMapping("/chat.send")
	public void sendMessage(@Payload ChatMessageDto messageDto) {
		chatService.saveMessage(messageDto);

		String destination = "/topic/support." + messageDto.getChatId();
		messagingTemplate.convertAndSend(destination, messageDto);
	}

	@PutMapping("/end/{idChat}")
	public ResponseEntity<?> endChat(@PathVariable int idChat) {
		ChatModel result = chatService.solvedChat(idChat);

		if(result == null) {
			return ResponseEntity.status(401).body(null);
		}
		return ResponseEntity.ok(result);
	}
}
