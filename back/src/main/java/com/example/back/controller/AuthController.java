package com.example.back.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.dto.LoginRequestDto;
import com.example.back.model.UserModel;
import com.example.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
		Optional<UserModel> user = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
		
		if(user == null || user.isEmpty()) {
			return ResponseEntity.status(401).body("Unauthorized");
		}
		
		return ResponseEntity.ok(user);
	}
}
