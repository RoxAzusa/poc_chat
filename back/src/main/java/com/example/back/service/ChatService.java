package com.example.back.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.back.dto.ChatMessageDto;
import com.example.back.model.MessageModel;
import com.example.back.model.SupportChatModel;
import com.example.back.model.UserModel;
import com.example.back.repository.MessageRepository;
import com.example.back.repository.SupportChatRepository;
import com.example.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
	private final MessageRepository messageRepository;
	private final SupportChatRepository supportChatRepository;
	private final UserRepository userRepository;
	
	public void saveMessage(ChatMessageDto chatMessageDto) {
		SupportChatModel chat = supportChatRepository.findById(chatMessageDto.getSupportChatId())
				.orElseThrow(() -> new IllegalArgumentException("Support chat not found"));
		
		UserModel sender = userRepository.findById(chatMessageDto.getSenderId())
				.orElseThrow(() -> new IllegalArgumentException("Sender not found"));
		
		MessageModel message = new MessageModel();
		message.setSupportChat(chat);
		message.setSender(sender);
		message.setContent(chatMessageDto.getContent());
		message.setSendDate(chatMessageDto.getSendDate() != null ? chatMessageDto.getSendDate() : LocalDateTime.now());
		
		messageRepository.save(message);
	}
}
