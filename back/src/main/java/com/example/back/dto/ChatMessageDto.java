package com.example.back.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatMessageDto {
	private Integer chatId;
	private Integer senderId;
	private String content;
	private LocalDateTime sendDate;
}
