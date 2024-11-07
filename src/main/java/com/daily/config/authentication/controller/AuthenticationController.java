package com.daily.config.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.config.authentication.dto.AuthenticationDTO;
import com.daily.config.authentication.service.AuthenticationService;
import com.daily.config.infra.TokenService;
import com.daily.user.dto.RegisterDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	TokenService tokenService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		return authenticationService.login(data);
	}

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		return authenticationService.register(data);
	}
}
