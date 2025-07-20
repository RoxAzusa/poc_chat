package com.example.back.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "support_chat")
public class SupportChatModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name ="id_user_user", nullable = false)
	private UserModel requestingUser;
	
	@ManyToOne
	@JoinColumn(name ="id_user_support")
	private UserModel userAnswerSupport;
	
	@Column(name = "solved")
	private boolean solved = false;
	
	@OneToMany(mappedBy = "supportChat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MessageModel> messages;
}
