package com.example.back.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.back.dto.ChatMessageDto;
import com.example.back.model.MessageModel;
import com.example.back.model.ChatModel;
import com.example.back.model.UserModel;
import com.example.back.repository.MessageRepository;
import com.example.back.repository.ChatRepository;
import com.example.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
	private final MessageRepository messageRepository;
	private final ChatRepository chatRepository;
	private final UserRepository userRepository;

	public void saveMessage(ChatMessageDto chatMessageDto) {
		UserModel sender = userRepository.findById(chatMessageDto.getSenderId())
				.orElseThrow(() -> new IllegalArgumentException("Sender not found"));

		if (chatMessageDto.getChatId() == null) {
			ChatModel supportChat = new ChatModel();
			supportChat.setRequestingUser(sender);
			chatRepository.save(supportChat);
			chatMessageDto.setChatId(supportChat.getId());
		}

		ChatModel chat = chatRepository.findById(chatMessageDto.getChatId())
				.orElseThrow(() -> new IllegalArgumentException("Chat not found"));

		if (chat != null && sender.getRole() == UserModel.RoleType.Support && chat.getUserAnswerSupport() == null) {
			chat.setUserAnswerSupport(sender);
			chatRepository.save(chat);
		}

		MessageModel message = new MessageModel();
		message.setChat(chat);
		message.setSender(sender);
		message.setContent(chatMessageDto.getContent());
		message.setSendDate(chatMessageDto.getSendDate() != null ? chatMessageDto.getSendDate() : LocalDateTime.now());

		messageRepository.save(message);
	}
}
