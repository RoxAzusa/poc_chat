package com.example.back.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, UUID> {

}
