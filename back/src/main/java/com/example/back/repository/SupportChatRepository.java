package com.example.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.SupportChatModel;

public interface SupportChatRepository extends JpaRepository<SupportChatModel, Integer> {

}
