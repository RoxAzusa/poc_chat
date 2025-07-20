package com.example.back.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.back.model.UserModel;
import com.example.back.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;	
	
	public Optional<UserModel> login(String email, String password) {
		Optional<UserModel> user = userRepository.findUserByEmail(email);
		
		if(!user.isEmpty() && password.equals(user.get().getPassword())) {
			return user;
		}
		
		return null;
	}
}
