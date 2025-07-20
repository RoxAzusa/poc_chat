package com.example.back.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ChatMessageDto {
	private Integer supportChatId;
	private Integer senderId;
	private String content;
	private LocalDateTime sendDate;
}
