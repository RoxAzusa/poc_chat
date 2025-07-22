package com.example.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.ChatModel;

public interface ChatRepository extends JpaRepository<ChatModel, Integer> {

}
