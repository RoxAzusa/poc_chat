package com.example.back.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "message")
public class MessageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_support_chat", nullable = false)
	private SupportChatModel supportChat;
	
	@ManyToOne
	@JoinColumn(name = "id_sender", nullable = false)
	private UserModel sender;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "send_date")
	private LocalDateTime sendDate = LocalDateTime.now();
}
