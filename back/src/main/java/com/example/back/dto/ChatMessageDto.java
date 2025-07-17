package com.example.back.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatMessageDto {
	private UUID supportChatId;
	private UUID senderId;
	private String content;
	private LocalDateTime sendDate;
}
