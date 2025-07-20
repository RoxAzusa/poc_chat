package com.example.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
}
